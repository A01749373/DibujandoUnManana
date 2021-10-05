package mx.itesm.dibujandounmaana

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

object RetrofitInstance {
    private val retrofit: Retrofit by lazy {

        Retrofit.Builder()
            // Localhost Liam: 172.17.80.1
            // Localhost Jorge: 192.168.3.18 10.48.74.247
            .baseUrl("http://10.48.74.247:8080")     // Localhost de la máquina donde corre el simulador
            .addConverterFactory(ScalarsConverterFactory.create())      // String. Int, etc
            .addConverterFactory(GsonConverterFactory.create())         // Json
            .build()
    }

    val servicioUsuarioApi: ServicioUsuarioApi by lazy {
        retrofit.create(ServicioUsuarioApi::class.java)
    }
}