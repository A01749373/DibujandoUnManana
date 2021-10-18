package mx.itesm.dibujandounmaana.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import mx.itesm.dibujandounmaana.RetrofitInstance
import mx.itesm.dibujandounmaana.model.JsonProyecto
import mx.itesm.dibujandounmaana.model.Proyectos
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AgregarProyectoVM : ViewModel(){
    val respuesta = MutableLiveData<String>()

    fun enviarProyecto(proyectos: Proyectos){
        println(Gson().toJson(JsonProyecto(proyectos)))
        val call = RetrofitInstance.servicioUsuarioApi.agregarProyecto(JsonProyecto(proyectos))
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