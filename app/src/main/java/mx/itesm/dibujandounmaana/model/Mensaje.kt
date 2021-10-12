package mx.itesm.dibujandounmaana.model

import java.util.*

data class Mensaje (
    var message: String= "",
    var from: String = "",
    var dob: Date = Date()
        )