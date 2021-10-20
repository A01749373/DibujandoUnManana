package mx.itesm.dibujandounmaana.Adaptadores

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
    private lateinit var binding: ListaUsuariosFragmentBinding

    //Crea cada renglon
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsuariosViewHolder {
        //Cada renglon se crea aqui

        val vista = LayoutInflater.from(parent.context).inflate(R.layout.renglon_usuarios, parent, false)
        return UsuariosViewHolder(vista)

    }

    //En cada uno de los renglones
    override fun onBindViewHolder(holder: UsuariosViewHolder, position: Int) {

        holder.set(arrUsuarios[position])

    }

    //Cuantos renglones tiene el RecyclerView
    override fun getItemCount(): Int {
        return arrUsuarios.size
    }

    fun actualizar(lista: List<ListaUsuarios>?) {
        arrUsuarios.clear()
        if (lista!=null){
            arrUsuarios.addAll(lista)
        }
        notifyDataSetChanged()  //Recargar la informacion
    }

    class UsuariosViewHolder(vista: View): RecyclerView.ViewHolder(vista) {
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