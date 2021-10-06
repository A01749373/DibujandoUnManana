package mx.itesm.dibujandounmaana.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import mx.itesm.dibujandounmaana.RetrofitInstance
import mx.itesm.dibujandounmaana.ServicioUsuarioApi
import mx.itesm.dibujandounmaana.model.CorreoUsuario
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PerfilUsuarioVM : ViewModel() {
    val respuesta = MutableLiveData<String>()

    fun descargarDatosUsuario(correo: String) {
        val call = RetrofitInstance.servicioUsuarioApi.verUsuario(CorreoUsuario(correo))

        call.enqueue(object: Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {
                if (response.isSuccessful) {
                    respuesta.value = "${response.body()}"
                    println(respuesta.value)
                } else {
                    respuesta.value = "[${response.body()}] ${response.errorBody()}"
                    println(respuesta.value)
                }
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                respuesta.value = "Error :(, ${t.localizedMessage}"
                println(respuesta.value)
            }
        })
    }
}