package mx.itesm.dibujandounmaana.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class IniciarSesionVM : ViewModel() {
    val respuesta = MutableLiveData<String>()

    fun verificaUsuario(usuario: Usuario){

    }
}