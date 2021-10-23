/*
Autores:
* Liam Garay Monroy
* Jorge Chávez Badillo
* Amy Murakami Tsutsumi
* Andrea Vianey Díaz Álvarez
* Ariadna Jocelyn Guzmán Jiménez
*/

package mx.itesm.dibujandounmaana.model

//Librerías
import java.util.*


//Contiene los datos que se almacenan de inicio en un mensaje
data class Mensaje (
    var message: String= "",
    var from: String = "",
    var dob: Date = Date()
        )