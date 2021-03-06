/*
Autores:
* Liam Garay Monroy
* Jorge Chávez Badillo
* Amy Murakami Tsutsumi
* Andrea Vianey Díaz Álvarez
* Ariadna Jocelyn Guzmán Jiménez
*/


package mx.itesm.dibujandounmaana

//Librerías
import mx.itesm.dibujandounmaana.model.*
import mx.itesm.dibujandounmaana.model.ListaUsuarios
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

// Servicios y contacto con el servidor
interface ServicioUsuarioApi
{
    @POST("/Usuario/CrearCuenta")
    fun enviarUsuario(@Body usuario: JsonUsuario) : Call<String>

    @POST("/Usuario/CrearCuentaAdmin")
    fun enviarAdmin(@Body admin: JsonAdmin) : Call<String>

    @POST("/crearPerfilUsuario/PerfilUsuario")
    fun verUsuario(@Body correo: CorreoUsuario): Call<crearPefilUsuario>

    @POST("/crearPerfilUsuario/EnviarDonaciones")
    fun verDonaciones(@Body correo: CorreoUsuario): Call<List<Donacion>>

    @POST("/crearPerfilUsuario/VerUsuarios")
    fun verUsuarios(): Call<List<ListaUsuarios>>

    @POST("/Sesion/BuscarUsuario")
    fun iniciarSesion(@Body sesionUsuario: JsonSesionUsuario): Call<String>

    @POST("/Sesion/BuscarAdmin")
    fun iniciarSesionAdmin(@Body sesionAdmin: JsonSesionAdmin): Call<String>

    @POST("/proyectos/Proyectos")
    fun verProyecto(): Call<List<Proyecto>>

    @POST("/configuraciones/BorrarCuenta")
    fun borrarCuenta(@Body correo: Correo) : Call<String>

    @POST("/configuraciones/EditarNombre")
    fun editarNombre(@Body nombre: Nombre) : Call<String>

    @POST("/configuraciones/EditarContrasena")
    fun editarContrasena(@Body nombre: Nombre) : Call<String>
    
    @POST("/donacion/NuevaDonacion")
    fun enviarDonacion(@Body donacion: JsonDonacion) : Call<String>

    @POST("/nuevapropuesta/NuevaPropuesta")
    fun enviarPropuesta(@Body nuevapropuesta: JsonPropuesta) : Call<String>

    @POST("/nuevapropuesta/verPropuestas")
    fun verPropuestas():Call <List<NuevaPropuesta>>

    @POST("/proyectos/AgregarProyecto")
    fun agregarProyecto(@Body proyecto: JsonProyecto) : Call<String>


}