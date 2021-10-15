package mx.itesm.dibujandounmaana.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Proyecto (
    @SerializedName("idProyectos")
    val idproyecto: Int,
    @SerializedName("nombreProyecto")
    val proyecto: String,
    @SerializedName("descripProyecto")
    val descrip: String
    ):Serializable