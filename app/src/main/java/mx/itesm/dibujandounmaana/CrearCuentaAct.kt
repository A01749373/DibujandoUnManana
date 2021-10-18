package mx.itesm.dibujandounmaana

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.facebook.*
import com.facebook.login.LoginResult
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.IdpResponse
import com.google.firebase.auth.FirebaseAuth
import mx.itesm.dibujandounmaana.databinding.ActivityCrearCuentaBinding
import mx.itesm.dibujandounmaana.model.Usuario
import mx.itesm.dibujandounmaana.viewmodel.CrearCuentaVM
import org.json.JSONObject

class CrearCuentaAct : AppCompatActivity() {

    private val CODIGO_SIGNIN =500
    private val viewModel: CrearCuentaVM by viewModels()

    private lateinit var binding: ActivityCrearCuentaBinding

    lateinit var callbackManager: CallbackManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCrearCuentaBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.loginButton.setReadPermissions("email")

        //saber si hay un token de login
        val accessToken = AccessToken.getCurrentAccessToken()
        val isLoggedIn = accessToken != null && !accessToken.isExpired
        println("logeado $isLoggedIn")

        configurarObservadores()
        configurarEventos()
    }


    override fun onActivityResult(requestCode: Int, resultCode:
    Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == CODIGO_SIGNIN) {
            when (resultCode) {
                RESULT_OK -> {
                    val usuario =
                        FirebaseAuth.getInstance().currentUser
                    println("Bienvenido: ${usuario?.displayName}")
                    println("Correo: ${usuario?.email}")
                    println("UID: ${usuario?.uid}")
                    println("Nombre: ${usuario?.displayName}")
                    viewModel.enviarUsuario(
                        Usuario(usuario?.email.toString(), usuario?.displayName.toString(),
                        "", "","",null.toString())
                    )
                    // Lanzar otra actividad
                    abrirActividad()
                }
                RESULT_CANCELED -> {
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
        } else{
            callbackManager.onActivityResult(requestCode, resultCode, data)
        }
    }

    private fun configurarEventos() {
        binding.btnEvniar.setOnClickListener {
            if(binding.etContrsena.text.toString() == binding.etconfcontra.text.toString()) {
                val nuevoUsuario = Usuario(
                    binding.etCorreo.text.toString(),
                    binding.etNombreUsuario.text.toString(),
                    binding.etFechaNacimiento.text.toString(),
                    binding.etGenero.text.toString(),
                    binding.etPais.text.toString(),
                    binding.etContrsena.text.toString()
                )

                viewModel.enviarUsuario(nuevoUsuario)
                abrirActividad()
            } else{
                binding.etconfcontra.error = "Las contraseña no coinciden"
                println(binding.etContrsena.text.toString())
            }
        }

        binding.btnSignInGoogle.setOnClickListener{
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
                                "",genero.toString()," ","")
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

    private fun abrirActividad() {
        val intIniciarSe = Intent(this,IniciarSesionAct::class.java)
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



    private fun configurarObservadores() {
        viewModel.respuesta.observe(this) { respuesta ->
            binding.tvEstado.text = respuesta
        }
    }
}