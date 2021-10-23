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
import mx.itesm.dibujandounmaana.databinding.ListaPropuestasFragmentBinding
import mx.itesm.dibujandounmaana.Adaptadores.AdaptadorListaPropuestas
import mx.itesm.dibujandounmaana.viewmodel.ListaPropuestasVM


class ListaPropuestas : Fragment() {
    companion object {
        fun newInstance() = ListaPropuestas()
    }

    private val viewModel: ListaPropuestasVM by viewModels()

    private lateinit var binding: ListaPropuestasFragmentBinding

    private val adaptadorListaPropuestas = AdaptadorListaPropuestas(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //Crea la vista de acuerlo al xml asignado
        binding = ListaPropuestasFragmentBinding.inflate(layoutInflater)
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
        binding.rvListaPropuestas.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = adaptadorListaPropuestas
        }
    }

    private fun configurarEventos() {
        //Lee los datos que se reciben del servidor
        viewModel.leerDatos()
    }

    private fun configurarObservadores() {
        //Observa las respuestas del servidor
        viewModel.arrPropuestas.observe(viewLifecycleOwner){ Lista ->
            adaptadorListaPropuestas.actualizar(Lista)
        }
    }
}