package mx.itesm.dibujandounmaana.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import mx.itesm.dibujandounmaana.R
import mx.itesm.dibujandounmaana.model.SesionUsuario
import mx.itesm.dibujandounmaana.databinding.IniciarSesionFragmentBinding
import mx.itesm.dibujandounmaana.viewmodel.IniciarSesionVM
import kotlinx.coroutines.delay as delay

class IniciarSesion : Fragment() {

    private val viewModel: IniciarSesionVM by viewModels()

    private lateinit var binding: IniciarSesionFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //return inflater.inflate(R.layout.iniciar_sesion_fragment, container, false)
        binding = IniciarSesionFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configurarObservadores()
        configurarEventos()
    }

    private fun configurarEventos() {
        binding.btnIniciarSesion.setOnClickListener {
            val usuarioRegistrado = SesionUsuario(
                binding.etUsuario.text.toString(),
                binding.etContrasena.text.toString())

            viewModel.verificaUsuario(usuarioRegistrado)
            //delay(2000)
            findNavController().navigate(R.id.action_iniciarSesion_to_nav_quienes)
        }

        binding.btnCrearCuenta.setOnClickListener {
            findNavController().navigate(R.id.action_iniciarSesion_to_crear_cuenta)
        }
    }

    private fun configurarObservadores() {
        viewModel.respuesta.observe(viewLifecycleOwner) { respuesta ->
            binding.tvEstado.text = respuesta
        }
    }
}