package mx.itesm.dibujandounmaana.view

import android.content.Intent
import android.net.Uri
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import mx.itesm.dibujandounmaana.R
import mx.itesm.dibujandounmaana.databinding.IniciarSesionFragmentBinding
import mx.itesm.dibujandounmaana.databinding.TipoUsuarioFragmentBinding
import mx.itesm.dibujandounmaana.viewmodel.IniciarSesionVM
import mx.itesm.dibujandounmaana.viewmodel.TipoUsuarioVM

class TipoUsuario : Fragment() {

    private val viewModel: TipoUsuarioVM by viewModels()

    private lateinit var binding: TipoUsuarioFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //return inflater.inflate(R.layout.iniciar_sesion_fragment, container, false)
        binding = TipoUsuarioFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configurarObservadores()
        configurarEventos()
    }

    private fun configurarEventos() {
        binding.btnTipoDonante.setOnClickListener {
            findNavController().navigate(R.id.action_tipoUsuario_to_iniciarSesion)
        }
        binding.btnTipoEmpresa.setOnClickListener {
            val urlEmpresa = "https://www.dibujando.org.mx/en/enterprises/"
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(urlEmpresa)))
        }
        binding.btnTipoAdministrador.setOnClickListener {
            findNavController().navigate((R.id.action_tipoUsuario_to_iniciarSesion))
        }
    }

    private fun configurarObservadores() {
        viewModel.respuesta.observe(viewLifecycleOwner) { respuesta ->
            //binding.tvEstado.text = respuesta
        }
    }

}