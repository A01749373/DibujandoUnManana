/*
Autores:
* Liam Garay Monroy
* Jorge Chávez Badillo
* Amy Murakami Tsutsumi
* Andrea Vianey Díaz Álvarez
* Ariadna Jocelyn Guzmán Jiménez
*/


package mx.itesm.dibujandounmaana.viewmodel

//Librerías
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import mx.itesm.dibujandounmaana.model.JsonSesionUsuario
import mx.itesm.dibujandounmaana.RetrofitInstance
import mx.itesm.dibujandounmaana.model.JsonUsuario
import mx.itesm.dibujandounmaana.model.SesionUsuario
import mx.itesm.dibujandounmaana.model.Usuario
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class IniciarSesionVM : ViewModel() {
    val respuesta = MutableLiveData<String>()

    fun verificaUsuario(sesionUsuario: SesionUsuario){
        //Verifica con el servidor si el usuario existe en la base de datos
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

    fun enviarUsuario(usuario: Usuario) {
        //Envía un nuevo usuario al servidor para crear el registro
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