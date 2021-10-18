package mx.itesm.dibujandounmaana.view

import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import mx.itesm.dibujandounmaana.databinding.AgregarProyectoFragmentBinding
import mx.itesm.dibujandounmaana.databinding.FragmentRegalosBinding
import mx.itesm.dibujandounmaana.model.Proyecto
import mx.itesm.dibujandounmaana.model.Proyectos
import mx.itesm.dibujandounmaana.viewmodel.AgregarProyectoVM
import mx.itesm.dibujandounmaana.viewmodel.RegalosVM

class AgregarProyecto: Fragment() {
    private val viewModel: AgregarProyectoVM by viewModels()
    private lateinit var binding: AgregarProyectoFragmentBinding
    companion object{
        fun newInstance() = AgregarProyecto()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= AgregarProyectoFragmentBinding.inflate(layoutInflater)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //configurarObservadores()
        configurarEventos()
    }

    private fun configurarEventos(){
        binding.btnAgregar.setOnClickListener{
            val nombre = binding.etNombreProyecto.text.toString()
            val tipo = binding.etTipoProyecto.text.toString()
            val descripcion = binding.etDescripcion.text.toString()

            val proyecto = Proyectos(nombre,tipo,descripcion)

            viewModel.enviarProyecto(proyecto)
        }
    }


}
