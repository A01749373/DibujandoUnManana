package mx.itesm.dibujandounmaana.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import mx.itesm.dibujandounmaana.Adaptadores.MessageAdapter
import mx.itesm.dibujandounmaana.databinding.NavMensajeBinding

class Mensaje : Fragment() {

    private var chatId= ""
    private var user = ""

    private lateinit var binding: NavMensajeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = NavMensajeBinding.inflate(layoutInflater)
        return binding.root
    }

    private fun initViews(){
        binding.mensajeRecyclerView.layoutManager = LinearLayoutManager(this.requireContext())
        binding.mensajeRecyclerView.adapter = MessageAdapter(user)

        binding.btnMensaje.setOnClickListener { sendMessage() }
    }

    private fun sendMessage(){
        binding.campoMensaje.setText("")
    }
}