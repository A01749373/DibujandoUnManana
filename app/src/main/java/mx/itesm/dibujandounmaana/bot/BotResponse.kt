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

            // ¿Cómo puedo comprar regalos?
            mensaje.contains("¿cómo puedo comprar regalos con causa?") -> {
                when (0) {
                    0 -> "Ingresa a nuestra página https://dibujando.org.mx/regalos-con-causa/\n" +
                            "ó comunicate con Alicia Chapa: achapa@dibujando.org.mx\n" +
                            "María Fernanda Ladrón de Guevara: desarrollo@dibujando.org.mx"
                    else -> "error"
                }
            }


            // ¿Cómo puedo comprar ver donaciones?
            mensaje.contains("¿dónde puedo ver mis donaciones?") -> {
                when (0) {
                    0 ->    "Dirígete hacia el menú deslizable del lado izquierdo y selecciona \n" +
                            "la sección de perfil, posteriormente selecciona *mis donaciones*,\n" +
                            "en esta sección aparecerá tu historial."
                    else -> "error"
                }
            }

            //Otra forma
            mensaje.contains("¿de qué otra manera puedo apoyar?") -> {
                when (0) {
                    0 -> "Puedes ayudarnos con voluntariados o donaciones materiales.\n" +
                            "Cuéntanos tu propuesta en Nuestros proyectos -> Mejorar un proyecto.\n"
                    else -> "error"
                }
            }

            // ¿Cómo puedo ponerme en contacto?
            mensaje.contains("¿dónde puedo tener asistencia personal?" ) -> {
                when (0) {
                    0 -> "Mándanos un correo electrónico a DUM: \ngdi.mty@dibujando.org.mx\n" +
                            "o llámanos al 55 2122 5286"
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