package mx.itesm.dibujandounmaana.model

import com.google.gson.annotations.SerializedName
import mx.itesm.dibujandounmaana.viewmodel.PerfilUsuarioVM
import java.io.Serializable

data class crearPefilUsuario(
    @SerializedName("nombre")
    val nombre: String,
    @SerializedName("donaciones")
    val donaciones: List< Map<String, Any>>,
    @SerializedName("historial")
    val historial: String
) :Serializable

data class CorreoUsuario(
    @SerializedName("CorreoElectronico")
    val correo: String
): Serializable


