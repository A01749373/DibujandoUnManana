package mx.itesm.dibujandounmaana.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import mx.itesm.dibujandounmaana.RetrofitInstance
import mx.itesm.dibujandounmaana.model.CorreoUsuario
import mx.itesm.dibujandounmaana.model.Donacion
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ListaDonacionesVM : ViewModel() {
    val arrDonaciones = MutableLiveData<List<Donacion>>()

    fun leerDatos(correo: String){
        descargarDatosDonaciones(correo)
    }

    private fun descargarDatosDonaciones(correo: String) {
        val call = RetrofitInstance.servicioUsuarioApi.verDonaciones(CorreoUsuario(correo))

        call.enqueue(object: Callback<List<Donacion>>{
            override fun onResponse(call: Call<List<Donacion>>, response: Response<List<Donacion>>) {
                if (response.isSuccessful){
                    arrDonaciones.value = response.body()
                    println("Datos descargados: ${response.body()}")
                }else{
                    println("Error: ${response.errorBody()}")
                }
            }

            override fun onFailure(call: Call<List<Donacion>>, t: Throwable) {
                println("Error descargando datos ${t.localizedMessage}")
                println(arrDonaciones.value)
            }
        })
    }

}