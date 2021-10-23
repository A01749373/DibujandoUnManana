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
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import mx.itesm.dibujandounmaana.R
import mx.itesm.dibujandounmaana.viewmodel.ComoAyudarVM

class ComoAyudar : Fragment() {

    companion object {
        fun newInstance() = ComoAyudar()
    }

    private lateinit var viewModel: ComoAyudarVM

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //Crea la vista de inicio de acuerdo al xml asignad
        return inflater.inflate(R.layout.nav_como_ayudar, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        //Inicializa la clase
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ComoAyudarVM::class.java)
    }

}