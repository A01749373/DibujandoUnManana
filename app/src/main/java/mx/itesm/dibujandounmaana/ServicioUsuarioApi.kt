package mx.itesm.dibujandounmaana

import mx.itesm.dibujandounmaana.model.*
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

// Servicios
interface ServicioUsuarioApi
{
    @POST("/Usuario/CrearCuenta")
    fun enviarUsuario(@Body usuario: JsonUsuario) : Call<String>

    @POST("/crearPerfilUsuario/PerfilUsuario")
    fun verUsuario(@Body correo: CorreoUsuario): Call<String>

    @POST("/Sesion/BuscarUsuario")
    fun iniciarSesion(@Body sesionUsuario: JsonSesionUsuario): Call<String>
}