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


//Contiene los datos que se solicitan para que el administrador agregue nuevos proyectos
data class Proyecto (
    @SerializedName("idProyectos")
    val idproyecto: Int,
    @SerializedName("nombreProyecto")
    val proyecto: String,
    @SerializedName("descripProyecto")
    val descrip: String
    ):Serializable