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
import mx.itesm.dibujandounmaana.model.NuevaPropuesta
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListaPropuestasVM : ViewModel() {
    val arrPropuestas = MutableLiveData<List<NuevaPropuesta>>()

    fun leerDatos(){
        //Lee los datos de las propuestas existentes en la base
        descargarPropuestas()
    }

    fun descargarPropuestas() {
        //Descarga el json de los datos recibidos del servidor
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


