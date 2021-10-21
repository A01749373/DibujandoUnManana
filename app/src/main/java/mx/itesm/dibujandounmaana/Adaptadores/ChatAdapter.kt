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
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.storage.FirebaseStorage
import mx.itesm.dibujandounmaana.R
import mx.itesm.dibujandounmaana.model.Chat
import java.io.File

class ChatAdapter(val chatClick: (Chat)-> Unit): RecyclerView.Adapter<ChatAdapter.ChatViewHolder>(){
   //Creamos una lista con elementos "chat" que se actualizará conforme se recuban datos del sistema
    var chats: List<Chat> = emptyList()

    fun setData(list: List<Chat>){
        chats = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
        //Se crean los elementos individuales del recycler view de acorde al contenido de la bandeja de entrada
        return ChatViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.chat_item,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        //Asignamos a cada elemento del renglón su correspondiente valor
        holder.itemView.findViewById<TextView>(R.id.chatNameText).text = chats[position].name
        holder.itemView.findViewById<TextView>(R.id.usersTextView).text = chats[position].users[1]
        val correo = chats[position].users[1]
        val storageRef = FirebaseStorage.getInstance().reference.child("Imagenes/$correo")
        val archivo = File.createTempFile("temp","jpg")
        storageRef.getFile(archivo).addOnSuccessListener {
            val bitmap = BitmapFactory.decodeFile(archivo.absolutePath)
            holder.itemView.findViewById<ImageView>(R.id.imageUsuario).setImageBitmap(bitmap)
        }
        holder.itemView.setOnClickListener {
            chatClick(chats[position])
        }
    }

    override fun getItemCount(): Int {
        //Obtenemos la cantidad de grupos de datos que estamos obteniendo del servidor
        return chats.size
    }
    class ChatViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
}