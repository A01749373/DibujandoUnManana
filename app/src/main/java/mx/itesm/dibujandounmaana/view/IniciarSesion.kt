package mx.itesm.dibujandounmaana.view

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.edit
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import mx.itesm.dibujandounmaana.R
import mx.itesm.dibujandounmaana.model.SesionUsuario
import mx.itesm.dibujandounmaana.databinding.IniciarSesionFragmentBinding
import mx.itesm.dibujandounmaana.viewmodel.IniciarSesionVM
import android.widget.Toast.makeText
import kotlinx.coroutines.delay as delay

class IniciarSesion : Fragment() {

    private val viewModel: IniciarSesionVM by viewModels()

    private lateinit var binding: IniciarSesionFragmentBinding

    private var anuncioRespuesta: String = ""

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
        cargarPreferencias()
        configurarObservadores()
        configurarEventos()
    }

    private fun cargarPreferencias() {
        val preferencias = this.requireContext().getSharedPreferences("Usuario", Context.MODE_PRIVATE)
        val favorito = preferencias.getString("Correo", "-1")
        if (favorito != "-1") {
            println("$favorito")
        } else {
            println("No funciono")
        }
    }

    //if (binding.etUsuario.text.toString().isNotEmpty() && binding.etContrasena.text.isNotEmpty()) {
    private fun configurarEventos() {
        binding.btnIniciarSesion.setOnClickListener {
            val usuarioRegistrado = SesionUsuario(
                binding.etUsuario.text.toString(),
                binding.etContrasena.text.toString()
            )
            val preferencias =
                this.requireContext().getSharedPreferences("Usuario", Context.MODE_PRIVATE)
            preferencias.edit {
                putString("Correo", binding.etUsuario.text.toString())
                commit()
            }
            // Ver Datos de preferencias
            val favorito = preferencias.getString("Correo", "-1")
            if (favorito != "-1") {
                println("Este es el correo del usuario: $favorito")
            } else {
                println("No funciono")
            }
            viewModel.verificaUsuario(usuarioRegistrado)
            if (viewModel.respuesta.value == "Lo sentimos: Usuario o contraseña no válidos") {
                //Toast.makeText(activity, "${viewModel.respuesta.value}" + " 😭", Toast.LENGTH_SHORT).show()
                Toast.makeText(activity, anuncioRespuesta + " 😭", Toast.LENGTH_SHORT).show()
                //println(viewModel.respuesta.value)
            } else {
                //Toast.makeText(activity, "${viewModel.respuesta.value}" + " 😃", Toast.LENGTH_SHORT).show()
                Toast.makeText(activity, anuncioRespuesta + " 😃", Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_iniciarSesion_to_nav_quienes)
            }
        }
        binding.btnCrearCuenta.setOnClickListener {
            findNavController().navigate(R.id.action_iniciarSesion_to_crear_cuenta)
        }
    }

    private fun configurarObservadores() {
        viewModel.respuesta.observe(viewLifecycleOwner) { respuesta ->
            binding.tvEstado.text = respuesta
            anuncioRespuesta = respuesta.toString()
        }
    }
}