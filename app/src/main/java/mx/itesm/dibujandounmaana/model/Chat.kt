/*
Autores:
* Liam Garay Monroy
* Jorge Chávez Badillo
* Amy Murakami Tsutsumi
* Andrea Vianey Díaz Álvarez
* Ariadna Jocelyn Guzmán Jiménez
*/

package mx.itesm.dibujandounmaana.model

//Contiene los tipos de datos de un chat
data class Chat (
    var id: String = "",
    var name: String = "",
    var users: List<String> = emptyList())