package com.example.aplikasicheckin

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.aplikasicheckin.model.Data
import kotlinx.android.synthetic.main.lokasi_item_layout.view.*

/**
 * Created by alimustofa on 19/02/18.
 */
class DataAdapter : RecyclerView.Adapter<DataAdapter.DataViewHolder>() {

    var datas: MutableList<Data>? = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): DataViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.lokasi_item_layout, parent, false)
        return DataViewHolder(view)
    }

    override fun getItemCount(): Int {
        return datas?.size!!
    }

    override fun onBindViewHolder(holder: DataViewHolder?, position: Int) {
        holder?.itemView?.tv_nama_tempat?.text = datas?.get(position)?.nama
        holder?.itemView?.tv_keterangan?.text = datas?.get(position)?.keterangan
        holder?.itemView?.tv_kontributor?.text = datas?.get(position)?.kontributor
        holder?.itemView?.tv_latitude?.text = datas?.get(position)?.lat
        holder?.itemView?.tv_longitude?.text = datas?.get(position)?.lon
    }

    inner class DataViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
}