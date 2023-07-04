package com.example.unitconverter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class spinnerAdapter(val units: MutableList<unit>) : BaseAdapter(){
    override fun getCount(): Int {
        return units.size
    }

    override fun getItem(position: Int): unit {
      return units[position]
    }

    override fun getItemId(position: Int): Long {
        return units[position].rel_val.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view : View
        val viewHolder : unitViewHolder
        if(convertView==null){
            view = LayoutInflater.from(parent?.context).inflate(R.layout.spinner_item,parent,false)
            viewHolder = unitViewHolder().apply {
                name = view.findViewById(R.id.name)
                view.tag = this
            }
        }
        else{
            view = convertView
            viewHolder = view.tag as unitViewHolder
        }
        viewHolder.apply{
            name.text = getItem(position).name
        }
        return view
    }

}
class unitViewHolder(){
    lateinit var name : TextView
}