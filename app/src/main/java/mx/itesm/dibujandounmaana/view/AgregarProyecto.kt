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
import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import mx.itesm.dibujandounmaana.databinding.AgregarProyectoFragmentBinding
import mx.itesm.dibujandounmaana.databinding.FragmentRegalosBinding
import mx.itesm.dibujandounmaana.model.Proyecto
import mx.itesm.dibujandounmaana.model.Proyectos
import mx.itesm.dibujandounmaana.viewmodel.AgregarProyectoVM
import mx.itesm.dibujandounmaana.viewmodel.RegalosVM

class AgregarProyecto: Fragment() {
    private val viewModel: AgregarProyectoVM by viewModels()
    private lateinit var binding: AgregarProyectoFragmentBinding
    companion object{
        fun newInstance() = AgregarProyecto()
    }

    override fun onCreateView(
        //Crea la vista de inicio de acuerdo al xml asignado
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= AgregarProyectoFragmentBinding.inflate(layoutInflater)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        /* Asegura que la vista esté completamente creada y manda a llamar a las funciones
        a los elementos correspondientes */
        super.onViewCreated(view, savedInstanceState)

        configurarEventos()
    }

    private fun configurarEventos(){
        //Configura las funciones de los botones correspondientes a la vista
        binding.btnAgregar.setOnClickListener{
            val nombre = binding.etNombreProyecto.text.toString()
            val tipo = binding.etTipoProyecto.text.toString()
            val descripcion = binding.etDescripcion.text.toString()

            val proyecto = Proyectos(nombre,tipo,descripcion)

            viewModel.enviarProyecto(proyecto)
        }
    }


}
