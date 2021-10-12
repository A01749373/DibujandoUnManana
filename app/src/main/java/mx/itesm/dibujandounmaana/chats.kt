package mx.itesm.dibujandounmaana


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import mx.itesm.dibujandounmaana.Adaptadores.ChatAdapter
import mx.itesm.dibujandounmaana.databinding.NavChatsBinding
import mx.itesm.dibujandounmaana.model.Chat
import mx.itesm.dibujandounmaana.view.Mensaje
import java.util.*


class chats : Fragment() {

    private lateinit var binding: NavChatsBinding

    private var user = "xmuse85@yahoo.com"
    private var db = Firebase.firestore

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = NavChatsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews(){
        binding.newChatButton.setOnClickListener { newChat() }

        binding.listaChats.layoutManager = LinearLayoutManager(this.requireContext())
        binding.listaChats.adapter =
            ChatAdapter { chat ->
                chatSelected(chat)
            }
        val userRef = db.collection("users").document(user)

        userRef.collection("chats")
            .get()
            .addOnSuccessListener { chats ->
                val listChats = chats.toObjects(Chat::class.java)

                (binding.listaChats.adapter as ChatAdapter).setData(listChats)
            }
        }

    private fun chatSelected(chat: Chat) {
        val intent = Intent(this.requireContext(), Mensaje::class.java)
        intent.putExtra("chatId", chat.id)
        intent.putExtra("user",user)
        startActivity(intent)
    }

    private fun newChat() {
        val chatId = UUID.randomUUID().toString()
        val otherUser = binding.inputChat.text.toString()
        val users = listOf(user, otherUser)

        val chat = Chat(
            id = chatId,
            name= "Chat con $otherUser",
            users = users
        )

        db.collection("chats").document(chatId).set(chat)
        db.collection("users").document(user).collection("chats").document(chatId).set(chat)
        db.collection("users").document(otherUser).collection("chats").document(chatId).set(chat)

        /*
        val intent = Intent(this.requireContext(), Mensaje::class.java)
        intent.putExtra("chatId",chatId)
        intent.putExtra("user", user)
        startActivity(intent)
         */
    }
}