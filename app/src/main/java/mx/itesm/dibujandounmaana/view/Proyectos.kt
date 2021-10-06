package mx.itesm.dibujandounmaana.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import mx.itesm.dibujandounmaana.R
import mx.itesm.dibujandounmaana.databinding.NavProyectosBinding
import mx.itesm.dibujandounmaana.viewmodel.ProyectosVM

class Proyectos : Fragment() {

    companion object {
        fun newInstance() = Proyectos()
    }

    private lateinit var viewModel: ProyectosVM

    private lateinit var binding: NavProyectosBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //return inflater.inflate(R.layout.nav_proyectos, container, false)
        binding = NavProyectosBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //configurarObservadores()
        configurarEventos()
    }

    private fun configurarEventos() {
        binding.btnDonar.setOnClickListener{
            findNavController().navigate(R.id.action_nav_proyectos_to_donar)
        }
        binding.btnSumate.setOnClickListener{
            findNavController().navigate(R.id.action_nav_proyectos_to_sumate)
        }
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ProyectosVM::class.java)
        // TODO: Use the ViewModel
    }

}