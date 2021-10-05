package mx.itesm.dibujandounmaana

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT

// Servicios
interface ServicioUsuarioApi
{
    @POST("/Usuario/CrearCuenta")
    fun enviarUsuario(@Body usuario: JsonUsuario) : Call<String>

    @POST("/Usuario/VerUsuario")
    fun verUsuario(@Body correo: Usuario): Call<String>

    @POST("/Sesion/BuscarUsuario")
    fun iniciarSesion(@Body sesionUsuario: JsonSesionUsuario): Call<String>
}