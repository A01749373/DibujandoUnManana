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

//Contiene algunos tipos de datos de los usuarios para realizar las configuraciones
data class Correo(
    @SerializedName("CorreoElectronico")
    val correo: String
): Serializable

data class Nombre(
    @SerializedName("Nombre")
    val nombre: Datos
): Serializable

data class Datos(
    @SerializedName("CorreoElectronico")
    val contrasena: String,

    @SerializedName("NuevoNombre")
    val NuevoNombre: String
): Serializable