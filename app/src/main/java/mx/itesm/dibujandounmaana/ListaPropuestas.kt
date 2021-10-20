package mx.itesm.dibujandounmaana

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import mx.itesm.dibujandounmaana.databinding.ListaPropuestasFragmentBinding
import mx.itesm.dibujandounmaana.view.AdaptadorListaPropuestas


class ListaPropuestas : Fragment() {
    companion object {
        fun newInstance() = ListaPropuestas()
    }

    private val viewModel: ListaPropuestasVM by viewModels()

    private lateinit var binding: ListaPropuestasFragmentBinding

    private val adaptadorListaPropuestas = AdaptadorListaPropuestas(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ListaPropuestasFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        configurarObservadores()
        configurarEventos()
        configurarRecyclerView()
    }

    private fun configurarRecyclerView() {
        binding.rvListaPropuestas.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = adaptadorListaPropuestas
        }
    }

    private fun configurarEventos() {

        viewModel.leerDatos() //Evento Boton
    }

    private fun configurarObservadores() {
        viewModel.arrPropuestas.observe(viewLifecycleOwner){ Lista ->
            adaptadorListaPropuestas.actualizar(Lista)
        }
    }
}