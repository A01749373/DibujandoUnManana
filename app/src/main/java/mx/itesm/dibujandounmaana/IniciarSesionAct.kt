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
import mx.itesm.dibujandounmaana.databinding.ActivityIniciarSesionBinding
import mx.itesm.dibujandounmaana.model.SesionUsuario
import mx.itesm.dibujandounmaana.model.Usuario
import mx.itesm.dibujandounmaana.view.Ayuda
import mx.itesm.dibujandounmaana.viewmodel.IniciarSesionVM
import org.json.JSONObject

class IniciarSesionAct : AppCompatActivity() {

    private val viewModel: IniciarSesionVM by viewModels()

    private lateinit var binding: ActivityIniciarSesionBinding

    private val CODIGO_SIGNIN = 500

    lateinit var callbackManager: CallbackManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIniciarSesionBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.loginButton.setReadPermissions("email")

        //saber si hay un token de login
        val accessToken = AccessToken.getCurrentAccessToken()
        val isLoggedIn = accessToken != null && !accessToken.isExpired
        println("logeado $isLoggedIn")

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
            abrirActividad()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode:
    Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == CODIGO_SIGNIN) {
            when (resultCode) {
                Activity.RESULT_OK -> {
                    val usuario =
                        FirebaseAuth.getInstance().currentUser
                    val usuarioRegistrado = SesionUsuario(usuario?.email.toString(), "")
                    println("Bienvenido: ${usuario?.displayName}")
                    println("Correo: ${usuario?.email}")
                    println("UID: ${usuario?.uid}")
                    println("Nombre: ${usuario?.displayName}")
                    /*viewModel.enviarUsuario(
                        Usuario(usuario?.email.toString(), usuario?.displayName.toString(),
                        "", "",null.toString())
                    )*/
                    viewModel.verificaUsuario(usuarioRegistrado)
                    // Lanzar otra actividad
                    abrirActividad()
                }
                Activity.RESULT_CANCELED -> {
                    println("Cancelado...")
                    val response =
                        IdpResponse.fromResultIntent(data)
                    println("Error: ${response?.error?.localizedMessage}")
                }
                else -> {
                    val response =
                        IdpResponse.fromResultIntent(data)
                    println("Error: ${response?.error?.errorCode}")
                }
            }
        } else {
            callbackManager.onActivityResult(requestCode, resultCode, data)
        }
    }

    private fun abrirActividad() {
        val intIniciarSe = Intent(this,MainActivity::class.java)
        startActivity(intIniciarSe)
    }

    private fun autenticar() {
        val providers =
            arrayListOf(AuthUI.IdpConfig.GoogleBuilder().build())
        startActivityForResult(
            AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAvailableProviders(providers)
                .build(),
            CODIGO_SIGNIN
        )
        
    }

    private fun configurarEventos() {
        binding.btnIniciarSesion.setOnClickListener {
            val usuarioRegistrado = SesionUsuario(
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
            viewModel.verificaUsuario(usuarioRegistrado)
        }
        binding.btnCrearCuenta.setOnClickListener {
            val intCrear = Intent(this,CrearCuentaAct::class.java)
            startActivity(intCrear)
        }
        binding.btnSignInGoogle.setOnClickListener {
            autenticar()
        }

        //Registra el callback de facebook
        callbackManager = CallbackManager.Factory.create()

        binding.loginButton.registerCallback(callbackManager, object :
            FacebookCallback<LoginResult?> {
            override fun onSuccess(loginResult: LoginResult?) {
                println("Firma Exitosa")
                val request: GraphRequest = GraphRequest.newMeRequest(
                    loginResult?.getAccessToken(),
                    object : GraphRequest.GraphJSONObjectCallback {
                        override fun onCompleted(objeto: JSONObject?, response: GraphResponse?) {
                            //println(objeto.toString())
                            //println(objeto?.get("name"))
                            //println(response.toString())
                            val name= objeto?.get("name"); val correo=objeto?.get("email")
                            val genero = objeto?.get("gender"); //val birthday = objeto?.get("birthday")
                            viewModel.enviarUsuario(
                                Usuario(correo.toString(),name.toString(),
                                genero.toString()," "," ")
                            )
                        }
                    })

                val parameters = Bundle()
                parameters.putString("fields", "email, name, gender")//birthday)
                //println(parameters)
                request.setParameters(parameters)
                request.executeAsync()
                abrirActividad()
            }

            override fun onCancel() {
                println("Firma Cancelada")
            }

            override fun onError(exception: FacebookException) {
                println("Firma no exitosa")
            }
        })
    }

    private fun configurarObservadores() {
        viewModel.respuesta.observe(this) { respuesta ->
            binding.tvEstado.text = respuesta
            cambiarPantalla(respuesta)
        }
    }
}