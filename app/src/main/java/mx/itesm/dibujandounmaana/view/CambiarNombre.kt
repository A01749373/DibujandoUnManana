/*
Autores:
* Liam Garay Monroy
* Jorge Chávez Badillo
* Amy Murakami Tsutsumi
* Andrea Vianey Díaz Álvarez
* Ariadna Jocelyn Guzmán Jiménez
*/

package mx.itesm.dibujandounmaana.view

//Librerías
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import mx.itesm.dibujandounmaana.databinding.FragmentCambiarNombreBinding
import mx.itesm.dibujandounmaana.model.Datos
import mx.itesm.dibujandounmaana.viewmodel.ConfiguracionesVM


class cambiarNombre : Fragment() {

    companion object {
        fun newInstance() = Configuraciones()
    }

    private val viewModel: ConfiguracionesVM by viewModels()

    private lateinit var binding: FragmentCambiarNombreBinding

    override fun onCreateView(
        //Crea la vista de inicio de acuerdo al xml asignado
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCambiarNombreBinding.inflate(layoutInflater)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        /* Asegura que la vista esté completamente creada y manda a llamar a las funciones
        a los elementos correspondientes */
        super.onViewCreated(view, savedInstanceState)
        configurarObservadores()
        configurarEventos()
    }
    // Configuración del listener para que el usuario edite los datos de nombre y contraseña
    private fun configurarEventos() {
        //Configura las funciones de los botones correspondientes a la vista
        val preferencias = this.requireContext().getSharedPreferences("Usuario", Context.MODE_PRIVATE)
        val correo = preferencias.getString("Correo","-1")

        binding.btnEditarNombre.setOnClickListener {
            val etNombre  = binding.etNombre.text.toString()
            viewModel.editarnombre(Datos(correo.toString(),etNombre))
        }
        binding.btnEditarContrasena.setOnClickListener {
            val etContrasena  = binding.etNuevaContrasena.text.toString()
            viewModel.editarcontrasena(Datos(correo.toString(), etContrasena))
        }
    }

    private fun configurarObservadores(){
        //Evalúa la respuesta que se obtiene por el servidor durante el procedimiento
        viewModel.respuesta.observe(viewLifecycleOwner){ respuesta ->
            binding.tvConfirmacion.text = respuesta
        }
    }
}