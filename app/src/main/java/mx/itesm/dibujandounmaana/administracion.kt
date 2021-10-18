package mx.itesm.dibujandounmaana

import android.content.Context
import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.edit
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import mx.itesm.dibujandounmaana.databinding.ActivityMainBinding
import mx.itesm.dibujandounmaana.databinding.AdministracionFragmentBinding

class administracion : Fragment() {

    companion object {
        fun newInstance() = administracion()
    }

    private val viewModel: AdministracionVM by viewModels()

    private lateinit var binding: AdministracionFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = AdministracionFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configurarEventos()
    }

    private fun configurarEventos() {
        val preferencias = this.requireContext().getSharedPreferences("Usuario", Context.MODE_PRIVATE)
        val correo = preferencias.getString("Correo","-1")

        binding.btnAgregarUsuario.setOnClickListener {
            val intentCrearCuentaAdmin = Intent(this.requireContext(), CrearCuentaAdminAct::class.java)
            startActivity(intentCrearCuentaAdmin)
        }

        binding.btnAgregarProyecto.setOnClickListener {

        }

        binding.btnListaUsuario.setOnClickListener {

        }
    }
}