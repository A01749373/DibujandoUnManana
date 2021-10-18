package mx.itesm.dibujandounmaana.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Donar(
    @SerializedName("nombreProyectoDonar")
    val nombreProyecto: String,
    @SerializedName("montoDonacion")
    val montoDonacion: Int,
    @SerializedName("usuarioCorreo")
    val correo: String,
    @SerializedName("proyectoIdProyectos")
    val proyectoId: Int
) : Serializable


data class JsonDonacion(
    @SerializedName("donacion")
    val donacion: Donar
) : Serializable