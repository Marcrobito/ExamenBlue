package com.marcrobito.examen.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.marcrobito.examen.R
import com.marcrobito.examen.network.hackernews.Api
import kotlinx.android.synthetic.main.item.view.*
import retrofit2.Call
import com.marcrobito.examen.R.layout.item



class CustomAdapter(private val items: List<Int>, val context:Context ): RecyclerView.Adapter<CustomAdapter.VH>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(parent)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size
    class VH(parent: ViewGroup) : RecyclerView.ViewHolder(

        LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)) {
        fun bind(item: Int) = with(itemView) {
            title.text = item.toString()
            itemView.setOnClickListener(object : View.OnClickListener {
                override fun onClick(v: View) {

                }
            })

        }
    }
}

interface OnItemClickListener {
    fun onItemClick(item: Int)
}