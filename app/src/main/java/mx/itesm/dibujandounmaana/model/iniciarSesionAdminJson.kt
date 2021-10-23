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

//Contiene los datos necesarios para validar la existencia de un usuario administrador
data class SesionAdmin(
    @SerializedName("correoElectronico")
    val correoElectronico: String,
    @SerializedName("contrasena")
    val contrasena: String): Serializable

data class JsonSesionAdmin(
    @SerializedName("sesionAdmin")
    val sesionAdmin: SesionAdmin
) : Serializable
