package mx.itesm.dibujandounmaana.Adaptadores

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
    //Fragmento
    //var listener: RenglonListener? = null

    //Crea cada renglon
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DonacionViewHolder {
        //Cada renglon se crea aqui
        val vista = LayoutInflater.from(parent.context).inflate(R.layout.renglon_donacion, parent, false)
        return DonacionViewHolder(vista)
    }

    //En cada uno de los renglones
    override fun onBindViewHolder(holder: DonacionViewHolder, position: Int) {
        holder.set(arrDonaciones[position])
    }

    //Cuantos renglones tiene el RecyclerView
    //Cuantos paises hay
    override fun getItemCount(): Int {
        return arrDonaciones.size
    }

    fun actualizar(lista: List<Donacion>?) {
        arrDonaciones.clear()
        if (lista!=null){
            arrDonaciones.addAll(lista)
        }
        notifyDataSetChanged()  //Recargar la informacion
    }

    class DonacionViewHolder(vista: View):RecyclerView.ViewHolder(vista) {
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