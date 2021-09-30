package mx.itesm.dibujandounmaana

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class perfilUsuario : Fragment() {

    companion object {
        fun newInstance() = perfilUsuario()
    }

    private lateinit var viewModel: PerfilUsuarioVM

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.perfil_usuario_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(PerfilUsuarioVM::class.java)
        // TODO: Use the ViewModel
    }

}