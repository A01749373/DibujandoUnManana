package mx.itesm.dibujandounmaana.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class SesionAdmin(
    @SerializedName("correoElectronico")
    val correoElectronico: String,
    @SerializedName("contrasena")
    val contrasena: String): Serializable

data class JsonSesionAdmin(
    @SerializedName("sesionAdmin")
    val sesionAdmin: SesionAdmin
) : Serializable
