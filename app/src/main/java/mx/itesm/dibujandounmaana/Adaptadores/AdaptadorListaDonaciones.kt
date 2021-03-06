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
import mx.itesm.dibujandounmaana.model.Donacion


class AdaptadosListaDonaciones (var arrDonaciones: ArrayList<Donacion>):
    RecyclerView.Adapter<AdaptadosListaDonaciones.DonacionViewHolder>()
{

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DonacionViewHolder {
        /* Se crea el elemento del diseño individual del recycler view. en este caso, cada
        renglón de las donaciones registradas */
        val vista = LayoutInflater.from(parent.context).inflate(R.layout.renglon_donacion, parent, false)
        return DonacionViewHolder(vista)
    }

    override fun onBindViewHolder(holder: DonacionViewHolder, position: Int) {
        //Accedemos a cada elemento de la lista (recycler view)
        holder.set(arrDonaciones[position])
    }


    override fun getItemCount(): Int {
        //Obtenemos la cantidad de grupos de datos que estamos obteniendo del servidor
        return arrDonaciones.size
    }

    fun actualizar(lista: List<Donacion>?) {
        //Actualizamos la lista dependiendo de la cantidad de datos registrados
        arrDonaciones.clear()
        if (lista!=null){
            arrDonaciones.addAll(lista)
        }
        notifyDataSetChanged()
    }

    class DonacionViewHolder(vista: View):RecyclerView.ViewHolder(vista) {
        //Asignamos a cada elemento del renglón su correspondiente valor
        private val tvNombreDonacion = vista.findViewById<TextView>(R.id.tvNombreProyecto)
        private val tvMonto = vista.findViewById<TextView>(R.id.tvMonto)
        private val tvFecha= vista.findViewById<TextView>(R.id.tvFecha)

        fun set(donacion: Donacion){
            tvNombreDonacion.text = donacion.nombreProyecto
            tvMonto.text = donacion.montoDonacion.toString()
            tvFecha.text = donacion.fechaDonacion
        }
    }
}