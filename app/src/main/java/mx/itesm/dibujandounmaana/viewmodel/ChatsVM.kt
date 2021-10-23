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
import mx.itesm.dibujandounmaana.RetrofitInstance
import mx.itesm.dibujandounmaana.model.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ChatsVM : ViewModel() {
    val respuesta = MutableLiveData<crearPefilUsuario>()

    // Descarga los datos del usuario de la base de datos
    fun descargarDatosUsuario(correo: String) {
        val call = RetrofitInstance.servicioUsuarioApi.verUsuario(CorreoUsuario(correo))

        call.enqueue(object: Callback<crearPefilUsuario> {
            override fun onResponse(call: Call<crearPefilUsuario>, response: Response<crearPefilUsuario>) {
                if (response.isSuccessful) {
                    respuesta.value = response.body()
                    println(respuesta.value)
                } else {
                    println(respuesta.value)
                }
            }

            override fun onFailure(call: Call<crearPefilUsuario>, t: Throwable) {
                println(respuesta.value)
            }
        })
    }
}