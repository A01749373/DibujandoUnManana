/*
Autores:
* Liam Garay Monroy
* Jorge Chávez Badillo
* Amy Murakami Tsutsumi
* Andrea Vianey Díaz Álvarez
* Ariadna Jocelyn Guzmán Jiménez
*/
package mx.itesm.dibujandounmaana.bot

//Librerías
import mx.itesm.dibujandounmaana.bot.Constants.OPEN_GOOGLE
import mx.itesm.dibujandounmaana.bot.Constants.OPEN_SEARCH
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.*

object BotResponse {
    //Contiene las respuestas preterminadas para preguntas frecuentes de uso de la app
    fun basicResponses(_mensaje: String): String {
        val random = (0..2).random()
        val mensaje = _mensaje.lowercase()

        return when {

            // Hola
            mensaje.contains("hola") -> {
                when (random) {
                    0 -> "¡Hola! 😃, Bienvenido a la app de Fundación Dibujando un Mañana\n\n" +
                            "Envía el número de la pregunta que necesites realizar:\n" +
                            "1. ¿Cómo puedo donar?\n 2. ¿Dónde puedo ver mis donaciones?\n" +
                            "3. ¿De qué otra manera puedo apoyar?\n 4. ¿Cómo puedo comprar regalos con causa?\n" +
                            "5. ¿Dónde puedo tener asistencia personal?"
                    1 -> "Hola, ¿Cómo puedo ayudarte?\n\n" +
                            "Envía el número de la pregunta que necesites realizar:\n" +
                            "1. ¿Cómo puedo donar?\n 2. ¿Dónde puedo ver mis donaciones?\n" +
                            "3. ¿De qué otra manera puedo apoyar?\n 4. ¿Cómo puedo comprar regalos con causa?\n" +
                            "5. ¿Dónde puedo tener asistencia personal?"
                    2 -> "Bienvenido al chat de Fundación DUM, ¿En qué puedo ayudarte?\n\n" +
                            "Envía el número de la pregunta que necesites realizar:\n" +
                            "1. ¿Cómo puedo donar?\n 2. ¿Dónde puedo ver mis donaciones?\n" +
                            "3. ¿De qué otra manera puedo apoyar?\n 4. ¿Cómo puedo comprar regalos con causa?\n" +
                            "5. ¿Dónde puedo tener asistencia personal?"
                    else -> "error"
                }
            }

            // ¿Cómo puedo donar?
            mensaje.contains("1") -> {
                when (0) {
                    0 -> "Dirígete hacia el menú deslizable del lado izquierdo\n" +
                            "y selecciona la sección de donaciones, posteriormente selecciona\n" +
                            "el proyecto al que deseas apoyar y por medio de paypal se \n" +
                            "se realiza el cargo de tu donación"
                    else -> "error"
                }
            }

            // ¿Cómo puedo ver mis donaciones?
            mensaje.contains("2") -> {
                when (0) {
                    0 ->    "Dirígete hacia el menú deslizable del lado izquierdo y selecciona \n" +
                            "la sección de perfil, posteriormente selecciona *mis donaciones*,\n" +
                            "en esta sección aparecerá tu historial."
                    else -> "error"
                }
            }


            //Otra forma
            mensaje.contains("3") -> {
                when (0) {
                    0 -> "Puedes ayudarnos con voluntariados o donaciones materiales.\n" +
                            "Cuéntanos tu propuesta en Nuestros proyectos -> Mejorar un proyecto.\n"
                    else -> "error"
                }
            }

            // ¿Cómo puedo comprar regalos?
            mensaje.contains("4") -> {
                when (0) {
                    0 -> "Ingresa a nuestra página https://dibujando.org.mx/regalos-con-causa/\n" +
                            "ó comunicate con Alicia Chapa: achapa@dibujando.org.mx\n" +
                            "María Fernanda Ladrón de Guevara: desarrollo@dibujando.org.mx"
                    else -> "error"
                }
            }


            // ¿Cómo puedo ponerme en contacto?
            mensaje.contains("5" ) -> {
                when (0) {
                    0 -> "Mándanos un correo electrónico a DUM: \ngdi.mty@dibujando.org.mx\n" +
                            "o llámanos al 55 2122 5286"
                    else -> "error"
                }
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