package mx.itesm.dibujandounmaana.view

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import mx.itesm.dibujandounmaana.Adaptadores.AdaptadosListaDonaciones
import mx.itesm.dibujandounmaana.viewmodel.ListaDonacionesVM
import mx.itesm.dibujandounmaana.databinding.ListaDonacionesFragmentBinding

class listaDonaciones : Fragment() {

    companion object {
        fun newInstance() = listaDonaciones()
    }

    private val viewModel: ListaDonacionesVM by viewModels()

    private lateinit var binding: ListaDonacionesFragmentBinding

    private val adaptadorListaDonaciones = AdaptadosListaDonaciones(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ListaDonacionesFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        configurarObservadores()
        configurarEventos()
        configurarRecyclerView()
    }

    private fun configurarRecyclerView() {
        binding.rvListaDonaciones.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = adaptadorListaDonaciones
        }
    }

    private fun configurarEventos() {
        val preferencias = this.requireContext().getSharedPreferences("Usuario", Context.MODE_PRIVATE)
        val correo = preferencias.getString("Correo", "-1")
        if (correo != "-1") {
            println("Este es el correo del usuario: $correo")
        } else {
            println("No funciono")
        }
        viewModel.leerDatos(correo.toString())  //Evento Boton
        //adaptadorListaDonaciones.listener = this  //Esta vista es el listener
    }

    private fun configurarObservadores() {
        viewModel.arrDonaciones.observe(viewLifecycleOwner){ Lista ->
            adaptadorListaDonaciones.actualizar(Lista)
        }
    }
}