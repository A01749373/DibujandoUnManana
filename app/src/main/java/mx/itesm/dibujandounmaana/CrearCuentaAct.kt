package mx.itesm.dibujandounmaana

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.facebook.*
import com.facebook.login.LoginResult
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.IdpResponse
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import mx.itesm.dibujandounmaana.databinding.ActivityCrearCuentaBinding
import mx.itesm.dibujandounmaana.model.Usuario
import mx.itesm.dibujandounmaana.viewmodel.CrearCuentaVM
import org.json.JSONObject

class CrearCuentaAct : AppCompatActivity() {

    private val viewModel: CrearCuentaVM by viewModels()

    private lateinit var binding: ActivityCrearCuentaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCrearCuentaBinding.inflate(layoutInflater)
        setContentView(binding.root)
        configurarObservadores()
        configurarEventos()
    }

    private fun configurarEventos() {
        binding.btnEvniar.setOnClickListener {
            if (binding.etCorreo.text.toString().isNotEmpty()) {
                if (binding.etNombreUsuario.text.toString().isNotEmpty()) {
                    if (binding.etYearFN.text.toString().length == 4) {
                        if (binding.etMesFN.text.toString().length >= 1 && binding.etMesFN.text.toString()
                                .toInt() >= 1 && binding.etMesFN.text.toString().toInt() <= 12
                        ) {
                            if (binding.etDiaFN.text.toString().length >= 1 && binding.etDiaFN.text.toString()
                                    .toInt() >= 1 && binding.etDiaFN.text.toString().toInt() <= 31
                            ) {
                                if (binding.etContrsena.text.toString().length > 6) {
                                    if (binding.etContrsena.text.toString() == binding.etconfcontra.text.toString()) {
                                        val fecha =
                                            "${binding.etYearFN.text.toString()}-${binding.etMesFN.text.toString()}-${binding.etDiaFN.text.toString()}"
                                        val nuevoUsuario = Usuario(
                                            binding.etCorreo.text.toString(),
                                            binding.etNombreUsuario.text.toString(),
                                            fecha,
                                            binding.etGenero.selectedItem.toString(),
                                            binding.etPais.selectedItem.toString(),
                                            binding.etContrsena.text.toString()
                                        )

                                        viewModel.enviarUsuario(nuevoUsuario)
                                        abrirActividad()
                                    } else {
                                        binding.etconfcontra.setError("Las contraseñas no coinciden")
                                    }
                                } else {
                                    binding.etContrsena.setError("La contraseña debe ser mayor a 6 caracteres")
                                }
                            } else {
                                binding.etDiaFN.setError("El Día debe ser un dígito entre 1 y 31")
                            }
                        } else {
                            binding.etMesFN.setError("El mes debe ser un dígito entre 1 y 12")
                        }
                    } else {
                        binding.etYearFN.setError("El año debe contener 4 dígitos")
                    }
                } else {
                    binding.etNombreUsuario.setError("No puedes dejar campos vacios")
                }
            } else {
                binding.etCorreo.setError("No puedes dejar campos vacios")
            }
        }
        println(binding.etCorreo.text.toString())
        println(binding.etContrsena.text.toString())
    }
    private fun abrirActividad() {
        val intIniciarSe = Intent(this,IniciarSesionAct::class.java)
        startActivity(intIniciarSe)
    }

    private fun configurarObservadores() {
        viewModel.respuesta.observe(this) { respuesta ->
            binding.tvEstado.text = respuesta
        }
    }
}