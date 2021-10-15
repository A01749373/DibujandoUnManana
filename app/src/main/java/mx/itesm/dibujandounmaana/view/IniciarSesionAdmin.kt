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
import mx.itesm.dibujandounmaana.databinding.IniciarSesionAdminBinding
import mx.itesm.dibujandounmaana.viewmodel.IniciarSesionAdminVM
import kotlinx.coroutines.delay as delay

class IniciarSesionAdmin : Fragment() {

    private val viewModel: IniciarSesionAdminVM by viewModels()

    private lateinit var binding: IniciarSesionAdminBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = IniciarSesionAdminBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        cargarPreferencias()
        configurarObservadores()
        configurarEventos()
    }

    private fun cargarPreferencias() {
        val preferencias =
            this.requireContext().getSharedPreferences("Usuario", Context.MODE_PRIVATE)
        val favorito = preferencias.getString("Correo", "-1")
        if (favorito != "-1") {
            println("$favorito")
        } else {
            println("No funciono")
        }
    }

    private fun cambiarPantalla(respuesta: String) {
        if (respuesta == "Lo sentimos: Usuario o contraseña no válidos") {
            Toast.makeText(activity, respuesta + " 😭", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(activity, respuesta + " 😃", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_iniciar_sesion_admin_to_nav_quienes)
        }
    }

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
        }
        binding.btnCrearCuenta.setOnClickListener {
            findNavController().navigate(R.id.action_iniciar_sesion_admin_to_crear_cuenta_admin)
        }
    }

    private fun configurarObservadores() {
        viewModel.respuesta.observe(viewLifecycleOwner) { respuesta ->
            binding.tvEstado.text = respuesta
            cambiarPantalla(respuesta)
        }
    }
}