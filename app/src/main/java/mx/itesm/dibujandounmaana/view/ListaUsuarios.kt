package mx.itesm.dibujandounmaana.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import mx.itesm.dibujandounmaana.Adaptadores.AdaptadorListaUsuarios
import mx.itesm.dibujandounmaana.databinding.ListaUsuariosFragmentBinding
import mx.itesm.dibujandounmaana.viewmodel.ListaUsuariosVM


class ListaUsuarios : Fragment() {

    companion object {
        fun newInstance() = ListaUsuarios()
    }

    private val viewModel: ListaUsuariosVM by viewModels()

    private lateinit var binding: ListaUsuariosFragmentBinding

    private val adaptadorListaUsuarios = AdaptadorListaUsuarios(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ListaUsuariosFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        configurarObservadores()
        configurarEventos()
        configurarRecyclerView()
    }

    private fun configurarRecyclerView() {
        binding.rvListaUsuarios.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = adaptadorListaUsuarios
        }
    }

    private fun configurarEventos() {

        viewModel.leerDatos() //Evento Boton
    }

    private fun configurarObservadores() {
        viewModel.arrUsuarios.observe(viewLifecycleOwner){ Lista ->
            adaptadorListaUsuarios.actualizar(Lista)
        }
    }
}