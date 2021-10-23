package mx.itesm.dibujandounmaana.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.edit
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import mx.itesm.dibujandounmaana.R
import mx.itesm.dibujandounmaana.databinding.ConfiguracionesFragmentBinding
import mx.itesm.dibujandounmaana.viewmodel.ConfiguracionesVM


class Configuraciones : Fragment() {

    companion object {
        fun newInstance() = Configuraciones()
    }
    private val viewModel: ConfiguracionesVM by viewModels()


    private lateinit var binding: ConfiguracionesFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ConfiguracionesFragmentBinding.inflate(layoutInflater)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configurarEventos()
    }

    // Configuración del listener para los botones de la pantalla de configuración
    private fun configurarEventos() {
        val preferencias = this.requireContext().getSharedPreferences("Usuario", Context.MODE_PRIVATE)
        val correo = preferencias.getString("Correo","-1")

        binding.btnCerrarSesion.setOnClickListener {
            preferencias.edit {
                putString("Correo", "-1")
                commit()
            }
            val intIniciarSe = Intent(this.requireContext(), IniciarSesionAct::class.java)
            startActivity(intIniciarSe)
        }
        binding.btnBorrarCuenta.setOnClickListener {
            viewModel.borrarUsuario(correo.toString())
            preferencias.edit {
                putString("Correo", "-1")
                commit()
            }
            val intIniciarSe = Intent(this.requireContext(), tipoUsuario::class.java)
            startActivity(intIniciarSe)
        }
        binding.btnEditarPerfil.setOnClickListener {
            findNavController().navigate(R.id.action_configuraciones_to_cambiarNombre)
        }
    }

}