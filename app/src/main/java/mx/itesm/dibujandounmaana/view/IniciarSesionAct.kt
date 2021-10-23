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
import mx.itesm.dibujandounmaana.MainActivity
import mx.itesm.dibujandounmaana.databinding.ActivityIniciarSesionBinding
import mx.itesm.dibujandounmaana.model.SesionUsuario
import mx.itesm.dibujandounmaana.model.Usuario
import mx.itesm.dibujandounmaana.viewmodel.IniciarSesionVM
import org.json.JSONObject

class IniciarSesionAct : AppCompatActivity() {

    private val viewModel: IniciarSesionVM by viewModels()

    private lateinit var binding: ActivityIniciarSesionBinding

    private val CODIGO_SIGNIN = 500

    lateinit var callbackManager: CallbackManager

    override fun onCreate(savedInstanceState: Bundle?) {
        //Crea la vista de acuerdo al xml asignado y carga las funciones y preferencias configuradas
        super.onCreate(savedInstanceState)
        binding = ActivityIniciarSesionBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.loginButton.setReadPermissions("email")

        val accessToken = AccessToken.getCurrentAccessToken()
        val isLoggedIn = accessToken != null && !accessToken.isExpired
        println("logeado $isLoggedIn")

        cargarPreferencias()
        configurarObservadores()
        configurarEventos()
    }

    private fun cargarPreferencias() {
        //Verfica la existencia del usuario para dejarlo acceder a la aplicación
        val preferencias = this.getSharedPreferences("Usuario", Context.MODE_PRIVATE)
        val favorito = preferencias.getString("Correo", "-1")
        val tipoUsuario = preferencias.getString("TipoUsuario", "-1")
        if (favorito != "-1") {
            println("si esta $favorito")
            val Mainact = Intent(this, MainActivity::class.java)
            startActivity(Mainact)
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
        //Si la respuesta del servidor es válida, lo deja entrar a la aplicación
        if (respuesta == "Lo sentimos: Usuario o contraseña no válidos") {
            Toast.makeText(this, respuesta + " 😭", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, respuesta + " 😃", Toast.LENGTH_SHORT).show()
            abrirActividad()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode:
    Int, data: Intent?) {
        //Se le da una respuesta al usuario de verificación si inicia sesión con google
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
                    viewModel.enviarUsuario(
                        Usuario(usuario?.email.toString(), usuario?.displayName.toString(),
                        "2001-12-07", "","", null.toString())
                    )
                    viewModel.verificaUsuario(usuarioRegistrado)
                    // Lanzar otra actividad
                    abrirActividad()
                    guardarPrederencias(usuario?.email.toString())
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
        //Abre la aplicación si el usuario es validad
        val intIniciarSe = Intent(this, MainActivity::class.java)
        startActivity(intIniciarSe)
    }

    private fun autenticar() {
        //Autentica a un usuario si inicia sesión con google
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
        //Dependiendo del método de  inicio de sesión, se le da una respuesta al usuario (inicio de sesión normal y con facebook)
        binding.btnIniciarSesion.setOnClickListener {
            val usuarioRegistrado = SesionUsuario(
                binding.etUsuario.text.toString(),
                binding.etContrasena.text.toString()
            )
            guardarPrederencias(binding.etUsuario.text.toString())
            viewModel.verificaUsuario(usuarioRegistrado)
        }
        binding.btnCrearCuenta.setOnClickListener {
            val intCrear = Intent(this, CrearCuentaAct::class.java)
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
                            val name= objeto?.get("name")
                            val correo=objeto?.get("email")
                            val genero = objeto?.get("gender"); //val birthday = objeto?.get("birthday")
                            viewModel.enviarUsuario(
                                Usuario(correo.toString(),name.toString(),
                                "2001-12-07",genero.toString()," "," ")
                            )
                            guardarPrederencias(correo.toString())
                        }
                    })

                val parameters = Bundle()
                parameters.putString("fields", "email, name, gender")//birthday)
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

    private fun guardarPrederencias(correo: String) {
        //Guarda las preferencias del usuario (correo y tipo de usuario)
        val preferencias = this.getSharedPreferences("Usuario", Context.MODE_PRIVATE)
        preferencias.edit {
            putString("Correo", correo)
            putString("TipoUsuario", "normal")
            commit()
        }
    }

    private fun configurarObservadores() {
        //Observa la respuesta que se obtiene del servidor
        viewModel.respuesta.observe(this) { respuesta ->
            binding.tvEstado.text = respuesta
            cambiarPantalla(respuesta)
        }
    }
}