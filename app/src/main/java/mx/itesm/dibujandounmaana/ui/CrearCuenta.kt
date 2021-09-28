package mx.itesm.dibujandounmaana.ui

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import mx.itesm.dibujandounmaana.R

class CrearCuenta: Fragment() {
    companion object {
        fun newInstance() = CrearCuenta()
    }

    private lateinit var viewModel: CrearCuentaVM

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.nav_crear_cuenta, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CrearCuentaVM::class.java)
        // TODO: Use the ViewModel
    }
}