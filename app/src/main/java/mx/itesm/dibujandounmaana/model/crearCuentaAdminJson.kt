/*
Autores:
* Liam Garay Monroy
* Jorge Chávez Badillo
* Amy Murakami Tsutsumi
* Andrea Vianey Díaz Álvarez
* Ariadna Jocelyn Guzmán Jiménez
*/

package mx.itesm.dibujandounmaana.model

//Librerias
import com.google.gson.annotations.SerializedName
import java.io.Serializable

//Contiene los tipos de datos de un usuario tipo administrador
data class Admin (
    @SerializedName("correoElectronico")
    val correo: String,
    @SerializedName("nombre")
    val nombre: String,
    @SerializedName("fechaNacimiento")
    val fechaNacimiento: String,
    @SerializedName("genero")
    val genero: String,
    @SerializedName("contrasena")
    val contrasena: String) : Serializable


//Realiza un objeto json con los datos del administrador
data class JsonAdmin(
    @SerializedName("admin")
    val admin: Admin
) : Serializable