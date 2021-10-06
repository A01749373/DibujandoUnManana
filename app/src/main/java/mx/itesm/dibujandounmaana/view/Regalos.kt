package mx.itesm.dibujandounmaana.view

import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import mx.itesm.dibujandounmaana.databinding.FragmentRegalosBinding
import mx.itesm.dibujandounmaana.viewmodel.RegalosVM

class Regalos : Fragment() {

    private val viewModel: RegalosVM by viewModels()
    private lateinit var binding: FragmentRegalosBinding
    companion object{
        fun newInstance() = Regalos()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentRegalosBinding.inflate(layoutInflater)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //configurarObservadores()
        configurarEventos()
    }
    private fun configurarEventos(){
        val link = binding.visita
        link.setMovementMethod(LinkMovementMethod.getInstance());
    }



}