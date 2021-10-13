package mx.itesm.dibujandounmaana.view

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



class perfilUsuario : Fragment() {

    companion object{
        fun newInstance() = perfilUsuario()
        val IMAGE_REQUEST_CODE = 100
    }

    private val viewModel: PerfilUsuarioVM by viewModels()

    private lateinit var binding: PerfilUsuarioFragmentBinding

    private lateinit var button: Button
    private lateinit var imageView: ImageView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = PerfilUsuarioFragmentBinding.inflate(layoutInflater)

        return binding.root
    }

    private fun pickImageGallery(){
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, IMAGE_REQUEST_CODE)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?){
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == IMAGE_REQUEST_CODE && resultCode == RESULT_OK){
            imageView.setImageURI(data?.data)
        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configurarObservadores()
        configurarEventos()
    }

    private fun configurarEventos() {
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
        viewModel.respuesta.observe(viewLifecycleOwner) { respuesta ->
            binding.tvNombre.text = respuesta.nombre
            binding.tvCorreo.text = respuesta.correo
            binding.tvHistorial.text = respuesta.historial.toString()
        }
    }
}