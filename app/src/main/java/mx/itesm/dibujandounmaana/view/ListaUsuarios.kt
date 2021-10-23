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
import androidx.recyclerview.widget.LinearLayoutManager
import mx.itesm.dibujandounmaana.Adaptadores.AdaptadorListaUsuarios
import mx.itesm.dibujandounmaana.databinding.ListaUsuariosFragmentBinding
import mx.itesm.dibujandounmaana.viewmodel.ListaUsuariosVM


class ListaUsuarios : Fragment() {

    companion object {
        fun newInstance() = ListaUsuarios()
    }

    private val viewModel: ListaUsuariosVM by viewModels()

    private lateinit var binding: ListaUsuariosFragmentBinding

    private val adaptadorListaUsuarios = AdaptadorListaUsuarios(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //Crea la vista de acuerdo al xml asignado
        binding = ListaUsuariosFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        /* Asegura que la vista esté completamente creada y manda a llamar a las funciones
        a los elementos correspondientes */

        super.onViewCreated(view, savedInstanceState)

        configurarObservadores()
        configurarEventos()
        configurarRecyclerView()
    }

    private fun configurarRecyclerView() {
        //Configura el recycler view de acuerdo a los valores de entrada
        binding.rvListaUsuarios.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = adaptadorListaUsuarios
        }
    }

    private fun configurarEventos() {
        //Lee los datos recibidos del servidor
        viewModel.leerDatos()
    }

    private fun configurarObservadores() {
        //Lee las respuestas obtenidas del servidor
        viewModel.arrUsuarios.observe(viewLifecycleOwner){ Lista ->
            adaptadorListaUsuarios.actualizar(Lista)
        }
    }
}