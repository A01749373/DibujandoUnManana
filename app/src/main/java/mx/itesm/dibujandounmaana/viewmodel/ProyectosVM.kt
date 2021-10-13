package mx.itesm.dibujandounmaana.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import mx.itesm.dibujandounmaana.RetrofitInstance
import mx.itesm.dibujandounmaana.model.Proyecto
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProyectosVM : ViewModel() {
    val arrProyectos = MutableLiveData<List<Proyecto>>()

    fun leerDatos(){
        descargarDatosProyecto()
    }

    fun descargarDatosProyecto() {
        val call = RetrofitInstance.servicioUsuarioApi.verProyecto()

        call.enqueue(object: Callback<List<Proyecto>> {
            override fun onResponse(call: Call<List<Proyecto>>, response: Response<List<Proyecto>>) {
                if (response.isSuccessful) {
                    arrProyectos.value = response.body()
                    println(arrProyectos.value)
                } else {
                    println(arrProyectos.value)
                }
            }

            override fun onFailure(call: Call<List<Proyecto>>, t: Throwable) {
                println(arrProyectos.value)
            }
        })
    }
}