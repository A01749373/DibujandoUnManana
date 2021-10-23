package mx.itesm.dibujandounmaana.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.*
import mx.itesm.dibujandounmaana.*
import mx.itesm.dibujandounmaana.Adaptadores.MessagingAdapter
import mx.itesm.dibujandounmaana.bot.Constants.RECEIVE_ID
import mx.itesm.dibujandounmaana.bot.Constants.SEND_ID
import mx.itesm.dibujandounmaana.bot.BotResponse
import mx.itesm.dibujandounmaana.model.Message
import mx.itesm.dibujandounmaana.viewmodel.AyudaVM
import mx.itesm.dibujandounmaana.bot.Time

class Ayuda : AppCompatActivity() {

    companion object {
        fun newInstance() = Ayuda()
    }

    private lateinit var viewModel: AyudaVM
    private lateinit var adapter: MessagingAdapter
    private val botList = listOf("Jorge", "Amy", "Ariadna", "Andrea", "Liam")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ayuda_fragment)

        recyclerView()

        clickEvents()

        val random = (0..3).random()
        customMessage("Hola! Estás hablando con ${botList[random]}, ¿cómo puedo ayudarte?", 500)
        customMessage("Envía el número de la pregunta que necesites realizar:\n" +
                "1. ¿Cómo puedo donar?\n 2. ¿Dónde puedo ver mis donaciones?\n" +
                "3. ¿De qué otra manera puedo apoyar?\n 4. ¿Cómo puedo comprar regalos con causa?\n" +
                "5. ¿Dónde puedo tener asistencia personal?", 1000)
    }

    private fun clickEvents() {
        // Enviar un mensaje
        findViewById<Button>(R.id.btn_send).setOnClickListener {
            sendMessage()
        }

        // Desplazarse hacia atrás a la posición correcta cuando el usuario hace clic en la vista de texto
        findViewById<EditText>(R.id.et_message).setOnClickListener {
            GlobalScope.launch {
                delay(100)

                withContext(Dispatchers.Main) {
                    findViewById<RecyclerView>(R.id.rv_messages).scrollToPosition(adapter.itemCount-1)
                }
            }
        }
    }

    private fun recyclerView() {
        adapter = MessagingAdapter()
        findViewById<RecyclerView>(R.id.rv_messages).adapter = adapter
        findViewById<RecyclerView>(R.id.rv_messages).layoutManager = LinearLayoutManager(applicationContext)
    }

    // Función para envíar un mensaje al bot
    private fun sendMessage() {
        val message = findViewById<EditText>(R.id.et_message).text.toString()
        val timeStamp = Time.timeStamp()

        if (message.isNotEmpty()) {
            findViewById<EditText>(R.id.et_message).setText("")

            adapter.insertMessage(Message(message, SEND_ID, timeStamp))
            findViewById<RecyclerView>(R.id.rv_messages).scrollToPosition(adapter.itemCount-1)

            botResponse(message)
        }
    }

    // Función que consiste en la obtencion de la respuesta del bot
    private fun botResponse(message: String) {
        val timeStamp = Time.timeStamp()

        GlobalScope.launch {
            delay(1000)
            withContext(Dispatchers.Main) {
                val response = BotResponse.basicResponses(message)

                adapter.insertMessage(Message(response, RECEIVE_ID, timeStamp))
                findViewById<RecyclerView>(R.id.rv_messages).scrollToPosition(adapter.itemCount-1)
            }
        }
    }

    override fun onStart() {
        super.onStart()
        GlobalScope.launch {
            delay(1000)
            withContext(Dispatchers.Main) {
                findViewById<RecyclerView>(R.id.rv_messages).scrollToPosition(adapter.itemCount-1)
            }
        }
    }


    private fun customMessage(message: String, retardo: Long) {
        GlobalScope.launch {
            delay(retardo)
            withContext(Dispatchers.Main) {
                val timeStamp = Time.timeStamp()
                adapter.insertMessage(Message(message, RECEIVE_ID, timeStamp))

                findViewById<RecyclerView>(R.id.rv_messages).scrollToPosition(adapter.itemCount-1)
            }
        }
    }
}