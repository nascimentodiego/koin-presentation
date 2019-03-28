package br.com.diegonascimento.koinpresentation.feature.films.adapters

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import br.com.diegonascimento.koinpresentation.R
import br.com.diegonascimento.koinpresentation.model.Film

class ItemFilmAdapter(var mList: List<Film>) :
        androidx.recyclerview.widget.RecyclerView.Adapter<androidx.recyclerview.widget.RecyclerView.ViewHolder>() {

    private inner class CellViewHolder(itemView: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(itemView), View.OnClickListener {
        val txt_title: TextView = itemView.findViewById(R.id.txt_title)
        val txt_description: TextView = itemView.findViewById(R.id.txt_description)

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(view: View) {

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            androidx.recyclerview.widget.RecyclerView.ViewHolder {
        val vItem = LayoutInflater.from(parent.context).inflate(R.layout.adapter_item_film, parent, false)
        return CellViewHolder(vItem)
    }


    override fun getItemCount(): Int {
        return mList.size
    }

    override fun onBindViewHolder(holder: androidx.recyclerview.widget.RecyclerView.ViewHolder, position: Int) {
        if (holder is CellViewHolder) {
            holder.txt_title.text = mList[position].title
            holder.txt_description.text = mList[position].description
        }
    }
}