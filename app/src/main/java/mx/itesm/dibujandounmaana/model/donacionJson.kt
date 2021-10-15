package mx.itesm.dibujandounmaana.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class UsuarioCorreo(
    @SerializedName("CorreoElectronico")
    val correo: String
): Serializable

data class Donar(
    @SerializedName("nombreProyectoDonar")
    val nombreProyecto: String,
    @SerializedName("montoDonacion")
    val montoDonacion: String) : Serializable


data class JsonDonacion(
    @SerializedName("donacion")
    val donacion: Donar
) : Serializable