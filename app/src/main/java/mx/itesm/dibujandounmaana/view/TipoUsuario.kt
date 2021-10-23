/*
Autores:
* Liam Garay Monroy
* Jorge Chávez Badillo
* Amy Murakami Tsutsumi
* Andrea Vianey Díaz Álvarez
* Ariadna Jocelyn Guzmán Jiménez
*/


package mx.itesm.dibujandounmaana.view

//Librerías
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import mx.itesm.dibujandounmaana.R
import mx.itesm.dibujandounmaana.databinding.ActivityTipoUsuarioBinding

class tipoUsuario : AppCompatActivity() {

    private lateinit var binding: ActivityTipoUsuarioBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        /* Asegura que la vista esté completamente creada y manda a llamar a las funciones
        a los elementos correspondientes, al igual que cambia el tema configurado después del splash
        screen */
        Thread.sleep(2000)
        setTheme(R.style.Theme_DibujandoUnMañana_NoActionBar)
        super.onCreate(savedInstanceState)
        binding = ActivityTipoUsuarioBinding.inflate(layoutInflater)
        setContentView(binding.root)
        configurarEventos()
    }


    private fun configurarEventos() {
        //Configura las acciones de los botones de acuerdo al tipo de usuario
        binding.btnTipoDonante.setOnClickListener {
            val intSesion = Intent(this, IniciarSesionAct::class.java)
            startActivity(intSesion)
        }
        binding.btnTipoEmpresa.setOnClickListener {
            val urlEmpresa = "https://www.dibujando.org.mx/en/enterprises/"
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(urlEmpresa)))
        }
        binding.btnTipoAdministrador.setOnClickListener {
            val intentAdministrador = Intent(this, IniciarSesionAdminAct::class.java)
            startActivity(intentAdministrador)
        }

    }

}