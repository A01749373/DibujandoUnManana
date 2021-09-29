package mx.itesm.dibujandounmaana

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

object RetrofitInstance {
    private val retrofit: Retrofit by lazy {

        Retrofit.Builder()
            .baseUrl("http://10.0.2.2")     // Localhost de la máquina donde corre el simulador
            .addConverterFactory(ScalarsConverterFactory.create())      // String. Int, etc
            .addConverterFactory(GsonConverterFactory.create())         // Json
            .build()
    }

    val servicioUsuarioApi: ServicioUsuarioApi by lazy {
        retrofit.create(ServicioUsuarioApi::class.java)
    }
}