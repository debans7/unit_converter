package com.example.unitconverter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import java.util.jar.Attributes.Name

class quantityAdapter (val quantities : MutableList<quantity>) : BaseAdapter(){
    override fun getCount(): Int {
        return quantities.size
    }

    override fun getItem(position: Int): quantity {
        return quantities[position]
    }

    override fun getItemId(position: Int): Long {
        return quantities[position].name.hashCode().toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view : View
        val viewholder : ViewHolder
        if(convertView==null){
            view = LayoutInflater.from(parent.context).inflate(R.layout.converter_item,parent,false)
            viewholder = ViewHolder().apply {
                Name = view.findViewById(R.id.textView2)
                icon = view.findViewById(R.id.imageView)
                view.tag = this
            }
        }
        else{
            view = convertView
            viewholder = (view.tag as ViewHolder)
        }
        viewholder.apply{
            Name.text = getItem(position).name
            icon.setImageResource(getItem(position).icon)
        }
        return view
    }
}
class ViewHolder(){
    lateinit var Name : TextView
    lateinit var icon : ImageView
}