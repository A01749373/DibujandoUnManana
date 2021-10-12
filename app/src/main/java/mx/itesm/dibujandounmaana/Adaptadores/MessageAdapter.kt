package mx.itesm.dibujandounmaana.Adaptadores

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import mx.itesm.dibujandounmaana.R
import mx.itesm.dibujandounmaana.model.Mensaje

class MessageAdapter(private val user: String): RecyclerView.Adapter<MessageAdapter.MessageViewHolder>() {

    private var messages: List<Mensaje> = emptyList()

    fun setData(list: List<Mensaje>){
        messages = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        return MessageViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.message_item,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        val message = messages[position]

        if(user == message.from){
            holder.itemView.findViewById<TextView>(R.id.tv_message).visibility = View.VISIBLE
            holder.itemView.findViewById<TextView>(R.id.tv_bot_message).visibility = View.GONE
            holder.itemView.findViewById<TextView>(R.id.tv_message).text = message.message
        }else {
            holder.itemView.findViewById<TextView>(R.id.tv_message).visibility = View.GONE
            holder.itemView.findViewById<TextView>(R.id.tv_bot_message).visibility = View.VISIBLE
            holder.itemView.findViewById<TextView>(R.id.tv_message).text = message.message
        }
    }

    override fun getItemCount(): Int {
        return messages.size
    }

    class MessageViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
}