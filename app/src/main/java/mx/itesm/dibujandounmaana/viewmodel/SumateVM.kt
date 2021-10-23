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
import mx.itesm.dibujandounmaana.model.JsonPropuesta
import mx.itesm.dibujandounmaana.model.NuevaPropuesta
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SumateVM : ViewModel() {
    val respuesta = MutableLiveData<String>()

    fun enviarPropuesta(nuevapropuesta: NuevaPropuesta) {
        //Envía la propuesta dada por el usuario activo en la aplicación a la base de datos
        println(Gson().toJson(JsonPropuesta(nuevapropuesta)))
        val call = RetrofitInstance.servicioUsuarioApi.enviarPropuesta(JsonPropuesta(nuevapropuesta))
        call.enqueue(object: Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {
                if (response.isSuccessful) {
                    respuesta.value = "${response.body()}"
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
