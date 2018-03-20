package com.balivo.materialdesgnbasic

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup

/**
 * Created by balivo on 3/19/18.
 */
class RecycleAdapter: RecyclerView.Adapter<RecycleAdapter.MyViewHolder>() {

    override fun getItemCount(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType:Int): MyViewHolder? {
        return null
    }
    override fun onBindViewHolder(holder:MyViewHolder, position:Int) {
    }
    inner class MyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView)
}