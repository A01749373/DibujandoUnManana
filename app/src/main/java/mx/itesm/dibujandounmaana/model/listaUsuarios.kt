/*
Autores:
* Liam Garay Monroy
* Jorge Chávez Badillo
* Amy Murakami Tsutsumi
* Andrea Vianey Díaz Álvarez
* Ariadna Jocelyn Guzmán Jiménez
*/

package mx.itesm.dibujandounmaana.model

//Librerías
import com.google.gson.annotations.SerializedName
import java.io.Serializable


//Contiene los datos de usuarios que se le mostrarán en el perfil de administrador
data class ListaUsuarios (
    @SerializedName("nombre")
    val nombre: String,
    @SerializedName("usuarioCorreo")
    val usuarioCorreo: String,
    @SerializedName("montoTotal")
    val montoTotal: String,
    @SerializedName("nombreProyectoDonar")
    val nombreProyectoDonar: String
):Serializable