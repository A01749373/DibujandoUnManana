/*
Autores:
* Liam Garay Monroy
* Jorge Chávez Badillo
* Amy Murakami Tsutsumi
* Andrea Vianey Díaz Álvarez
* Ariadna Jocelyn Guzmán Jiménez
*/

package mx.itesm.dibujandounmaana.Adaptadores

//Librerías
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import mx.itesm.dibujandounmaana.R
import mx.itesm.dibujandounmaana.model.Mensaje

class MessageAdapter(private val user: String): RecyclerView.Adapter<MessageAdapter.MessageViewHolder>() {
    //Creamos una lista con elementos "mensaje" que se actualizará conforme se recuban datos del sistema
    private var messages: List<Mensaje> = emptyList()

    fun setData(list: List<Mensaje>){
        messages = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        //Se crean los elementos individuales del recycler view de acorde al contenido del mensaje
        return MessageViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.message_item,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        //Asignamos a cada elemento del renglón su correspondiente valor
        val message = messages[position]

        if(user == message.from){
            holder.itemView.findViewById<TextView>(R.id.tv_message).visibility = View.VISIBLE
            holder.itemView.findViewById<TextView>(R.id.tv_bot_message).visibility = View.GONE
            holder.itemView.findViewById<TextView>(R.id.tv_message).text = message.message
        }else {
            holder.itemView.findViewById<TextView>(R.id.tv_message).visibility = View.GONE
            holder.itemView.findViewById<TextView>(R.id.tv_bot_message).visibility = View.VISIBLE
            holder.itemView.findViewById<TextView>(R.id.tv_bot_message).text = message.message
        }
    }

    override fun getItemCount(): Int {
        //Obtenemos la cantidad de grupos de datos que estamos obteniendo del servidor
        return messages.size
    }

    class MessageViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
}