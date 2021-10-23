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
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import mx.itesm.dibujandounmaana.databinding.FragmentRegalosBinding
import mx.itesm.dibujandounmaana.viewmodel.RegalosVM

class Regalos : Fragment() {

    private val viewModel: RegalosVM by viewModels()
    private lateinit var binding: FragmentRegalosBinding
    companion object{
        fun newInstance() = Regalos()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //Crea la vista de acuerdo al xml asignado
        binding= FragmentRegalosBinding.inflate(layoutInflater)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        /* Asegura que la vista esté completamente creada y manda a llamar a las funciones
        a los elementos correspondientes */
        super.onViewCreated(view, savedInstanceState)
        configurarEventos()
    }
    private fun configurarEventos(){
        //Configura el link de la pantalla para mandar al navegador con la página de la fundación
        val link = binding.visita
        link.setMovementMethod(LinkMovementMethod.getInstance());
    }



}