package mx.itesm.dibujandounmaana.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import mx.itesm.dibujandounmaana.R
import mx.itesm.dibujandounmaana.model.Proyecto

class AdaptadorListaProyectos (var arrProyectos: ArrayList<Proyecto>):
    RecyclerView.Adapter<AdaptadorListaProyectos.ProyectosViewHolder>()
{

    //Crea cada renglon
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProyectosViewHolder {
        //Cada renglon se crea aqui
        val vista = LayoutInflater.from(parent.context).inflate(R.layout.renglon_proyectos, parent, false)
        return ProyectosViewHolder(vista)
    }

    //En cada uno de los renglones
    override fun onBindViewHolder(holder: ProyectosViewHolder, position: Int) {
        holder.set(arrProyectos[position])
    }

    //Cuantos renglones tiene el RecyclerView
    //Cuantos proyectos hay
    override fun getItemCount(): Int {
        return arrProyectos.size
    }

    fun actualizar(lista: List<Proyecto>?) {
        arrProyectos.clear()
        if (lista!=null){
            arrProyectos.addAll(lista)
        }
        notifyDataSetChanged()  //Recargar la informacion
    }

    class ProyectosViewHolder(vista: View): RecyclerView.ViewHolder(vista) {
        private val tvProyectos = vista.findViewById<TextView>(R.id.tvProyecto)
        private val tvDescripcion = vista.findViewById<TextView>(R.id.tvDescripcion)

        fun set(proyecto: Proyecto){
            tvProyectos.text = proyecto.proyecto
            tvDescripcion.text = proyecto.descrip
        }
    }
}