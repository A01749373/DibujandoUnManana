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


//Contiene los datos necesarios para desplegar las propuestas de usuarios al administrador
data class NuevaPropuesta(
    @SerializedName("nombreProyecto")
    val nombreProyecto: String,
    @SerializedName("descripcion")
    val descripcion: String,
    @SerializedName("usuarioCorreo")
    val correo: String
) : Serializable


data class JsonPropuesta(
    @SerializedName("nuevapropuesta")
    val nuevapropuesta: NuevaPropuesta
) : Serializable