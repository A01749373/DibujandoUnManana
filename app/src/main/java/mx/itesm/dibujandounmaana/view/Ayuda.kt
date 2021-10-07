package mx.itesm.dibujandounmaana.view

import android.app.Activity
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import mx.itesm.dibujandounmaana.R
import mx.itesm.dibujandounmaana.viewmodel.AyudaVM

class Ayuda : AppCompatActivity() {

    companion object {
        fun newInstance() = Ayuda()
    }

    private lateinit var viewModel: AyudaVM

    override fun onCreate( savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ayuda_fragment)
    }



}