package com.widr.net.ui.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.widr.net.data_flow.network.api_models.ChatMessageAnswer
import kotlinx.android.synthetic.main.chat_item.view.*
import android.view.Gravity
import android.widget.FrameLayout
import android.widget.FrameLayout.LayoutParams
import com.widr.net.R
import com.widr.net.utils.dpToPixel


class ChatAdapter(private val items: List<ChatMessageAnswer>, private val listener: (ChatMessageAnswer) -> Unit) : RecyclerView.Adapter<ChatAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.chat_item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(items[position], listener)

    override fun getItemCount(): Int {
        return items.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val twoDp = 2.dpToPixel(itemView.context)
        fun bind(item: ChatMessageAnswer, listener: (ChatMessageAnswer) -> Unit) = with(itemView) {
            chatMessage.text = item.msg
            val params = FrameLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
            if (item.myMsg.not()){
                itemView.setPadding(48.dpToPixel(context), twoDp, 0, twoDp)
                chatMessage.setBackgroundResource(R.drawable.massage_blue_bg)
                chatMessage.setTextColor(resources.getColor(R.color.feed_contact, null))
                params.gravity = Gravity.END
            }else{
                itemView.setPadding(0, twoDp, 48.dpToPixel(context), twoDp)
                chatMessage.setBackgroundResource(R.drawable.massage_violet_bg)
                chatMessage.setTextColor(resources.getColor(R.color.dark_violet, null))
                params.gravity = Gravity.START
            }
            chatMessage.layoutParams = params
        }
    }
}