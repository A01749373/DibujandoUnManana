package mx.itesm.dibujandounmaana

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Usuario (
    @SerializedName("matricula")
    val matricula: String,
    @SerializedName("nombre")
    val nombre: String,
    @SerializedName("semestre")
    val semestre: Int) : Serializable


data class JsonUsuario(
    @SerializedName("alumno")
    val usuario: Usuario
) : Serializable