package mx.itesm.dibujandounmaana.view

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.content.edit
import mx.itesm.dibujandounmaana.MainActivity
import mx.itesm.dibujandounmaana.databinding.ActivityIniciarSesionAdminBinding
import mx.itesm.dibujandounmaana.databinding.ActivityMainBinding
import mx.itesm.dibujandounmaana.model.SesionAdmin
import mx.itesm.dibujandounmaana.viewmodel.IniciarSesionAdminVM

class IniciarSesionAdminAct : AppCompatActivity() {

    private val viewModel: IniciarSesionAdminVM by viewModels()

    private lateinit var binding: ActivityIniciarSesionAdminBinding

    private lateinit var bindingMain: ActivityMainBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIniciarSesionAdminBinding.inflate(layoutInflater)
        bindingMain = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        cargarPreferencias()
        configurarObservadores()
        configurarEventos()
    }

    private fun cargarPreferencias() {
        val preferencias = this.getSharedPreferences("Usuario", Context.MODE_PRIVATE)
        val favorito = preferencias.getString("Correo", "-1")
        val tipoUsuario = preferencias.getString("TipoUsuario", "-1")
        if (favorito != "-1") {
            println("$favorito")
        } else {
            println("No funciono")
        }
        if (tipoUsuario != "-1") {
            println("$tipoUsuario")
        } else {
            println("No funcionó")
        }
    }

    private fun cambiarPantalla(respuesta: String) {
        if (respuesta == "Lo sentimos: Usuario o contraseña no válidos") {
            Toast.makeText(this, respuesta + " 😭", Toast.LENGTH_SHORT).show()
            println(respuesta)
        } else {
            Toast.makeText(this, respuesta + " 😃", Toast.LENGTH_SHORT).show()
            println(respuesta)
            var menu = bindingMain.navView.menu
            var item = menu.getItem(8)
            println("Tamaño: ${menu.size()}")
            println("Nombre de Item: $item")
            //menu.removeItem(9)
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
            guardarPrederencias(binding.etUsuario.text.toString())
            viewModel.verificaAdmin(adminRegistrado)
        }
        // Hacer actividad CrearCuentaAdmin
        binding.btnCrearCuenta.setOnClickListener {
            val intentCrearCuentaAdmin = Intent(this, CrearCuentaAdminAct::class.java)
            startActivity(intentCrearCuentaAdmin)
        }
    }

    private fun guardarPrederencias(correo: String) {
        val preferencias = this.getSharedPreferences("Usuario", Context.MODE_PRIVATE)
        preferencias.edit {
            putString("Correo", correo)
            putString("TipoUsuario", "administrador")
            commit()
        }
    }

    private fun configurarObservadores() {
        viewModel.respuesta.observe(this) { respuesta ->
            binding.tvEstado.text = respuesta
            cambiarPantalla(respuesta)
        }
    }
}