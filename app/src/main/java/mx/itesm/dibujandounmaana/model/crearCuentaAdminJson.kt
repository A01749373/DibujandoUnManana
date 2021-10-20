package mx.itesm.dibujandounmaana.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Admin (
    @SerializedName("correoElectronico")
    val correo: String,
    @SerializedName("nombre")
    val nombre: String,
    @SerializedName("fechaNacimiento")
    val fechaNacimiento: String,
    @SerializedName("genero")
    val genero: String,
    @SerializedName("contrasena")
    val contrasena: String) : Serializable


data class JsonAdmin(
    @SerializedName("admin")
    val admin: Admin
) : Serializable