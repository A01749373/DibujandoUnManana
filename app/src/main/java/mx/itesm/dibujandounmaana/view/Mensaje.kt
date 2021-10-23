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
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.fragment.navArgs
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

    //Recibir el dato de la primer pantalla
    val args: MensajeArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //Crea la vista de acuerdo al xml asignado
        binding = NavMensajeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        /* Asegura que la vista esté completamente creada y manda a llamar a las funciones
        a los elementos correspondientes */
        super.onViewCreated(view, savedInstanceState)
        chatId = args.chatId
        user = args.user
        initViews()
    }

    private fun initViews(){
        //Adapta el recycler view de acuerdo al número de chats que tiene el usuario
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
        //Manda el mensaje a otro usuario
        val message = Mensaje(
            message = binding.campoMensaje.text.toString(),
            from = user
        )

        db.collection("chats").document(chatId).collection("messages").document().set(message)

        binding.campoMensaje.setText("")

    }
}