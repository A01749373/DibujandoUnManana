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
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import mx.itesm.dibujandounmaana.R
import mx.itesm.dibujandounmaana.databinding.QuienesFragmentBinding

class Quienes : Fragment() {

    private lateinit var binding: QuienesFragmentBinding

    companion object {
        fun newInstance() = Quienes()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //Crea la vista de acuerdo al xml asignado
        binding = QuienesFragmentBinding.inflate(layoutInflater)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        /* Asegura que la vista esté completamente creada y manda a llamar a las funciones
        a los elementos correspondientes */
        super.onViewCreated(view, savedInstanceState)

        configurarEventos()
    }



    private fun configurarEventos(){
        //Configura el botón de la pantalla para mandar a otro fragmento
        binding.btnSumar.setOnClickListener {
            findNavController().navigate(R.id.action_nav_quienes_to_comoAyudar)
        }
    }



}
