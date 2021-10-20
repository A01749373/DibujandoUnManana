package mx.itesm.dibujandounmaana.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import mx.itesm.dibujandounmaana.RetrofitInstance
import mx.itesm.dibujandounmaana.model.ListaUsuarios
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ListaUsuariosVM : ViewModel() {
    val arrUsuarios = MutableLiveData<List<ListaUsuarios>>()

    fun leerDatos(){
        descargarUsuarios()
    }

    fun descargarUsuarios() {
        val call = RetrofitInstance.servicioUsuarioApi.verUsuarios()

        call.enqueue(object: Callback<List<ListaUsuarios>> {
            override fun onResponse(call: Call<List<ListaUsuarios>>, response: Response<List<ListaUsuarios>>) {
                if (response.isSuccessful) {
                    arrUsuarios.value = response.body()
                    println(arrUsuarios.value)
                } else {
                    println(arrUsuarios.value)
                }
            }

            override fun onFailure(call: Call<List<ListaUsuarios>>, t: Throwable) {
                println(arrUsuarios.value)
            }
        })
    }
}


