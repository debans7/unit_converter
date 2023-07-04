package com.example.unitconverter

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ListView
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val quantities = fillQuantities()

        val list  =findViewById<ListView>(R.id.list).apply{
                adapter = quantityAdapter(quantities)
        }
        list.setOnItemClickListener{AdapterView,view,position,_ ->
                val intent = Intent(this@MainActivity,ConverterActivity::class.java).apply {
                    putExtra("quantity",view.findViewById<TextView>(R.id.textView2).getText().toString())
                }
            startActivity((intent))
        }

    }
    private  fun fillQuantities():MutableList<quantity>{
        return mutableListOf(quantity("Length",R.drawable.length),
            quantity("Area",R.drawable.area),
            quantity("Weight",R.drawable.weight),
            quantity("Volume",R.drawable.volume),
            quantity("Power",R.drawable.powern),
            quantity("Energy",R.drawable.power),
            quantity("Speed",R.drawable.speed),
            quantity("Angle",R.drawable.angle),
            quantity("Temperature",R.drawable.tempereautre)
        ,quantity("Pressure",R.drawable.pressure)
        )
    }
}