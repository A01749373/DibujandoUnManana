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
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import mx.itesm.dibujandounmaana.Adaptadores.AdaptadorListaProyectos
import mx.itesm.dibujandounmaana.R
import mx.itesm.dibujandounmaana.databinding.NavProyectosBinding
import mx.itesm.dibujandounmaana.viewmodel.ProyectosVM

class Proyectos : Fragment() {

    companion object {
        fun newInstance() = Proyectos()
    }

    private val viewModel: ProyectosVM by viewModels()

    private lateinit var binding: NavProyectosBinding

    private val adaptadorListaProyectos = AdaptadorListaProyectos(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //Crea la vista de acuerdo al xml asignado
        binding = NavProyectosBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        /* Asegura que la vista esté completamente creada y manda a llamar a las funciones
        a los elementos correspondientes */
        super.onViewCreated(view, savedInstanceState)
        configurarObservadores()
        configurarEventos()
        configruarRecyclerView()
    }

    private fun configruarRecyclerView() {
        binding.rvListaProyectos.apply{
            layoutManager = LinearLayoutManager(context)
            adapter = adaptadorListaProyectos
        }
    }

    private fun configurarEventos() {
        //Configura las acciones de los botones que se encuentran en la pantalla
        binding.btnDonar.setOnClickListener{
            findNavController().navigate(R.id.action_nav_proyectos_to_donar)
        }
        binding.btnSumate.setOnClickListener{
            findNavController().navigate(R.id.action_nav_proyectos_to_sumate)
        }
        viewModel.leerDatos()
    }

    private fun configurarObservadores() {
        //Observa las respuestas del servidor
        viewModel.arrProyectos.observe(viewLifecycleOwner){Lista ->
            adaptadorListaProyectos.actualizar(Lista)
        }
    }

}