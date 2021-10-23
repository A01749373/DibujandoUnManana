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


//Contiene los datos que se desplegarán para la visualización del perfil del usuario
data class crearPefilUsuario(
    @SerializedName("nombre")
    val nombre: String,
    @SerializedName("historial")
    val historial: Int,
    @SerializedName("usuarioCorreo")
    val correo: String
) :Serializable

data class CorreoUsuario(
    @SerializedName("CorreoElectronico")
    val correo: String
): Serializable

data class Donacion(
    @SerializedName("nombreProyectoDonar")
    val nombreProyecto: String,
    @SerializedName("montoDonacion")
    val montoDonacion: Int,
    @SerializedName("fechaDonacion")
    val fechaDonacion: String

):Serializable


