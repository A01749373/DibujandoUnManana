/*
Autores:
* Liam Garay Monroy
* Jorge Chávez Badillo
* Amy Murakami Tsutsumi
* Andrea Vianey Díaz Álvarez
* Ariadna Jocelyn Guzmán Jiménez
*/

package mx.itesm.dibujandounmaana.Adaptadores

//Librerias
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import mx.itesm.dibujandounmaana.R
import mx.itesm.dibujandounmaana.databinding.ListaPropuestasFragmentBinding
import mx.itesm.dibujandounmaana.model.NuevaPropuesta

class AdaptadorListaPropuestas (var arrPropuestas: ArrayList<NuevaPropuesta>):
    RecyclerView.Adapter<AdaptadorListaPropuestas.PropuestasViewHolder>()
{

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PropuestasViewHolder {
        /* Se crea el elemento del diseño individual del recycler view. en este caso, cada
        renglón de las propuestas registradas */
        val vista = LayoutInflater.from(parent.context).inflate(R.layout.renglon_propuestas, parent, false)
        return PropuestasViewHolder(vista)
    }

    override fun onBindViewHolder(holder: PropuestasViewHolder, position: Int) {
        //Accedemos a cada elemento de la lista (recycler view)
        holder.set(arrPropuestas[position])
    }

    override fun getItemCount(): Int {
        //Obtenemos la cantidad de grupos de datos que estamos obteniendo del servidor
        return arrPropuestas.size
    }

    fun actualizar(lista: List<NuevaPropuesta>?) {
        //Actualizamos la lista dependiendo de la cantidad de datos registrados
        arrPropuestas.clear()
        if (lista!=null){
            arrPropuestas.addAll(lista)
        }
        notifyDataSetChanged()
    }

    class PropuestasViewHolder(vista: View): RecyclerView.ViewHolder(vista) {
        //Asignamos a cada elemento del renglón su correspondiente valor
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