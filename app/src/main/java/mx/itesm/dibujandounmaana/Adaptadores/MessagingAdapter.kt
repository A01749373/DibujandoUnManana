/*
Autores:
* Liam Garay Monroy
* Jorge Chávez Badillo
* Amy Murakami Tsutsumi
* Andrea Vianey Díaz Álvarez
* Ariadna Jocelyn Guzmán Jiménez
*/

package mx.itesm.dibujandounmaana.Adaptadores

//Librerias
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import mx.itesm.dibujandounmaana.R
import mx.itesm.dibujandounmaana.bot.Constants.RECEIVE_ID
import mx.itesm.dibujandounmaana.bot.Constants.SEND_ID
import mx.itesm.dibujandounmaana.model.Message

private val View.tv_message: Unit
    get() {}

class MessagingAdapter: RecyclerView.Adapter<MessagingAdapter.MessageViewHolder>() {
    //Creamos una lista con elementos "mensaje" que se actualizará conforme se recuban datos del sistema
    var messagesList = mutableListOf<Message>()

    inner class MessageViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        init {
            itemView.setOnClickListener {
                messagesList.removeAt(adapterPosition)
                notifyItemRemoved(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        return MessageViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.message_item, parent, false))
    }

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        //Se crean los elementos individuales del recycler view de acorde al contenido del mensaje
        val currenMessage = messagesList[position]
        
        when (currenMessage.id) {
            SEND_ID -> {
                holder.itemView.findViewById<TextView>(R.id.tv_message).apply {
                    text = currenMessage.message
                    visibility = View.VISIBLE
                }
                holder.itemView.findViewById<TextView>(R.id.tv_bot_message).visibility = View.GONE
            }

            RECEIVE_ID -> {
                holder.itemView.findViewById<TextView>(R.id.tv_bot_message).apply {
                    text = currenMessage.message
                    visibility = View.VISIBLE
                }
                holder.itemView.findViewById<TextView>(R.id.tv_message).visibility = View.GONE
            }
        }
    }

    override fun getItemCount(): Int {
        //Asignamos a cada elemento del renglón su correspondiente valor
        return messagesList.size
    }

    fun insertMessage(message: Message) {
        //Obtenemos la cantidad de grupos de datos que estamos obteniendo del servidor
        this.messagesList.add(message)
        notifyItemInserted(messagesList.size)
    }
}