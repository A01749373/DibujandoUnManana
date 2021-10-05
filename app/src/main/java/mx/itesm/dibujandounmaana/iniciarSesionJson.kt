package mx.itesm.dibujandounmaana

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class SesionUsuario (
    @SerializedName("correoElectronico")
    val correoElectronico: String,
    @SerializedName("contrasena")
    val contrasena: String): Serializable

data class JsonSesionUsuario(
    @SerializedName("sesionUsuario")
    val sesionUsuario: SesionUsuario
) : Serializable