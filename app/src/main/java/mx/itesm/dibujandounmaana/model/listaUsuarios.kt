package mx.itesm.dibujandounmaana.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ListaUsuarios (
    @SerializedName("nombre")
    val nombre: String,
    @SerializedName("usuarioCorreo")
    val usuarioCorreo: String,
    @SerializedName("montoTotal")
    val montoTotal: String,
    @SerializedName("nombreProyectoDonar")
    val nombreProyectoDonar: String
):Serializable