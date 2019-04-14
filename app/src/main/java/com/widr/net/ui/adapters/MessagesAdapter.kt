package com.widr.net.ui.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import com.widr.net.R
import com.widr.net.data_flow.network.api_models.MessagesAnswer
import com.widr.net.utils.CircleTransform
import kotlinx.android.synthetic.main.messages_item.view.*


class MessagesAdapter(private val items: List<MessagesAnswer>, private val listener: (MessagesAnswer) -> Unit) : RecyclerView.Adapter<MessagesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.messages_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(items[position], listener)

    override fun getItemCount(): Int {
        return items.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: MessagesAnswer, listener: (MessagesAnswer) -> Unit) = with(itemView) {
            messagesName.text = item.name
            Picasso.get().load(item.icon).transform(CircleTransform()).into(messagesIcon)
            setOnClickListener { listener(item) }
        }
    }
}