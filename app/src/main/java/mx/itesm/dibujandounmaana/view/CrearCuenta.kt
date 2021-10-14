package mx.itesm.dibujandounmaana.view

import android.app.Activity.RESULT_CANCELED
import android.app.Activity.RESULT_OK
import android.os.Bundle
import android.os.ProxyFileDescriptorCallback
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.facebook.CallbackManager
import mx.itesm.dibujandounmaana.model.Usuario
import mx.itesm.dibujandounmaana.databinding.NavCrearCuentaBinding
import mx.itesm.dibujandounmaana.viewmodel.CrearCuentaVM
import com.facebook.FacebookException
import com.facebook.GraphRequest
import com.facebook.login.LoginResult
import com.facebook.GraphResponse
import com.facebook.FacebookCallback
import android.content.Intent
import com.facebook.AccessToken
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.IdpResponse
import com.google.firebase.auth.FirebaseAuth
import com.google.gson.JsonElement
import org.json.JSONObject


class CrearCuenta: Fragment() {

    private val CODIGO_SIGNIN =500
    private val viewModel: CrearCuentaVM by viewModels()

    private lateinit var binding: NavCrearCuentaBinding

    lateinit var callbackManager: CallbackManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = NavCrearCuentaBinding.inflate(layoutInflater)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        configurarObservadores()
        configurarEventos()
        //Email
        binding.loginButton.setReadPermissions("email")
        binding.loginButton.setReadPermissions("user_gender")
        //binding.loginButton.setReadPermissions("user_birthday")

        //saber si hay un token de login
        val accessToken = AccessToken.getCurrentAccessToken()
        val isLoggedIn = accessToken != null && !accessToken.isExpired
        println("logeado $isLoggedIn")

        //if de las preferencias
    }

    // Resultado del callbackmanager
    /*
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        callbackManager.onActivityResult(requestCode, resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)

    }*/


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
                    viewModel.enviarUsuario(Usuario(usuario?.email.toString(), usuario?.displayName.toString(),
                    "", "", ""))
                    // Lanzar otra actividad
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
        }
    }

    private fun configurarEventos() {
        binding.btnEnviar.setOnClickListener {
            val nuevoUsuario = Usuario(binding.etCorreo.text.toString(),
                binding.etNombreUsuario.text.toString(),
                binding.etGenero.text.toString(),
                binding.etPais.text.toString(),
                binding.etContrsena.text.toString())

            viewModel.enviarUsuario(nuevoUsuario)
        }

        binding.btnSignInGoogle.setOnClickListener{
            autenticar()

        }


        //Registra el callback de facebook
        callbackManager = CallbackManager.Factory.create()

        //Para usar el fragmento
        binding.loginButton.setFragment(this)

        binding.loginButton.registerCallback(callbackManager, object : FacebookCallback<LoginResult?> {
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
                            viewModel.enviarUsuario(Usuario(correo.toString(),name.toString(),
                                genero.toString()," "," "))
                        }
                    })

                val parameters = Bundle()
                parameters.putString("fields", "email, name, gender")//birthday)
                //println(parameters)
                request.setParameters(parameters)
                request.executeAsync()
            }

            override fun onCancel() {
                println("Firma Cancelada")
            }

            override fun onError(exception: FacebookException) {
                println("Firma no exitosa")
            }


        })



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
        viewModel.respuesta.observe(viewLifecycleOwner) { respuesta ->
            binding.tvEstado.text = respuesta
        }
    }
}