package mx.itesm.dibujandounmaana.view

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import mx.itesm.dibujandounmaana.R
import mx.itesm.dibujandounmaana.databinding.NavSumateBinding
import mx.itesm.dibujandounmaana.model.NuevaPropuesta
import mx.itesm.dibujandounmaana.viewmodel.SumateVM

class Sumate : Fragment() {

    companion object {
        fun newInstance() = Sumate()
    }

    private val viewModel: SumateVM by viewModels()
    private lateinit var binding: NavSumateBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //return inflater.inflate(R.layout.nav_sumate, container, false)
        binding = NavSumateBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        configurarObservadores()
        configurarEventos()
    }

    private fun configurarEventos() {
        val preferencias = this.requireContext().getSharedPreferences("Usuario", Context.MODE_PRIVATE)
        val usuarioCorreo = preferencias.getString("Correo", "-1")
        binding.btnEnviarPropuesta.setOnClickListener {
            val nuevaPropuesta = NuevaPropuesta(
                binding.spinner.selectedItem.toString(),
                binding.etDescripcion.text.toString(),
                usuarioCorreo.toString()
            )
            viewModel.enviarPropuesta(nuevaPropuesta)
        }
    }

    private fun configurarObservadores() {
        viewModel.respuesta.observe(viewLifecycleOwner) { respuesta ->
            binding.tvEstad.text = respuesta
        }
    }

    /*
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(SumateVM::class.java)


    }*/

}