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
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import mx.itesm.dibujandounmaana.R
import mx.itesm.dibujandounmaana.databinding.AdministracionFragmentBinding


class administracion : Fragment() {

    companion object {
        fun newInstance() = administracion()
    }

    private lateinit var binding: AdministracionFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //Crea la vista de inicio de acuerdo al xml asignado
        binding = AdministracionFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        /* Asegura que la vista esté completamente creada y manda a llamar a las funciones
        a los elementos correspondientes */
        super.onViewCreated(view, savedInstanceState)
        configurarEventos()
    }

    // Se configuran los listener de cada botón para la navegación de pantallas cuando el usuario
    // es de tipo administrador
    private fun configurarEventos() {
        //Configura las funciones de los botones correspondientes a la vista
        val preferencias = this.requireContext().getSharedPreferences("Usuario", Context.MODE_PRIVATE)
        val correo = preferencias.getString("Correo","-1")

        binding.btnAgregarUsuario.setOnClickListener {
            val intentCrearCuentaAdmin = Intent(this.requireContext(), CrearCuentaAdminAct::class.java)
            startActivity(intentCrearCuentaAdmin)
        }

        binding.btnAgregarProyecto.setOnClickListener {
            findNavController().navigate(R.id.action_administracion_to_agregarProyecto)

        }

        binding.btnListaUsuario.setOnClickListener {
            findNavController().navigate(R.id.action_administracion_to_listaUsuarios)
        }

        binding.btnListaPropuestas.setOnClickListener {
            findNavController().navigate(R.id.action_administracion_to_listaPropuestas)
        }
    }
}