package mx.itesm.dibujandounmaana.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import mx.itesm.dibujandounmaana.RetrofitInstance
import mx.itesm.dibujandounmaana.model.NuevaPropuesta
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListaPropuestasVM : ViewModel() {
    val arrPropuestas = MutableLiveData<List<NuevaPropuesta>>()

    fun leerDatos(){
        descargarPropuestas()
    }

    fun descargarPropuestas() {
        val call = RetrofitInstance.servicioUsuarioApi.verPropuestas()

        call.enqueue(object: Callback<List<NuevaPropuesta>> {
            override fun onResponse(call: Call<List<NuevaPropuesta>>, response: Response<List<NuevaPropuesta>>) {
                if (response.isSuccessful) {
                    arrPropuestas.value = response.body()
                    println(arrPropuestas.value)
                } else {
                    println(arrPropuestas.value)
                }
            }

            override fun onFailure(call: Call<List<NuevaPropuesta>>, t: Throwable) {
                println(arrPropuestas.value)
            }
        })
    }
}


