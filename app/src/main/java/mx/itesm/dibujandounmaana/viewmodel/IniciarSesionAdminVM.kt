package mx.itesm.dibujandounmaana.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import mx.itesm.dibujandounmaana.RetrofitInstance
import mx.itesm.dibujandounmaana.model.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class IniciarSesionAdminVM : ViewModel() {
    val respuesta = MutableLiveData<String>()
    fun verificaAdmin(sesionAdmin: SesionAdmin){
        println(Gson().toJson(JsonSesionAdmin(sesionAdmin)))
        val call = RetrofitInstance.servicioUsuarioApi.iniciarSesionAdmin(JsonSesionAdmin(sesionAdmin))

        call.enqueue(object: Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {
                if(response.isSuccessful) {
                    respuesta.value = "${response.body()}"
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

    fun enviarUsuario(usuario: Usuario) {
        println(Gson().toJson(JsonUsuario(usuario)))
        val call = RetrofitInstance.servicioUsuarioApi.enviarUsuario(JsonUsuario(usuario))
        call.enqueue(object: Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {
                if (response.isSuccessful) {
                    respuesta.value = "Ok, ${response.body()}"
                } else {
                    respuesta.value = "Error [${response.code()}] ${response.errorBody()}"
                    println(respuesta.value)
                }
            }
            override fun onFailure(call: Call<String>, t: Throwable) {
                respuesta.value = "Error, ${t.localizedMessage}"
                println(respuesta.value)
            }
        })
    }
}
