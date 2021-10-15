package mx.itesm.dibujandounmaana.view

import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import mx.itesm.dibujandounmaana.R
import mx.itesm.dibujandounmaana.model.Proyecto
import com.google.firebase.storage.FirebaseStorage
import mx.itesm.dibujandounmaana.databinding.NavProyectosBinding
import java.io.File


class AdaptadorListaProyectos (var arrProyectos: ArrayList<Proyecto>):
    RecyclerView.Adapter<AdaptadorListaProyectos.ProyectosViewHolder>()
{
    private lateinit var binding: NavProyectosBinding

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
        private val ivImagen = vista.findViewById<ImageView>(R.id.ivProyectoImg)

        fun set(proyecto: Proyecto){
            tvProyectos.text = proyecto.proyecto
            tvDescripcion.text = proyecto.descrip
            val storageRef = FirebaseStorage.getInstance().reference.child("ImagenesProyectos/${proyecto.idproyecto}.png")
            val archivo = File.createTempFile("temp","png")
            storageRef.getFile(archivo).addOnSuccessListener {
                val bitmap = BitmapFactory.decodeFile(archivo.absolutePath)
                ivImagen.setImageBitmap(bitmap)
            }


        }

    }
}