package mx.itesm.dibujandounmaana.bot

import mx.itesm.dibujandounmaana.bot.Constants.OPEN_GOOGLE
import mx.itesm.dibujandounmaana.bot.Constants.OPEN_SEARCH
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.*

object BotResponse {
    fun basicResponses(_message: String): String {
        val random = (0..2).random()
        val message = _message.lowercase()

        return when {

            // Flips a coin
            message.contains("flip") && message.contains("coin") -> {
                val r = (0..1).random()
                val result = if (r == 0) "heads" else "tails"

                "I flipped a coin and it landed on $result"
            }

            // Math calculations
            message.contains("solve") -> {
                val equation: String? = message.substringAfterLast("solve")
                return try {
                    val answer = SolveMath.solveMath(equation ?: "0")
                    "$answer"

                } catch (e: Exception) {
                    "Sorry, I can't solve that."
                }
            }

            // Hello
            message.contains("hello") -> {
                when (random) {
                    0 -> "Hello there!"
                    1 -> "Sup"
                    2 -> "Buongiorno!"
                    else -> "error" }
            }

            // How are you?
            message.contains("how are you") -> {
                when (random) {
                    0 -> "I'm doing fine, thanks!"
                    1 -> "I'm hungry..."
                    2 -> "Pretty good! How about you?"
                    else -> "error"
                }
            }

            // What time is it?
            message.contains("time") && message.contains("?") -> {
                val timeStamp = Timestamp(System.currentTimeMillis())
                val sdf = SimpleDateFormat("dd/MM/yyyy HH:mm")
                val date = sdf.format(Date(timeStamp.time))

                date.toString()
            }

            // Hola
            message.contains("hola") -> {
                when (random) {
                    0 -> "¡Hola! 😃"
                    1 -> "Saludos..."
                    2 -> "Bienvenido al chat de Fundación DUM, ¿En qué puedo ayudarte?"
                    else -> "error"
                }
            }

            // ¿Cómo puedo donar?
            message.contains("¿cómo puedo donar?") -> {
                when (0) {
                    0 -> "Dirígete hacia el menú deslizable del lado izquierdo\n" +
                            "y selecciona la sección de donaciones, posteriormente selecciona\n" +
                            "el proyecto al que deseas apoyar y por medio de paypal se \n" +
                            "se realiza el cargo de tu donación"
                    else -> "error"
                }
            }

            // Open Google
            message.contains("open") && message.contains("google") -> {
                OPEN_GOOGLE
            }

            // Search on the internet
            message.contains("search") -> {
                OPEN_SEARCH
            }

            // When the programme doesn't understand...
            else -> {
                when (random) {
                    0 -> "I don't understand..."
                    1 -> "Try asking me something different"
                    2 -> "Idk"
                    else -> "error"
                }
            }
        }
    }
}