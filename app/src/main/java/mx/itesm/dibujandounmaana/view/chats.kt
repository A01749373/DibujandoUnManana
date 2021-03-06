/*
Autores:
* Liam Garay Monroy
* Jorge Chávez Badillo
* Amy Murakami Tsutsumi
* Andrea Vianey Díaz Álvarez
* Ariadna Jocelyn Guzmán Jiménez
*/


package mx.itesm.dibujandounmaana.view

//Librerías
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.FirebaseApp
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import mx.itesm.dibujandounmaana.Adaptadores.ChatAdapter
import mx.itesm.dibujandounmaana.databinding.NavChatsBinding
import mx.itesm.dibujandounmaana.model.Chat
import mx.itesm.dibujandounmaana.viewmodel.ChatsVM
import java.util.*


class chats : Fragment() {

    companion object {
        fun newInstance() = chats()
    }
    private val viewModel: ChatsVM by viewModels()

    private lateinit var binding: NavChatsBinding

    private var nombre: String = ""
    private var user: String = ""
    private lateinit var db: FirebaseFirestore

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //Crea la vista de inicio de acuerdo al xml asignado
        binding = NavChatsBinding.inflate(layoutInflater)
        FirebaseApp.initializeApp(this.requireContext())
        db = Firebase.firestore
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val preferencias = this.requireContext().getSharedPreferences("Usuario", Context.MODE_PRIVATE)
        val correo = preferencias.getString("Correo","-1")
        user = correo!!
        initViews()
    }

    private fun initViews() {
        binding.newChatButton.setOnClickListener { configurarObservadores() }

        binding.listaChats.layoutManager = LinearLayoutManager(this.requireContext())
        binding.listaChats.adapter =
            ChatAdapter { chat ->
                chatSelected(chat)
            }
        val userRef = db.collection("users").document(user)

        //Actualiza los chats cuando se inicia con el usuario
        userRef.collection("chats")
            .get()
            .addOnSuccessListener { chats ->
                val listChats = chats.toObjects(Chat::class.java)

                (binding.listaChats.adapter as ChatAdapter).setData(listChats)
            }
        userRef.collection("chats")
            .addSnapshotListener { chats, error ->
                if (error == null) {
                    chats?.let {
                        val listChats = it.toObjects(Chat::class.java)
                        (binding.listaChats.adapter as ChatAdapter).setData(listChats)
                    }
                }
            }
    }

    private fun chatSelected(chat: Chat) {
        //Manda al usuario a la pantalla de conversasión con el otro usuario
        val accion = chatsDirections.actionChatsToMensaje(chat.id,user)
        findNavController().navigate(accion)
    }

    private fun configurarObservadores() {
        //Verifica los componentes del chat y asigna valores de acuerdo a el comportamiento del usuario
        val otherUser = binding.inputChat.text.toString()
        viewModel.descargarDatosUsuario(otherUser)
        viewModel.respuesta.observe(viewLifecycleOwner) { respuesta ->
            nombre = respuesta.nombre
        }
        val chatId = UUID.randomUUID().toString()
        val users = listOf(user, otherUser)
        val chat = Chat(
            id = chatId,
            name= nombre,
            users = users
        )

        db.collection("chats").document(chatId).set(chat)
        db.collection("users").document(user).collection("chats").document(chatId).set(chat)
        db.collection("users").document(otherUser).collection("chats").document(chatId).set(chat)


        val accion = chatsDirections.actionChatsToMensaje(chatId,user)
        findNavController().navigate(accion)
    }
}