/*
Autores:
* Liam Garay Monroy
* Jorge Chávez Badillo
* Amy Murakami Tsutsumi
* Andrea Vianey Díaz Álvarez
* Ariadna Jocelyn Guzmán Jiménez
*/


package mx.itesm.dibujandounmaana

//Librerías
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import com.facebook.login.LoginManager
import mx.itesm.dibujandounmaana.databinding.ActivityMainBinding
import mx.itesm.dibujandounmaana.view.Ayuda
import com.firebase.ui.auth.AuthUI
import mx.itesm.dibujandounmaana.view.CrearCuentaAdminAct
import mx.itesm.dibujandounmaana.view.tipoUsuario


class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private lateinit var navViewPrueba: NavigationView
    private lateinit var drawerLayoutPrueba: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        //Abre la aplicación con el tema iniciar y configura las acciones de los elementos de entrada
        Thread.sleep(2000)
        setTheme(R.style.Theme_DibujandoUnMañana_NoActionBar)
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.appBarMain.toolbar)

        binding.appBarMain.fab.setOnClickListener {
            val intent = Intent(this, Ayuda::class.java)
            startActivity(intent)
        }
        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_main)

        drawerLayoutPrueba = binding.drawerLayout
        navViewPrueba = binding.navView
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_donar, R.id.nav_quienes, R.id.nav_regalos,
                R.id.nav_perfil, R.id.nav_contactanos, R.id.nav_proyectos,
                R.id.configuraciones
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
        verificaTipoUsuario()
    }

    private fun verificaTipoUsuario() {
        //Habilita o deshabilita el item de administrador de acuerdo al tipo de usuario
        val preferencias = this.getSharedPreferences("Usuario", MODE_PRIVATE)
        val correo = preferencias.getString("Correo","-1")
        val tipoUsuario = preferencias.getString("TipoUsuario", "-1")
        var menu = navViewPrueba.menu


        println("Tipo usuario: $tipoUsuario")
        if (tipoUsuario == "administrador") {
            println("Se verificó el tipo de usuario")
            //menu.removeItem(9)
            //menu.setGroupVisible(0, false)
            menu.getItem(8).setVisible(true)
        }
        if (tipoUsuario == "normal") {
            menu.getItem(8).setVisible(false)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        //Crea el menu de opciones
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    private fun logout(){
        //Método para cerrar sesión en cualquier tipo de inicio de sesión
        println("Sesion cerrada")
        val preferencias = this.getSharedPreferences("Usuario", MODE_PRIVATE)
        val correo = preferencias.getString("Correo","-1")
        preferencias.edit {
            putString("Correo", "-1")
            commit()
        }
        //Google
        AuthUI.getInstance().signOut(this)
        //Facebook
        LoginManager.getInstance().logOut()
        println("Este es el correo: ${correo}")
        val tipo = Intent(this, tipoUsuario::class.java)
        startActivity(tipo)
    }

        override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    private fun toCrearCuentaAdmin() {
        val intentCrearCuentaAdmin = Intent(this, CrearCuentaAdminAct::class.java)
        startActivity(intentCrearCuentaAdmin)
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        //Configura el botón de cerrar sesión en la aplicación
        R.id.btnLogout -> {
            println("Hacer logout")
            logout()
            true
        }
        else -> {
            super.onOptionsItemSelected(item)
        }
    }
}