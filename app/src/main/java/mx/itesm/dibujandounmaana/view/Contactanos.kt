package mx.itesm.dibujandounmaana.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import mx.itesm.dibujandounmaana.R
import mx.itesm.dibujandounmaana.databinding.NavContactanosFragmentBinding
import mx.itesm.dibujandounmaana.viewmodel.ContactanosVM

class Contactanos : Fragment() {

    private val viewModel: ContactanosVM by viewModels()
    private lateinit var binding: NavContactanosFragmentBinding
    companion object {
        fun newInstance() = Contactanos()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= NavContactanosFragmentBinding.inflate(layoutInflater)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //configurarObservadores()
        configurarEventos()
    }

    // Configuración del listener para los botones que llevan al usuario a las diferentes redes sociales
    // de la Fundación Dibujando un Mañana
    private fun configurarEventos(){
        val facebook= "https://www.facebook.com/DibujandoUnManana/"
        val twitter = "https://twitter.com/FDibujando"
        val instagram = "https://www.instagram.com/fdibujando/"
        val linkedin = "https://www.linkedin.com/company/fundaci%C3%B3n-dibujando-un-manana"
        val youtube = "https://www.youtube.com/user/FDibujando/"
        binding.ivFB.setOnClickListener{
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(facebook)))
        }
        binding.ivTW.setOnClickListener{
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(twitter)))
        }
        binding.ivIG.setOnClickListener{
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(instagram)))
        }
        binding.ivLI.setOnClickListener{
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(linkedin)))
        }
        binding.ivYT.setOnClickListener{
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(youtube)))
        }
    }

}