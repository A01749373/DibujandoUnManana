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

//Contiene los tipos de datos correspondientes a una donación
data class Donar(
    @SerializedName("nombreProyectoDonar")
    val nombreProyecto: String,
    @SerializedName("montoDonacion")
    val montoDonacion: Int,
    @SerializedName("usuarioCorreo")
    val correo: String,
    @SerializedName("proyectoIdProyectos")
    val proyectoId: Int
) : Serializable


//Realiza un objeto json con los datos de la donación
data class JsonDonacion(
    @SerializedName("donacion")
    val donacion: Donar
) : Serializable