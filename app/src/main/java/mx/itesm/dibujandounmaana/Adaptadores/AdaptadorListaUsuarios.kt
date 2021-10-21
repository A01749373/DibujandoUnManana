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
import mx.itesm.dibujandounmaana.databinding.ListaUsuariosFragmentBinding
import mx.itesm.dibujandounmaana.model.ListaUsuarios

class AdaptadorListaUsuarios (var arrUsuarios: ArrayList<ListaUsuarios>):
    RecyclerView.Adapter<AdaptadorListaUsuarios.UsuariosViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsuariosViewHolder {
        /* Se crea el elemento del diseño individual del recycler view. en este caso, cada
        renglón de los usuarios registrados */
        val vista = LayoutInflater.from(parent.context).inflate(R.layout.renglon_usuarios, parent, false)
        return UsuariosViewHolder(vista)

    }

    override fun onBindViewHolder(holder: UsuariosViewHolder, position: Int) {
        //Accedemos a cada elemento de la lista (recycler view)
        holder.set(arrUsuarios[position])

    }

    override fun getItemCount(): Int {
        //Obtenemos la cantidad de grupos de datos que estamos obteniendo del servidor
        return arrUsuarios.size
    }

    fun actualizar(lista: List<ListaUsuarios>?) {
        //Actualizamos la lista dependiendo de la cantidad de datos registrados
        arrUsuarios.clear()
        if (lista!=null){
            arrUsuarios.addAll(lista)
        }
        notifyDataSetChanged()
    }

    class UsuariosViewHolder(vista: View): RecyclerView.ViewHolder(vista) {
        //Asignamos a cada elemento del renglón su correspondiente valor
        private val tvNombres = vista.findViewById<TextView>(R.id.tvNombreUsuario)
        private val tvCorreos = vista.findViewById<TextView>(R.id.tvCorreoUsuario)
        private val tvMontos = vista.findViewById<TextView>(R.id.tvMontoTotal)
        private val tvProyectoDonado = vista.findViewById<TextView>(R.id.tvNProyecto)

        fun set(usuarios: ListaUsuarios){
            tvNombres.text = usuarios.nombre
            tvCorreos.text = usuarios.usuarioCorreo
            tvMontos.text = usuarios.montoTotal
            tvProyectoDonado.text = usuarios.nombreProyectoDonar

        }

    }
}