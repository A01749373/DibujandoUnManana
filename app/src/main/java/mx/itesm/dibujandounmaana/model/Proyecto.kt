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
import java.io.Serializable
import com.google.gson.annotations.SerializedName


//Contiene los datos necesarios para la visualización de proyectos de la fundación en la aplicación
data class Proyectos(
    @SerializedName("nombreProyecto")
    val nombreProyecto: String,
    @SerializedName("tipoProyecto")
    val tipoProyecto: String,
    @SerializedName("descripcion")
    val descripcion: String):Serializable

data class JsonProyecto(
    @SerializedName("proyecto")
    val proyecto: Proyectos
) : Serializable

