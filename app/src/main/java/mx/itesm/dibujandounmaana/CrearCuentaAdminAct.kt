package mx.itesm.dibujandounmaana

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import mx.itesm.dibujandounmaana.databinding.ActivityCrearCuentaAdminBinding
import mx.itesm.dibujandounmaana.databinding.ActivityIniciarSesionAdminBinding
import mx.itesm.dibujandounmaana.model.Admin
import mx.itesm.dibujandounmaana.model.Usuario
import mx.itesm.dibujandounmaana.viewmodel.CrearCuentaAdminVM

class CrearCuentaAdminAct : AppCompatActivity() {

    private val viewModel: CrearCuentaAdminVM by viewModels()

    private lateinit var binding: ActivityCrearCuentaAdminBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCrearCuentaAdminBinding.inflate(layoutInflater)
        setContentView(binding.root)
        cargarPreferencias()
        configurarObservadores()
        configurarEventos()
    }

    private fun configurarEventos() {
        viewModel.respuesta.observe(this) { respuesta ->
            binding.tvEstado.text = respuesta
        }
    }

    private fun configurarObservadores() {
        binding.btnEvniar.setOnClickListener {
            val nuevoUsuario = Admin(
                binding.etCorreo.text.toString(),
                binding.etNombreUsuario.text.toString(),
                binding.etGenero.text.toString(),
                binding.etContrsena.text.toString()
            )
            viewModel.enviarAdmin(nuevoUsuario)
        }
    }

    private fun cargarPreferencias() {
        val preferencias = this.getSharedPreferences("Usuario", Context.MODE_PRIVATE)
        val favorito = preferencias.getString("Correo", "-1")
        if (favorito != "-1") {
            println("$favorito")
        } else {
            println("No funciono")
        }
    }
}