package mx.itesm.dibujandounmaana.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class NuevaPropuesta(
    @SerializedName("nombreProyecto")
    val nombreProyecto: String,
    @SerializedName("descripcion")
    val descripcion: String,
    @SerializedName("usuarioCorreo")
    val correo: String
) : Serializable


data class JsonPropuesta(
    @SerializedName("nuevapropuesta")
    val nuevapropuesta: NuevaPropuesta
) : Serializable