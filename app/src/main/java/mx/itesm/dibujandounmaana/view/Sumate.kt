package mx.itesm.dibujandounmaana.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import mx.itesm.dibujandounmaana.R
import mx.itesm.dibujandounmaana.viewmodel.SumateVM

class Sumate : Fragment() {

    companion object {
        fun newInstance() = Sumate()
    }

    private lateinit var viewModel: SumateVM

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.nav_sumate, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(SumateVM::class.java)
        // TODO: Use the ViewModel
    }

}