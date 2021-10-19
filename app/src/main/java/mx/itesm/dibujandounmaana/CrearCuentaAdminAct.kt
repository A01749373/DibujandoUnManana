package mx.itesm.dibujandounmaana

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.findNavController
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
            val s1 = binding.etCorreo.text.toString();
            val s2 = "dibujando.org.mx"
            val comp = s2 in s1
            println(comp)
            if (binding.etCorreo.text.toString().isNotEmpty()) {
                if (binding.etNombreUsuario.text.toString().isNotEmpty()) {
                    if (binding.etYearFN.text.toString().length == 4) {
                        if (binding.etMesFN.text.toString().length >= 1 && binding.etMesFN.text.toString().toInt() >= 1 && binding.etMesFN.text.toString().toInt() <= 12) {
                            if (binding.etDiaFN.text.toString().length >= 1 && binding.etDiaFN.text.toString().toInt() >= 1 && binding.etDiaFN.text.toString().toInt() <= 31) {
                                if (binding.etContrsena.text.toString().length > 6) {
                                    if (binding.etContrsena.text.toString() == binding.etconfcontra.text.toString()) {
                                        if (comp) {
                                            val nuevoUsuario = Admin(
                                                binding.etCorreo.text.toString(),
                                                binding.etNombreUsuario.text.toString(),
                                                binding.etGenero.selectedItem.toString(),
                                                binding.etContrsena.text.toString()
                                            )
                                            viewModel.enviarAdmin(nuevoUsuario)
                                            findNavController(R.id.administracion)
                                            } else {
                                                println(binding.etCorreo.text.toString())
                                                //println("dibujando.org.mx" in binding.etCorreo.text.toString())
                                                binding.tvEstado.text = "Usuario no válido"
                                                binding.etCorreo.error = "El correo debe contener el dominio de la fundación"
                                            }
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
