package mx.itesm.dibujandounmaana.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

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


data class JsonUsuario(
    @SerializedName("usuario")
    val usuario: Usuario
) : Serializable