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
import android.app.Activity.RESULT_OK
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.edit
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import mx.itesm.dibujandounmaana.databinding.PerfilUsuarioFragmentBinding
import mx.itesm.dibujandounmaana.viewmodel.PerfilUsuarioVM
import mx.itesm.dibujandounmaana.R
import android.content.Intent
import android.widget.Button
import android.widget.ImageView
import android.app.ProgressDialog
import com.google.firebase.storage.FirebaseStorage
import android.net.Uri
import android.widget.Toast
import java.io.File
import android.graphics.BitmapFactory



class perfilUsuario : Fragment() {

    companion object{
        fun newInstance() = perfilUsuario()
        val IMAGE_REQUEST_CODE = 100
    }

    private val viewModel: PerfilUsuarioVM by viewModels()

    private lateinit var binding: PerfilUsuarioFragmentBinding

    private lateinit var button: Button
    private lateinit var imageView: ImageView
    private lateinit var imageURi: Uri


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //Crea la vista de acuerdo al xml asignado
        binding = PerfilUsuarioFragmentBinding.inflate(layoutInflater)

        return binding.root
    }

    private fun pickImageGallery(){
        //Realiza la actividad para que el usuario escoja una foto de perfil de su dispositivo
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, IMAGE_REQUEST_CODE)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?){
        super.onActivityResult(requestCode, resultCode, data)
        //Guardar Imagen seleccionada por el usuario
        if(requestCode == IMAGE_REQUEST_CODE && resultCode == RESULT_OK){
            imageURi = data?.data!!
            val progressDialog = ProgressDialog(this.requireContext())
            progressDialog.setMessage("Subiendo archivo")
            progressDialog.setCancelable(false)
            progressDialog.show()
            val preferencias = this.requireContext().getSharedPreferences("Usuario", Context.MODE_PRIVATE)
            val correo = preferencias.getString("Correo", "-1")
            if (correo != "-1") {
                println("PerfilView Este es el correo del usuario: $correo")
            } else {
                println("PerfilView No funciono")
            }
            val fileName = correo
            val storageReference = FirebaseStorage.getInstance().getReference("Imagenes/$fileName")
            imageView.setImageURI(data?.data)
            storageReference.putFile(imageURi).
                addOnSuccessListener {
                    Toast.makeText(this.requireContext(),"Imagen cambiada",Toast.LENGTH_SHORT).show()
                    if (progressDialog.isShowing){
                        progressDialog.dismiss()
                    }
                    preferencias.edit {
                        putBoolean("ImagenPerfil", true)
                        commit()
                    }
                }.addOnFailureListener{
                    if (progressDialog.isShowing){
                        progressDialog.dismiss()
                    }
                Toast.makeText(this.requireContext(),"Error al subir la imagen",Toast.LENGTH_SHORT).show()
            }
        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        /* Asegura que la vista esté completamente creada y manda a llamar a las funciones
        a los elementos correspondientes */
        super.onViewCreated(view, savedInstanceState)
        configurarObservadores()
        configurarEventos()
    }

    private fun configurarEventos() {
        //Configura las acciones de los botones de acuerdo a lo que el usuario quiera interactuar en el perfil
        val preferencias = this.requireContext().getSharedPreferences("Usuario", Context.MODE_PRIVATE)
        val correo = preferencias.getString("Correo","-1")
        viewModel.descargarDatosUsuario(correo.toString())
        binding.btnDonacion.setOnClickListener {
            findNavController().navigate(R.id.action_nav_perfil_to_lista_donaciones)
        }
        button = binding.btnEditarFoto
        imageView = binding.imgPerfil
        button.setOnClickListener {
            pickImageGallery()
        }


    }

    private fun configurarObservadores() {
        //Observa las respuestas obtenidas del servidor y configura elementos del xml
        viewModel.respuesta.observe(viewLifecycleOwner) { respuesta ->
            binding.tvNombre.text = respuesta.nombre
            binding.tvCorreo.text = respuesta.correo
            binding.tvHistorial.text = respuesta.historial.toString()
        }
        //Poner la imagen de perfil
        val preferencias = this.requireContext().getSharedPreferences("Usuario", Context.MODE_PRIVATE)
        val correo = preferencias.getString("Correo","-1").toString()
        val storageRef = FirebaseStorage.getInstance().reference.child("Imagenes/$correo")
        val archivo = File.createTempFile("temp","jpg")
        storageRef.getFile(archivo).addOnSuccessListener {
            val bitmap = BitmapFactory.decodeFile(archivo.absolutePath)
            binding.imgPerfil.setImageBitmap(bitmap)
        }
    }
}