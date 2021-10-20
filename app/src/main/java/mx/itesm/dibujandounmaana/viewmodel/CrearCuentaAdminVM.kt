package mx.itesm.dibujandounmaana.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import mx.itesm.dibujandounmaana.RetrofitInstance
import mx.itesm.dibujandounmaana.model.Admin
import mx.itesm.dibujandounmaana.model.JsonAdmin
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CrearCuentaAdminVM : ViewModel()
{
    val respuesta = MutableLiveData<String>()

    fun enviarAdmin(admin: Admin) {
        println(Gson().toJson(JsonAdmin(admin)))
        val call = RetrofitInstance.servicioUsuarioApi.enviarAdmin(JsonAdmin(admin))
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