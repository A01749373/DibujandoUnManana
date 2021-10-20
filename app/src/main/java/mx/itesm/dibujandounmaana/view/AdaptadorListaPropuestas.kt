package mx.itesm.dibujandounmaana.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import mx.itesm.dibujandounmaana.ListaPropuestas
import mx.itesm.dibujandounmaana.R
import mx.itesm.dibujandounmaana.databinding.ListaPropuestasFragmentBinding
import mx.itesm.dibujandounmaana.databinding.ListaUsuariosFragmentBinding
import mx.itesm.dibujandounmaana.model.ListaUsuarios
import mx.itesm.dibujandounmaana.model.NuevaPropuesta

class AdaptadorListaPropuestas (var arrPropuestas: ArrayList<NuevaPropuesta>):
    RecyclerView.Adapter<AdaptadorListaPropuestas.PropuestasViewHolder>()
{
    private lateinit var binding: ListaPropuestasFragmentBinding

    //Crea cada renglon
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PropuestasViewHolder {
        //Cada renglon se crea aqui

        val vista = LayoutInflater.from(parent.context).inflate(R.layout.renglon_propuestas, parent, false)
        return PropuestasViewHolder(vista)

    }

    //En cada uno de los renglones
    override fun onBindViewHolder(holder: PropuestasViewHolder, position: Int) {

        holder.set(arrPropuestas[position])

    }

    //Cuantos renglones tiene el RecyclerView
    override fun getItemCount(): Int {
        return arrPropuestas.size
    }

    fun actualizar(lista: List<NuevaPropuesta>?) {
        arrPropuestas.clear()
        if (lista!=null){
            arrPropuestas.addAll(lista)
        }
        notifyDataSetChanged()  //Recargar la informacion
    }

    class PropuestasViewHolder(vista: View): RecyclerView.ViewHolder(vista) {
        private val tvNombreP = vista.findViewById<TextView>(R.id.tvNombreP)
        private val tvDes = vista.findViewById<TextView>(R.id.tvDes)
        private val tvCorreoU = vista.findViewById<TextView>(R.id.tvCorreoU)

        fun set(propuestas: NuevaPropuesta){
            tvNombreP.text = propuestas.nombreProyecto
            tvDes.text = propuestas.descripcion
            tvCorreoU.text = propuestas.correo

        }

    }
}