package mx.itesm.dibujandounmaana.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.setFragmentResultListener
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import mx.itesm.dibujandounmaana.Adaptadores.MessageAdapter
import mx.itesm.dibujandounmaana.databinding.NavMensajeBinding
import mx.itesm.dibujandounmaana.model.Mensaje

class Mensaje : Fragment() {

    private var chatId= ""
    private var user = ""

    private var db = Firebase.firestore

    private lateinit var binding: NavMensajeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = NavMensajeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        /*  Preguntar cómo llega
        setFragmentResultListener("ChatUsuario"){requestKey, bundle ->
        user = bundle.getString("usuario")!!
        chatId = bundle.getString("chatId")!!}
        println("si estoy $user")
        initViews()
         */
    }

    private fun initViews(){
        binding.mensajeRecyclerView.layoutManager = LinearLayoutManager(this.requireContext())
        binding.mensajeRecyclerView.adapter = MessageAdapter(user)

        println("Hola soy el chatId: $chatId")

        binding.btnMensaje.setOnClickListener { sendMessage() }

        val chatRef = db.collection("chats").document(chatId)

        println("si llegue 2")

        chatRef.collection("messages").orderBy("dob", Query.Direction.ASCENDING)
            .get()
            .addOnSuccessListener { messages ->
                val listMessages = messages.toObjects(Mensaje::class.java)
                (binding.mensajeRecyclerView.adapter as MessageAdapter).setData(listMessages)
            }

        chatRef.collection("messages").orderBy("dob", Query.Direction.ASCENDING)
            .addSnapshotListener { messages, error ->
                if(error == null){
                    messages?.let {
                        val listMessages = it.toObjects(Mensaje::class.java)
                        (binding.mensajeRecyclerView.adapter as MessageAdapter).setData(listMessages)
                    }
                }
            }
        println("si llegue 3")
    }

    private fun sendMessage(){
        val message = Mensaje(
            message = binding.campoMensaje.text.toString(),
            from = user
        )

        db.collection("chats").document(chatId).collection("messages").document().set(message)

        binding.campoMensaje.setText("")

    }
}