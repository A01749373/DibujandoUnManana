package mx.itesm.dibujandounmaana.model

data class Chat (
    var id: String = "",
    var name: String = "",
    var users: List<String> = emptyList())