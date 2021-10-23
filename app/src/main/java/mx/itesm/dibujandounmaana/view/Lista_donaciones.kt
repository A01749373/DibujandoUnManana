/*
Autores:
* Liam Garay Monroy
* Jorge Chávez Badillo
* Amy Murakami Tsutsumi
* Andrea Vianey Díaz Álvarez
* Ariadna Jocelyn Guzmán Jiménez
*/


package mx.itesm.dibujandounmaana.view

//Liberías
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import mx.itesm.dibujandounmaana.Adaptadores.AdaptadosListaDonaciones
import mx.itesm.dibujandounmaana.viewmodel.ListaDonacionesVM
import mx.itesm.dibujandounmaana.databinding.ListaDonacionesFragmentBinding

class listaDonaciones : Fragment() {

    companion object {
        fun newInstance() = listaDonaciones()
    }

    private val viewModel: ListaDonacionesVM by viewModels()

    private lateinit var binding: ListaDonacionesFragmentBinding

    private val adaptadorListaDonaciones = AdaptadosListaDonaciones(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //Crea la vista de acuerdo al xml asignado
        binding = ListaDonacionesFragmentBinding.inflate(layoutInflater)
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
        binding.rvListaDonaciones.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = adaptadorListaDonaciones
        }
    }

    private fun configurarEventos() {
        //Lee las preferencias del usuario, así como sus datos
        val preferencias = this.requireContext().getSharedPreferences("Usuario", Context.MODE_PRIVATE)
        val correo = preferencias.getString("Correo", "-1")
        if (correo != "-1") {
            println("Este es el correo del usuario: $correo")
        } else {
            println("No funciono")
        }
        viewModel.leerDatos(correo.toString())  //Evento Boton
        //adaptadorListaDonaciones.listener = this  //Esta vista es el listener
    }

    private fun configurarObservadores() {
        //Observa las respuestas del servidor
        viewModel.arrDonaciones.observe(viewLifecycleOwner){ Lista ->
            adaptadorListaDonaciones.actualizar(Lista)
        }
    }
}