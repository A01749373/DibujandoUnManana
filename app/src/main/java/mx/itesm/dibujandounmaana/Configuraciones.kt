package mx.itesm.dibujandounmaana

import android.content.Context
import android.graphics.BitmapFactory
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.edit
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.firebase.storage.FirebaseStorage
import mx.itesm.dibujandounmaana.databinding.ConfiguracionesFragmentBinding
import mx.itesm.dibujandounmaana.databinding.PerfilUsuarioFragmentBinding
import mx.itesm.dibujandounmaana.viewmodel.ConfiguracionesVM
import java.io.File

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

    private fun configurarEventos() {
        val preferencias = this.requireContext().getSharedPreferences("Usuario", Context.MODE_PRIVATE)
        val correo = preferencias.getString("Correo","-1")

        binding.btnCerrarSesion.setOnClickListener {
            preferencias.edit {
                putString("Correo", "-1")
                commit()
            }
            findNavController().navigate(R.id.action_configuraciones_to_iniciarSesion)
        }
        binding.btnBorrarCuenta.setOnClickListener {
            viewModel.borrarUsuario(correo.toString())
            preferencias.edit {
                putString("Correo", "-1")
                commit()
            }
            findNavController().navigate(R.id.action_configuraciones_to_iniciarSesion)
        }
        /*binding.btnCambiarContraseA.setOnClickListener {

        }
        binding.btnCambiarNombre.setOnClickListener {

        }*/
    }

}