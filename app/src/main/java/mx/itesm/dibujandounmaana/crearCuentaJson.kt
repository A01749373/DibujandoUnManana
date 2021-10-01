package mx.itesm.dibujandounmaana

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Usuario (
    @SerializedName("CorreoElectronico")
    val correo: String,
    @SerializedName("nombre")
    val nombre: String,
    @SerializedName("contrasena")
    val contrasena: String) : Serializable


data class JsonUsuario(
    @SerializedName("usuario")
    val usuario: Usuario
) : Serializable