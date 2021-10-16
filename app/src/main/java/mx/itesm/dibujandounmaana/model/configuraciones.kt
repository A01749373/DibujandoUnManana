package mx.itesm.dibujandounmaana.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Correo(
    @SerializedName("CorreoElectronico")
    val correo: String
): Serializable

data class Nombre(
    @SerializedName("Nombre")
    val nombre: Datos
): Serializable

data class Datos(
    @SerializedName("CorreoElectronico")
    val contrasena: String,

    @SerializedName("NuevoNombre")
    val NuevoNombre: String
): Serializable