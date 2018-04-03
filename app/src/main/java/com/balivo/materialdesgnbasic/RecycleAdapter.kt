package com.balivo.materialdesgnbasic

import android.content.Context;
import android.content.Intent
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


/**
 * Created by balivo on 3/19/18.
 */
class RecycleAdapter(context:Context,data:ArrayList<Information>): RecyclerView.Adapter<RecycleAdapter.MyViewHolder>() {

    protected var inflater: LayoutInflater?=null
    protected var data: ArrayList<Information>?= null
    protected var mContext: Context?=null

    init{
        inflater = LayoutInflater.from(context)
        this.data = data
        this.mContext=context
    }

    override fun onCreateViewHolder(viewGroup : ViewGroup, viewType:Int): MyViewHolder? {
        val view:View = inflater!!.inflate(R.layout.custom_row, viewGroup, false)
        val holder = MyViewHolder(view)
        return holder
    }

    override fun onBindViewHolder(myViewHolder:MyViewHolder, position:Int) {

        myViewHolder.tv_title.text=data!![position].title
        myViewHolder.iv_icon.setImageResource(data!![position].iconId)

    }

    override fun getItemCount(): Int {

        return data!!.size
    }

    inner class MyViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        var iv_icon:ImageView
        var tv_title:TextView
        init{
            tv_title = itemView.findViewById(R.id.listText) as TextView
            iv_icon = itemView.findViewById(R.id.listIcon) as ImageView
        }

    }
}