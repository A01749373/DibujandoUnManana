package mx.itesm.dibujandounmaana.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import mx.itesm.dibujandounmaana.R
import mx.itesm.dibujandounmaana.databinding.ActivityMainBinding
import mx.itesm.dibujandounmaana.databinding.IniciarSesionFragmentBinding
import mx.itesm.dibujandounmaana.databinding.PerfilUsuarioFragmentBinding
import mx.itesm.dibujandounmaana.model.CorreoUsuario
import mx.itesm.dibujandounmaana.model.SesionUsuario
import mx.itesm.dibujandounmaana.viewmodel.PerfilUsuarioVM

class perfilUsuario : Fragment() {


    private val viewModel: PerfilUsuarioVM by viewModels()

    private lateinit var binding: PerfilUsuarioFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //return inflater.inflate(R.layout.iniciar_sesion_fragment, container, false)
        binding = PerfilUsuarioFragmentBinding.inflate(layoutInflater)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //configurarObservadores()
        configurarEventos()
    }
    private fun configurarEventos() {
        binding.tvBoton.setOnClickListener {
            val correo = binding.tvCorreo.text.toString()
            viewModel.descargarDatosUsuario(correo)
        }
    }

    //private fun configurarObservadores() {
    //    viewModel.respuesta.observe(viewLifecycleOwner) { respuesta ->
    //        binding.tvNombre.text =
    //    }
    //}

}