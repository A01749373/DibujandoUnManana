package mx.itesm.dibujandounmaana.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Correo(
    @SerializedName("CorreoElectronico")
    val correo: String
): Serializable