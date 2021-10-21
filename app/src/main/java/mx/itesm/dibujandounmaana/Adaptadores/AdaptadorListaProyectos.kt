/*
Autores:
* Liam Garay Monroy
* Jorge Chávez Badillo
* Amy Murakami Tsutsumi
* Andrea Vianey Díaz Álvarez
* Ariadna Jocelyn Guzmán Jiménez
*/

package mx.itesm.dibujandounmaana.Adaptadores

//Librerías
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
    /* Se crea el elemento del diseño individual del recycler view. en este caso, cada
        renglón de los proyectos registrados */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProyectosViewHolder {
        val vista = LayoutInflater.from(parent.context).inflate(R.layout.renglon_proyectos, parent, false)
        return ProyectosViewHolder(vista)

    }

    override fun onBindViewHolder(holder: ProyectosViewHolder, position: Int) {
        //Accedemos a cada elemento de la lista (recycler view)
        holder.set(arrProyectos[position])
    }

    override fun getItemCount(): Int {
        //Obtenemos la cantidad de grupos de datos que estamos obteniendo del servidor
        return arrProyectos.size
    }

    fun actualizar(lista: List<Proyecto>?) {
        //Actualizamos la lista dependiendo de la cantidad de datos registrados
        arrProyectos.clear()
        if (lista!=null){
            arrProyectos.addAll(lista)
        }
        notifyDataSetChanged()
    }

    class ProyectosViewHolder(vista: View): RecyclerView.ViewHolder(vista) {
        //Asignamos a cada elemento del renglón su correspondiente valor
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