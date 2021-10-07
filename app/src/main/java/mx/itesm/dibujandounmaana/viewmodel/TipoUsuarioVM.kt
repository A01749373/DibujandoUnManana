package mx.itesm.dibujandounmaana.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TipoUsuarioVM : ViewModel() {
    val respuesta = MutableLiveData<String>()
}