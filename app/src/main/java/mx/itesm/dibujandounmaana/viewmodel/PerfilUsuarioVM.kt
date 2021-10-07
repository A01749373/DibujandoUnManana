package mx.itesm.dibujandounmaana.viewmodel

import android.content.SharedPreferences
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import mx.itesm.dibujandounmaana.RetrofitInstance
import mx.itesm.dibujandounmaana.ServicioUsuarioApi
import mx.itesm.dibujandounmaana.model.CorreoUsuario
import mx.itesm.dibujandounmaana.model.crearPefilUsuario
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PerfilUsuarioVM : ViewModel() {
    val respuesta = MutableLiveData<crearPefilUsuario>()

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