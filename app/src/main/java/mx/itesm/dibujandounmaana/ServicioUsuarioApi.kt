package mx.itesm.dibujandounmaana

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.PUT

// Servicios
interface ServicioUsuarioApi
{
    @POST("retrofit/alumno_post.php")
    fun enviarUsuario(@Body alumno: JsonAlumno) : Call<String>
}