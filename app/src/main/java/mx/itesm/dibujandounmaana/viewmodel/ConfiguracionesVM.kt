package mx.itesm.dibujandounmaana.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import mx.itesm.dibujandounmaana.RetrofitInstance
import mx.itesm.dibujandounmaana.model.Correo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ConfiguracionesVM : ViewModel() {

    val respuesta = MutableLiveData<String>()

    fun borrarUsuario(correo: String) {
        val call = RetrofitInstance.servicioUsuarioApi.borrarCuenta(Correo(correo))
        call.enqueue(object: Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {
                if (response.isSuccessful) {
                    println(respuesta.value)
                } else {
                    println(respuesta.value)
                }
            }
            override fun onFailure(call: Call<String>, t: Throwable) {
                println(respuesta.value)
            }
        })
    }
}