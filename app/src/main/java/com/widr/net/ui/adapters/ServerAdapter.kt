package com.widr.net.ui.adapters

import android.support.v7.widget.PagerSnapHelper
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import com.widr.net.R
import com.widr.net.data_flow.database.entities.ServerEntity
import com.widr.net.utils.CircleTransform
import com.widr.net.utils.LinePagerIndicatorDecoration
import kotlinx.android.synthetic.main.feed_item.view.*
import java.util.*


class ServerAdapter(private val items: List<ServerEntity>, private val listener: (ServerEntity) -> Unit) : RecyclerView.Adapter<ServerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.feed_item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(items[position], listener)

    override fun getItemCount(): Int {
        return items.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: ServerEntity, listener: (ServerEntity) -> Unit) = with(itemView) {
            feedName.text = "Anna Mendez".plus(item.name)
            Picasso.get().load(if (item.distance % 2 == 1) R.drawable.images else R.drawable.images2).transform(CircleTransform()).into(feedIcon)
            setOnClickListener { listener(item) }
            if (feedInnerList.onFlingListener == null) PagerSnapHelper().attachToRecyclerView(feedInnerList)
            //recyclerViewIndicator.setRecyclerView(feedInnerList)
            feedInnerList.addItemDecoration(LinePagerIndicatorDecoration())
            feedInnerList.adapter = InnerAdapter(ArrayList<ServerEntity>().apply {
                add(ServerEntity("Avocat d'affaires", 1))
                add(ServerEntity("D'affaires avocat", 2))
                add(ServerEntity("Demain d'affaires", 3))
            }, {})
        }
    }
}