package mx.itesm.dibujandounmaana.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class crearPefilUsuario(
    @SerializedName("nombre")
    val nombre: String,
    @SerializedName("historial")
    val historial: String,
    @SerializedName("usuarioCorreo")
    val correo: String
) :Serializable

data class CorreoUsuario(
    @SerializedName("CorreoElectronico")
    val correo: String
): Serializable

data class Donacion(
    @SerializedName("nombreProyectoDonar")
    val nombreProyecto: String,
    @SerializedName("montoDonacion")
    val montoDonacion: Int,
    @SerializedName("fechaDonacion")
    val fechaDonacion: String

):Serializable


