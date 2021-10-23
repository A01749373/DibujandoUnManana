/*
Autores:
* Liam Garay Monroy
* Jorge Chávez Badillo
* Amy Murakami Tsutsumi
* Andrea Vianey Díaz Álvarez
* Ariadna Jocelyn Guzmán Jiménez
*/

package mx.itesm.dibujandounmaana.view


//Librerías
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.navigation.findNavController
import mx.itesm.dibujandounmaana.MainActivity
import mx.itesm.dibujandounmaana.R
import mx.itesm.dibujandounmaana.databinding.ActivityCrearCuentaAdminBinding
import mx.itesm.dibujandounmaana.model.Admin
import mx.itesm.dibujandounmaana.viewmodel.CrearCuentaAdminVM

class CrearCuentaAdminAct : AppCompatActivity() {

    private val viewModel: CrearCuentaAdminVM by viewModels()

    private lateinit var binding: ActivityCrearCuentaAdminBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        //Crea la vista de inicio de acuerdo al xml asignado
        super.onCreate(savedInstanceState)
        binding = ActivityCrearCuentaAdminBinding.inflate(layoutInflater)
        setContentView(binding.root)
        cargarPreferencias()
        configurarObservadores()
        configurarEventos()
    }

    private fun configurarObservadores() {
        //Observa las respuestas del servidor
        viewModel.respuesta.observe(this) { respuesta ->
            binding.tvEstado.text = respuesta
            mostrarEstadoRegistro(respuesta)
        }
    }

    // Función que muestra si el registro fue exitoso o si el usuario ya existe en la base de datos
    // para posteriormente pasar a la pantalla de administración
    private fun mostrarEstadoRegistro(respuesta: String) {
        //Entera al usuario si es un usuario ya existente o no
        if (respuesta == "Nombre de usuario ya existente") {
            Toast.makeText(this, respuesta + " 👎", Toast.LENGTH_SHORT).show()
            println(respuesta)
        } else {
            Toast.makeText(this, respuesta + " 👍", Toast.LENGTH_SHORT).show()
            println(respuesta)
            findNavController(R.id.administracion)
        }
    }

    // Configuración del listener de crear cuenta administrador, además de la verificación de que todos los datos
    // sean ingresados correctamente
    private fun configurarEventos() {
        //Evalúa que al envíar los datos del usuario estos sean correspondienres y los adecuados para guardar en la base de datos
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
                                            val fecha =
                                                "${binding.etYearFN.text.toString()}-${binding.etMesFN.text.toString()}-${binding.etDiaFN.text.toString()}"
                                            val nuevoUsuario = Admin(
                                                binding.etCorreo.text.toString(),
                                                binding.etNombreUsuario.text.toString(),
                                                fecha,
                                                binding.etGenero.selectedItem.toString(),
                                                binding.etContrsena.text.toString()
                                            )
                                            viewModel.enviarAdmin(nuevoUsuario)
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

    // Función que obtiene las preferencias del usuario en la app
    private fun cargarPreferencias() {
        //Guarda las preferencias del usuario
        val preferencias = this.getSharedPreferences("Usuario", Context.MODE_PRIVATE)
        val favorito = preferencias.getString("Correo", "-1")
        if (favorito != "-1") {
            println("$favorito")
        } else {
            println("No funciono")
        }
    }
}
