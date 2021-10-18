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

    private val CODIGO_SIGNIN =500
    private val viewModel: CrearCuentaVM by viewModels()

    private lateinit var auth: FirebaseAuth
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

        auth = Firebase.auth
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
            if (binding.etCorreo.text.toString().isNotEmpty()) {
                if (binding.etNombreUsuario.text.toString().isNotEmpty()) {
                    if (binding.etYearFN.text.toString().length == 4) {
                        if (binding.etMesFN.text.toString()
                                .toInt() >= 1 && binding.etMesFN.text.toString().toInt() <= 12 &&
                                binding.etMesFN.text.toString().isNotEmpty()
                        ) {
                            if (binding.etDiaFN.text.toString()
                                    .toInt() >= 1 && binding.etDiaFN.text.toString().toInt() <= 31 &&
                                    binding.etDiaFN.text.toString().isNotEmpty()
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
                                        auth.createUserWithEmailAndPassword(
                                            binding.etCorreo.text.toString(),
                                            binding.etContrsena.text.toString()
                                        ).addOnCompleteListener(this) { task ->
                                            if (task.isSuccessful) {
                                                println("Usuario creado ${auth.currentUser}")
                                                auth.currentUser!!.sendEmailVerification()

                                            } else {
                                                println("Fallido/n ${task.result.toString()}")
                                            }
                                        }
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

            //val usuario = FirebaseAuth.getInstance().currentUser
            //println(usuario)
            //usuario?.sendEmailVerification()
            //println("Correo enviado")
            /*
                if(task.isSuccessful && usuario!=null){
                    usuario?.sendEmailVerification()
                    println("Correo enviado")
                }else{
                    println("Correo no enviado")

            }*/


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