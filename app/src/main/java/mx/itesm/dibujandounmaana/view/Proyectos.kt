package mx.itesm.dibujandounmaana.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import mx.itesm.dibujandounmaana.R
import mx.itesm.dibujandounmaana.viewmodel.ProyectosVM

class Proyectos : Fragment() {

    companion object {
        fun newInstance() = Proyectos()
    }

    private lateinit var viewModel: ProyectosVM

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.nav_proyectos, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ProyectosVM::class.java)
        // TODO: Use the ViewModel
    }

}