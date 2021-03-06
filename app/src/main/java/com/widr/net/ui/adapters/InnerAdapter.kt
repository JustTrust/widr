package com.widr.net.ui.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.widr.net.R
import com.widr.net.data_flow.database.entities.ServerEntity
import kotlinx.android.synthetic.main.inner_item.view.*


class InnerAdapter(private val items: List<ServerEntity>, private val listener: (ServerEntity) -> Unit) : RecyclerView.Adapter<InnerAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.inner_item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(items[position], listener)

    override fun getItemCount(): Int {
        return items.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: ServerEntity, listener: (ServerEntity) -> Unit) = with(itemView) {
            innerName.text = item.name
        }
    }
}