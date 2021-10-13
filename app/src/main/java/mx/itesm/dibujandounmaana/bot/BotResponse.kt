package mx.itesm.dibujandounmaana.bot

import mx.itesm.dibujandounmaana.bot.Constants.OPEN_GOOGLE
import mx.itesm.dibujandounmaana.bot.Constants.OPEN_SEARCH
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.*

object BotResponse {
    fun basicResponses(_mensaje: String): String {
        val random = (0..2).random()
        val mensaje = _mensaje.lowercase()

        return when {

            // Math calculations
            mensaje.contains("solve") -> {
                val equation: String? = mensaje.substringAfterLast("solve")
                return try {
                    val answer = SolveMath.solveMath(equation ?: "0")
                    "$answer"

                } catch (e: Exception) {
                    "Sorry, I can't solve that."
                }
            }

            // Hello
            mensaje.contains("hello") -> {
                when (random) {
                    0 -> "Hello there!"
                    1 -> "Sup"
                    2 -> "Buongiorno!"
                    else -> "error" }
            }

            // How are you?
            mensaje.contains("how are you") -> {
                when (random) {
                    0 -> "I'm doing fine, thanks!"
                    1 -> "I'm hungry..."
                    2 -> "Pretty good! How about you?"
                    else -> "error"
                }
            }

            // What time is it?
            mensaje.contains("time") && mensaje.contains("?") -> {
                val timeStamp = Timestamp(System.currentTimeMillis())
                val sdf = SimpleDateFormat("dd/MM/yyyy HH:mm")
                val date = sdf.format(Date(timeStamp.time))

                date.toString()
            }

            // Hola
            mensaje.contains("hola") -> {
                when (random) {
                    0 -> "¡Hola! 😃, Bienvenido a la app de Fundación Dibujando un Mañana"
                    1 -> "Hola, ¿Cómo puedo ayudarte?"
                    2 -> "Bienvenido al chat de Fundación DUM, ¿En qué puedo ayudarte?"
                    else -> "error"
                }
            }

            // ¿Cómo puedo donar?
            mensaje.contains("¿cómo puedo donar?") -> {
                when (0) {
                    0 -> "Dirígete hacia el menú deslizable del lado izquierdo\n" +
                            "y selecciona la sección de donaciones, posteriormente selecciona\n" +
                            "el proyecto al que deseas apoyar y por medio de paypal se \n" +
                            "se realiza el cargo de tu donación"
                    else -> "error"
                }
            }

            // ¿Cómo puedo ponerme en contacto?
            mensaje.contains("¿cómo puedo ponerme en contacto?" ) -> {
                when (0) {
                    0 -> "Correo electrónico de DUM: \ngdi.mty@dibujando.org.mx\n" +
                            "Número: 55 2122 5286"
                    else -> "error"
                }
            }

            // Open Google
            mensaje.contains("open") && mensaje.contains("google") -> {
                OPEN_GOOGLE
            }

            // Search on the internet
            mensaje.contains("search") -> {
                OPEN_SEARCH
            }

            // Cuando el programa no entiende la entrada de texto
            else -> {
                when (random) {
                    0 -> "Lo siento, no entiendo..."
                    1 -> "Intenta preguntar algo diferente"
                    2 -> "No se como resolver tu duda :("
                    else -> "error"
                }
            }
        }
    }
}