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
import mx.itesm.dibujandounmaana.viewmodel.CrearCuentaAdminVM
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
import mx.itesm.dibujandounmaana.databinding.NavCrearCuentaAdminBinding
import mx.itesm.dibujandounmaana.model.Admin
import org.json.JSONObject


class CrearCuentaAdmin: Fragment() {


    //private val viewModel: CrearCuentaAdminVM by viewModels()

    private val viewModel: CrearCuentaAdminVM by viewModels()

    private lateinit var binding: NavCrearCuentaAdminBinding

    lateinit var callbackManager: CallbackManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = NavCrearCuentaAdminBinding.inflate(layoutInflater)
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
            val nuevoAdmin = Admin(binding.etCorreo.text.toString(),
                binding.etNombreUsuario.text.toString(),
                binding.etGenero.text.toString(),
                binding.etContrsena.text.toString())
            viewModel.enviarAdmin(nuevoAdmin)
        }
    }


    private fun configurarObservadores() {
        viewModel.respuesta.observe(viewLifecycleOwner) { respuesta ->
            binding.tvEstado.text = respuesta
        }
    }
}