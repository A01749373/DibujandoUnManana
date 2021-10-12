package mx.itesm.dibujandounmaana.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import mx.itesm.dibujandounmaana.R
import mx.itesm.dibujandounmaana.databinding.NavContactanosFragmentBinding
import mx.itesm.dibujandounmaana.databinding.QuienesFragmentBinding
import mx.itesm.dibujandounmaana.viewmodel.ContactanosVM
import mx.itesm.dibujandounmaana.viewmodel.QuienesVM

class Quienes : Fragment() {

    private val viewModel: QuienesVM by viewModels()
    private lateinit var binding: QuienesFragmentBinding

    companion object {
        fun newInstance() = Quienes()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = QuienesFragmentBinding.inflate(layoutInflater)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //configurarObservadores()
        configurarEventos()
    }



    private fun configurarEventos(){
        binding.btnSumar.setOnClickListener {
            findNavController().navigate(R.id.action_nav_quienes_to_comoAyudar)
        }
    }



}
