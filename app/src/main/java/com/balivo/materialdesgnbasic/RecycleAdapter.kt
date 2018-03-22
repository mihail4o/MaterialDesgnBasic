package com.balivo.materialdesgnbasic

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import java.util.*


/**
 * Created by balivo on 3/19/18.
 */
class RecycleAdapter: RecyclerView.Adapter<RecycleAdapter.MyViewHolder> {

    private var inflater: LayoutInflater?=null
    private var data: List<Information>?= Collections.emptyList()

    constructor(context:Context,data:List<Information>){
        inflater = LayoutInflater.from(context)
        this.data = data
    }

    override fun onCreateViewHolder(viewGroup : ViewGroup, viewType:Int): MyViewHolder? {
        val view:View = inflater!!.inflate(R.layout.custom_row, viewGroup, false)
        val holder = MyViewHolder(view)
        return holder
    }

    override fun onBindViewHolder(myViewHolder:MyViewHolder, position:Int) {

        myViewHolder.title.text=data!![position].title
        myViewHolder.icon.setImageResource(data!![position].iconId)

    }

    override fun getItemCount(): Int {

        return data!!.size
    }

    inner class MyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){

        internal var title: TextView = itemView.findViewById(R.id.listText)
        internal var icon: ImageView = itemView.findViewById(R.id.listIcon)

    }
}