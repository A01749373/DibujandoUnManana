package mx.itesm.dibujandounmaana

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class SesionUsuario (
    @SerializedName("correoElectronico")
    val usuario: String,
    @SerializedName("contrasena")
    val contrasena: String): Serializable

data class JsonSesionUsuario(
    @SerializedName("usuario")
    val usuario: SesionUsuario
) : Serializable