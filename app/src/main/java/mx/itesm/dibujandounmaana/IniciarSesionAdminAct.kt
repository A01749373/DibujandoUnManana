package mx.itesm.dibujandounmaana

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.content.edit
import com.facebook.*
import com.facebook.login.LoginResult
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.IdpResponse
import com.google.firebase.auth.FirebaseAuth
import mx.itesm.dibujandounmaana.databinding.ActivityIniciarSesionAdminBinding
import mx.itesm.dibujandounmaana.databinding.ActivityIniciarSesionBinding
import mx.itesm.dibujandounmaana.model.SesionAdmin
import mx.itesm.dibujandounmaana.model.SesionUsuario
import mx.itesm.dibujandounmaana.model.Usuario
import mx.itesm.dibujandounmaana.viewmodel.IniciarSesionAdminVM
import mx.itesm.dibujandounmaana.viewmodel.IniciarSesionVM
import org.json.JSONObject

class IniciarSesionAdminAct : AppCompatActivity() {

    private val viewModel: IniciarSesionAdminVM by viewModels()

    private lateinit var binding: ActivityIniciarSesionAdminBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIniciarSesionAdminBinding.inflate(layoutInflater)
        setContentView(binding.root)
        cargarPreferencias()
        configurarObservadores()
        configurarEventos()
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

    private fun cambiarPantalla(respuesta: String) {
        if (respuesta == "Lo sentimos: Usuario o contraseña no válidos") {
            Toast.makeText(this, respuesta + " 😭", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, respuesta + " 😃", Toast.LENGTH_SHORT).show()
            val intentAppPrincipal = Intent(this, MainActivity::class.java)
            startActivity(intentAppPrincipal)
        }
    }

    private fun configurarEventos() {
        binding.btnIniciarSesion.setOnClickListener {
            val adminRegistrado = SesionAdmin(
                binding.etUsuario.text.toString(),
                binding.etContrasena.text.toString()
            )
            val preferencias =
                this.getSharedPreferences("Usuario", Context.MODE_PRIVATE)
            preferencias.edit {
                putString("Correo", binding.etUsuario.text.toString())
                commit()
            }
            // Ver Datos de preferencias
            val favorito = preferencias.getString("Correo", "-1")
            if (favorito != "-1") {
                println("Este es el correo del usuario: $favorito")
            } else {
                println("No funciono")
            }
            viewModel.verificaAdmin(adminRegistrado)
        }
        // Hacer actividad CrearCuentaAdmin
        binding.btnCrearCuenta.setOnClickListener {
            val intentCrearCuentaAdmin = Intent(this, CrearCuentaAct::class.java)
            startActivity(intentCrearCuentaAdmin)
        }
    }

    private fun configurarObservadores() {
        viewModel.respuesta.observe(this) { respuesta ->
            binding.tvEstado.text = respuesta
            cambiarPantalla(respuesta)
        }
    }
}