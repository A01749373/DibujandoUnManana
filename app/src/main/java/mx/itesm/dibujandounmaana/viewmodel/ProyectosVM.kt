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
import mx.itesm.dibujandounmaana.RetrofitInstance
import mx.itesm.dibujandounmaana.model.Proyecto
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProyectosVM : ViewModel() {
    val arrProyectos = MutableLiveData<List<Proyecto>>()

    fun leerDatos(){
        //Lee los datos de los proyectos existentes en la base de datos
        descargarDatosProyecto()
    }

    fun descargarDatosProyecto() {
        //Descarga el json de los datos recibidos del servidor
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