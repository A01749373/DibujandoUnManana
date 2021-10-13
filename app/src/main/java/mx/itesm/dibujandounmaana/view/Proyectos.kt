package mx.itesm.dibujandounmaana.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import mx.itesm.dibujandounmaana.R
import mx.itesm.dibujandounmaana.databinding.NavProyectosBinding
import mx.itesm.dibujandounmaana.viewmodel.ProyectosVM

class Proyectos : Fragment() {

    companion object {
        fun newInstance() = Proyectos()
    }

    private val viewModel: ProyectosVM by viewModels()

    private lateinit var binding: NavProyectosBinding

    private val adaptadorListaProyectos = AdaptadorListaProyectos(arrayListOf())

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
        configurarObservadores()
        configurarEventos()
        configruarRecyclerView()
    }

    private fun configruarRecyclerView() {
        binding.rvListaProyectos.apply{
            layoutManager = LinearLayoutManager(context)
            adapter = adaptadorListaProyectos
        }
    }

    private fun configurarEventos() {
        binding.btnDonar.setOnClickListener{
            findNavController().navigate(R.id.action_nav_proyectos_to_donar)
        }
        binding.btnSumate.setOnClickListener{
            findNavController().navigate(R.id.action_nav_proyectos_to_sumate)
        }
        viewModel.leerDatos()
    }

    private fun configurarObservadores() {
        viewModel.arrProyectos.observe(viewLifecycleOwner){Lista ->
            adaptadorListaProyectos.actualizar(Lista)
        }
    }
    /*
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ProyectosVM::class.java)
        // TODO: Use the ViewModel
    }
    
     */

}