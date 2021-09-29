package mx.itesm.dibujandounmaana

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class IniciarSesion : Fragment() {

    companion object {
        fun newInstance() = IniciarSesion()
    }

    private lateinit var viewModel: IniciarSesionVM

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.iniciar_sesion_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(IniciarSesionVM::class.java)
        // TODO: Use the ViewModel
    }

}