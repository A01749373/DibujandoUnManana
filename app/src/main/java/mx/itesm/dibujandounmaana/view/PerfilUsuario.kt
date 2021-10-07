package mx.itesm.dibujandounmaana.view

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.edit
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import mx.itesm.dibujandounmaana.databinding.PerfilUsuarioFragmentBinding
import mx.itesm.dibujandounmaana.viewmodel.PerfilUsuarioVM
import mx.itesm.dibujandounmaana.R

class perfilUsuario : Fragment() {

    companion object{
        fun newInstance() = perfilUsuario()
    }

    private val viewModel: PerfilUsuarioVM by viewModels()

    private lateinit var binding: PerfilUsuarioFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = PerfilUsuarioFragmentBinding.inflate(layoutInflater)
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

    private fun configurarEventos() {
        binding.tvBoton.setOnClickListener {
            val correo = binding.tvCorreo.text.toString()
            val preferencias = this.requireContext().getSharedPreferences("Usuario", Context.MODE_PRIVATE)
            preferencias.edit {
                putString("Correo", correo)
                commit()
            }
            viewModel.descargarDatosUsuario(correo)

            // Ver Datos de preferencias
            val favorito = preferencias.getString("Correo", "-1")
            if (favorito != "-1") {
                println("Este es el correo del usuario: $favorito")
            } else {
                println("No funciono")
            }
        }
        binding.btnDonacion.setOnClickListener {
            findNavController().navigate(R.id.action_nav_perfil_to_lista_donaciones)
        }
    }

    private fun configurarObservadores() {
        viewModel.respuesta.observe(viewLifecycleOwner) { respuesta ->
            binding.tvNombre.text = respuesta.nombre
        }
    }
}