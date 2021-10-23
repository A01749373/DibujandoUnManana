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


//Contiene los datos necesarios para validar la existencia de un donador
data class SesionUsuario (
    @SerializedName("correoElectronico")
    val correoElectronico: String,
    @SerializedName("contrasena")
    val contrasena: String): Serializable

data class JsonSesionUsuario(
    @SerializedName("sesionUsuario")
    val sesionUsuario: SesionUsuario
) : Serializable