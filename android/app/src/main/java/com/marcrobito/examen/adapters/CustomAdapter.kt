package com.marcrobito.examen.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.marcrobito.examen.R
import com.marcrobito.examen.network.hackernews.Api
import kotlinx.android.synthetic.main.item.view.*
import retrofit2.Call
import com.marcrobito.examen.R.layout.item
import org.json.JSONObject


class CustomAdapter(private val items: List<Int>, private val context:Context): RecyclerView.Adapter<CustomAdapter.VH>(){

    var listener: OnItemClickListener

    init {
        if(context is OnItemClickListener)
            listener = context
        else throw Exception("you must extend OnItemClickListener")
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(parent)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {


        val queue = Volley.newRequestQueue(context)
        Log.d("Dial_M", items[position].toString())
        val current = items[position]
        val request = object : JsonObjectRequest(Request.Method.GET,"https://hacker-news.firebaseio.com/v0/item/$current.json?print=pretty", null,
            Response.Listener<JSONObject> { response ->
                holder.bind(response)
                holder.setListener(listener)


            }, Response.ErrorListener { error ->
                Log.d("Dial_MN", error.toString())
            }){

        }

        queue.add(request)


    }


    override fun getItemCount(): Int = items.size
    class VH(parent: ViewGroup) : RecyclerView.ViewHolder(

        LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)), View.OnClickListener {

        init{
            parent.setOnClickListener(this)
        }

        private lateinit var listener:OnItemClickListener


        fun bind(item: JSONObject) = with(itemView) {
            title.text = item.getString("title")
            content.text = item.getString("by")
            val url = item.getString("url")
            itemView.tag = url
            setOnClickListener(this@VH)

        }

        fun setListener(listener:OnItemClickListener){
            this.listener = listener
        }

        override fun onClick(v: View?) {
            listener.onItemClick(v!!.tag.toString())

        }
    }
}

interface OnItemClickListener {
    fun onItemClick(url: String)
}