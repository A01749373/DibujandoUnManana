package mx.itesm.dibujandounmaana

import mx.itesm.dibujandounmaana.model.JsonSesionUsuario
import mx.itesm.dibujandounmaana.model.JsonUsuario
import mx.itesm.dibujandounmaana.model.Usuario
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

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