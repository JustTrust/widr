package com.widr.net.data_flow.interactions

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.widr.net.data_flow.converters.ServerConverter
import com.widr.net.data_flow.database.LocalDatabase
import com.widr.net.data_flow.database.entities.ServerEntity
import com.widr.net.data_flow.network.Api
import io.reactivex.Observable
import io.reactivex.Single
import org.jetbrains.anko.doAsync
import timber.log.Timber

class ServerInteractor(private val api: Api, private val db: LocalDatabase) : IServerInteractor {

    override fun writeServersToDb(sitesList: List<ServerEntity>) {
        Timber.d("Write new server list to DB ${sitesList.size}")
        doAsync { db.serverDao().clearAndUpdateServer(sitesList) }
    }

    override fun updateListOfServers(): Single<List<ServerEntity>> {
        return api.getSitesList()
                .flatMap { Observable.fromIterable(it) }
                .map { ServerConverter.convert(it) }
                .toList()
    }

    override fun getListOfServers(): LiveData<List<ServerEntity>> {
        return MutableLiveData<List<ServerEntity>>().apply {
            value = ArrayList<ServerEntity>().apply {
                for (i in 1..120) {
                    add(ServerEntity(i.toString(), i))
                }
            }
        }
    }

    override fun clearServerList() {
        doAsync { db.serverDao().deleteAllServer() }
    }
}