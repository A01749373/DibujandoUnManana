package mx.itesm.dibujandounmaana.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import mx.itesm.dibujandounmaana.R
import mx.itesm.dibujandounmaana.viewmodel.ComoAyudarVM

class ComoAyudar : Fragment() {

    companion object {
        fun newInstance() = ComoAyudar()
    }

    private lateinit var viewModel: ComoAyudarVM

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.nav_como_ayudar, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ComoAyudarVM::class.java)
        // TODO: Use the ViewModel
    }

}