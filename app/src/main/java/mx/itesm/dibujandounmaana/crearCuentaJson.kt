package mx.itesm.dibujandounmaana

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Usuario (
    @SerializedName("CorreoElectronico")
    val correo: String) : Serializable


data class JsonUsuario(
    @SerializedName("alumno")
    val usuario: Usuario
) : Serializable