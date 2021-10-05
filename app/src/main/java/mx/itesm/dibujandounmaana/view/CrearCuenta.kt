package mx.itesm.dibujandounmaana.view

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

import com.facebook.login.LoginResult

import com.facebook.FacebookCallback
import android.content.Intent
import com.facebook.AccessToken










class CrearCuenta: Fragment() {

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

        //saber si hay un token de login

        val accessToken = AccessToken.getCurrentAccessToken()
        val isLoggedIn = accessToken != null && !accessToken.isExpired
        println("logeado $isLoggedIn")

        //if de las preferencias
    }

    // Resultado del callbackmanager
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        callbackManager.onActivityResult(requestCode, resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)
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
        //Registra el callback de facebook
        callbackManager = CallbackManager.Factory.create()

        //Para usar el fragmento
        binding.loginButton.setFragment(this)

        binding.loginButton.registerCallback(callbackManager, object : FacebookCallback<LoginResult?> {
            override fun onSuccess(loginResult: LoginResult?) {
                println("Firma Exitosa")
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
        viewModel.respuesta.observe(viewLifecycleOwner) { respuesta ->
            binding.tvEstado.text = respuesta
        }
    }
}