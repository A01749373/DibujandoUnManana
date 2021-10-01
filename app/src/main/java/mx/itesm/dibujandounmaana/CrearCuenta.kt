package mx.itesm.dibujandounmaana

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import mx.itesm.dibujandounmaana.databinding.NavCrearCuentaBinding

class CrearCuenta: Fragment() {

    private val viewModel: CrearCuentaVM by viewModels()

    private lateinit var binding: NavCrearCuentaBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = NavCrearCuentaBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        configurarObservadores()
        configurarEventos()
    }

    private fun configurarEventos() {
        binding.btnEnviar.setOnClickListener {
            val nuevoUsuario = Usuario(binding.etCorreo.text.toString(),
                binding.etNombreUsuario.text.toString(),
                binding.etContrsena.text.toString())

            viewModel.enviarUsuario(nuevoUsuario)
        }
    }

    private fun configurarObservadores() {
        viewModel.respuesta.observe(viewLifecycleOwner) { respuesta ->
            binding.tvEstado.text = respuesta
        }
    }
}