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
import mx.itesm.dibujandounmaana.bot.Constants.OPEN_GOOGLE
import mx.itesm.dibujandounmaana.bot.Constants.OPEN_SEARCH
import mx.itesm.dibujandounmaana.bot.Constants.RECEIVE_ID
import mx.itesm.dibujandounmaana.bot.Constants.SEND_ID
import mx.itesm.dibujandounmaana.bot.BotResponse
import mx.itesm.dibujandounmaana.bot.Time
import mx.itesm.dibujandounmaana.model.Message
import mx.itesm.dibujandounmaana.viewmodel.AyudaVM

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
        customMessage("Hola! Estás hablando con ${botList[random]}, ¿cómo puedo ayudarte?")
    }

    private fun clickEvents() {
        //Send a message
        findViewById<Button>(R.id.btn_send).setOnClickListener {
            sendMessage()
        }

        //Scroll back to correct position when user clicks on text view
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

    private fun botResponse(message: String) {
        val timeStamp = Time.timeStamp()

        GlobalScope.launch {
            delay(1000)
            withContext(Dispatchers.Main) {
                val response = BotResponse.basicResponses(message)

                adapter.insertMessage(Message(response, RECEIVE_ID, timeStamp))
                findViewById<RecyclerView>(R.id.rv_messages).scrollToPosition(adapter.itemCount-1)

                when (response) {
                    OPEN_GOOGLE -> {
                        val site = Intent(Intent.ACTION_VIEW)
                        site.data = Uri.parse("https://google.com/")
                        startActivity(site)
                    }
                    OPEN_SEARCH -> {
                        val site = Intent(Intent.ACTION_VIEW)
                        val searchTerm: String? = message.substringAfter("search")
                        site.data = Uri.parse("https://www.google.com/search?&q=$searchTerm")
                        startActivity(site)
                    }
                }
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


    private fun customMessage(message: String) {
        GlobalScope.launch {
            delay(1000)
            withContext(Dispatchers.Main) {
                val timeStamp = Time.timeStamp()
                adapter.insertMessage(Message(message, RECEIVE_ID, timeStamp))

                findViewById<RecyclerView>(R.id.rv_messages).scrollToPosition(adapter.itemCount-1)
            }
        }
    }
}