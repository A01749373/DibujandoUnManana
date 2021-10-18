package mx.itesm.dibujandounmaana.model

import java.io.Serializable
import com.google.gson.annotations.SerializedName

data class Proyectos(
    @SerializedName("nombreProyecto")
    val nombreProyecto: String,
    @SerializedName("tipoProyecto")
    val tipoProyecto: String,
    @SerializedName("descripcion")
    val descripcion: String):Serializable

data class JsonProyecto(
    @SerializedName("proyecto")
    val proyecto: Proyectos
) : Serializable

