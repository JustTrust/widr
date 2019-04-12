package com.widr.net.data_flow.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.widr.net.data_flow.database.entities.ServerEntity


@Database(version = 1, entities = [(ServerEntity::class)])
abstract class LocalDatabase : RoomDatabase() {
    abstract fun serverDao(): ServerDao
}