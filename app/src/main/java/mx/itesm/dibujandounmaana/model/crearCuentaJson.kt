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

//Contiene los tipos de datos de un usuario tipo donador
data class Usuario (
    @SerializedName("correoElectronico")
    val correo: String,
    @SerializedName("nombre")
    val nombre: String,
    @SerializedName("fechaNacimiento")
    val FechaNacimiento: String,
    @SerializedName("genero")
    val genero: String,
    @SerializedName("pais")
    val pais: String,
    @SerializedName("contrasena")
    val contrasena: String) : Serializable


//Realiza un objeto json con los datos del donador
data class JsonUsuario(
    @SerializedName("usuario")
    val usuario: Usuario
) : Serializable