package mx.itesm.dibujandounmaana.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import mx.itesm.dibujandounmaana.model.JsonSesionUsuario
import mx.itesm.dibujandounmaana.RetrofitInstance
import mx.itesm.dibujandounmaana.model.SesionUsuario
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class IniciarSesionVM : ViewModel() {
    val respuesta = MutableLiveData<String>()

    fun verificaUsuario(sesionUsuario: SesionUsuario){
        println(Gson().toJson(JsonSesionUsuario(sesionUsuario)))
        val call =
            RetrofitInstance.servicioUsuarioApi.iniciarSesion(JsonSesionUsuario(sesionUsuario))
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
}