package com.example.unitconverter

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import net.objecthunter.exp4j.ExpressionBuilder

class ConverterActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_converter)

        val units = fillUnit()
        val quantity = getIntent().getStringExtra("quantity")
        findViewById<TextView>(R.id.textView).setText(quantity + " Converter")
        val unit1 = findViewById<EditText>(R.id.first)
        val unit2 = findViewById<EditText>(R.id.second)
        val thisUnit = units.filter{it.Quantity == quantity}
        val spinner1 = findViewById<Spinner>(R.id.spinner1).apply{
            adapter = spinnerAdapter(thisUnit as MutableList<unit>)
        }
        val spinner2 = findViewById<Spinner>(R.id.spinner2).apply{
            adapter = spinnerAdapter(thisUnit as MutableList<unit>)
        }
        var rel1 : Double = 0.0
        var rel2 : Double = 0.0
        var exp1 : String = "0"
        var exp2 : String = "0"
        var temp : Boolean = false
        val convert = findViewById<Button>(R.id.convert)
        findViewById<ImageButton>(R.id.reset).apply{
            setOnClickListener{reset(unit1,unit2)}
        }

        spinner1.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                unit1.getText().clear()
                unit1.hint = thisUnit[position].code
                rel1 = thisUnit[position].rel_val
                if(thisUnit[position].Exp!="NA"){
                    exp1 = thisUnit[position].Exp
                    temp = true }
                else{
                    temp = false
                }

            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                unit1.hint = "Please select a unit"
            }

        }
        spinner2.onItemSelectedListener = object:AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                unit2.getText().clear()
                unit2.hint = thisUnit[position].code
                rel2 = thisUnit[position].rel_val
                if(temp){
                    exp2 = thisUnit[position].Exp
                } }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                unit2.hint = "Please select a unit"
            }

        }
        unit1.setOnFocusChangeListener { _,hasFocus ->
            if(hasFocus){
                convert.setOnClickListener{
                    if(temp){
                        convTemp(exp1,exp2,unit1,unit2)
                    }
                    else
                        convert(rel1,rel2,unit2,unit1)
                }
            }
        }

        unit2.setOnFocusChangeListener { _,hasFocus ->
            if(hasFocus){
                convert.setOnClickListener{
                if(temp){
                        convTemp(exp2,exp1,unit2,unit1)

                    }
                    else
                convert(rel2,rel1,unit1,unit2)
                }

            }
        }

    }

    private fun convTemp(fromUnit: String, toUnit: String, fromE: EditText, toE: EditText) {
        val ans : Double
        val value = fromE.getText().toString().toDouble()
        when {
            fromUnit.equals("C") && toUnit.equals("F") ->
                ans =  (value * 9 / 5) + 32

            fromUnit.equals("C") && toUnit.equals("K") ->
                ans =  value + 273.15

            fromUnit.equals("F") && toUnit.equals("C") ->
                ans =  (value - 32) * 5 / 9

            fromUnit.equals("F") && toUnit.equals("K") ->
                ans =  (value + 459.67) * 5 / 9

            fromUnit.equals("K") && toUnit.equals("C") ->
                ans = value - 273.15

            fromUnit.equals("K") && toUnit.equals("F") ->
                ans =  (value * 9 / 5) - 459.67

            else -> ans =  value
        }
toE.setText(ans.toString())
    }


    private fun reset(unit1:EditText,unit2:EditText){
        unit1.getText().clear()
        unit2.getText().clear()
    }
    private fun convert(rel1 : Double ,rel2 : Double,to : EditText,from : EditText) {
            to.setText((from.getText().toString().toDouble()*rel2/rel1).toString())

    }
    private fun fillUnit():MutableList<unit>{
        return mutableListOf(unit("Kilometer","km","Length",0.001),
            unit("Centimeter","cm","Length",100.0),
            unit("Decimeter","dm","Length",10.0),
            unit("Millimeter","mm","Length",1000.0),
            unit("Meter","m","Length",1.00),
            unit("LightYear","ly","Length",1.057E-16),
            unit("Micrometer","µm","Length",10E6),
            unit("Parse","pc","Length",3.24077E-17),
            unit("Astronomic Unit","AU","Length",6.6846E-12),
            unit("Lunar Distance","LD","Length",2.6041E-8),
            unit("Pico-meter","pm","Length",10E12),
            unit("Nanometer","nm","Length",10E9),
            unit("Angstrom","Å","Length",10E10),
            unit("Furlong","fur","Length",0.004971),
            unit("Fathom","fm","Length",0.54680866),
            unit("Yard","yd","Length",1.0936133),
            unit("Nautical mile","nmil","Length",0.00054),
            unit("Foot","ft","Length",3.2808399),
            unit("Mile","mil","Length",0.0006214),
            unit("Inch","in","Length",39.3700787),



            //area
            unit("Square meter","m²","Area",1.0),
            unit("Square decimeter","dm²","Area",100.0),
            unit("Square centimeter","cm²","Area",10000.0),
            unit("Square kilometer","km²","Area",0.000001),
            unit("Square millimeter","mm²","Area",1000000.0),
            unit("Square mile","mil²","Area",3.861E-7),
            unit("Square rod","rd²","Area",0.0395369),
            unit("Square yard","yd²","Area",1.19599),
            unit("Square foot","ft²","Area",10.7639104),
            unit("Hectare","ha","Area",0.001),
            unit("Are","ar","Area",0.01),
            unit("Acre","ac","Area",0.0002471),
            unit("Square inch","in²","Area",1550.0031),



            //volume

            unit("Cubic meter","m³","Volume",1.0),
            unit("Hectoliter", "hl", "Volume",10.0),
                    unit("Cubic centimeter", "cm³", "Volume",1000000.0),
        unit("Deciliter", "dl", "Volume",10000.0),
        unit("Centiliter", "cl", "Volume",100000.0),
        unit("Cubic decimeter", "dm³", "Volume",1000.0),
        unit("Liter", "l", "Volume",1000.0),
        unit("Cubic millimeter", "mm³", "Volume",1000000000.0),
        unit("Milliliter", "ml", "Volume",1000000.0),
        unit("Cubic foot", "ft³", "Volume",35.3147248),
        unit("US fluid ounce", "US fl oz", "Volume",34164.6737274),
        unit("Cubic inch", "in³", "Volume",61023.8445022),
        unit("Acre-foot", "af³", "Volume",0.0008107),
        unit("UK gallon", "UK gal", "Volume",219.9691573),
        unit("US gallon", "US gal", "Volume",264.1720524),
        unit("UK fluid ounce", "UK fl oz", "Volume",35198.873636),
        unit("Cubic yard","yd³","Volume",1.30795528),

            /// weight
            unit("Kilograms","kg","Weight",1.0),
            unit("Grams","g","Weight",1000.0),
            unit("Microgram", "µg", "Weight", 1000000000.0),
                    unit("Quintal", "q", "Weight", 0.01),
        unit("Carat", "ct", "Weight", 5000.0),
        unit("Ton", "t", "Weight", 0.001),
        unit("Milligram", "mg", "Weight", 1000000.0),
        unit("Short ton", "st", "Weight",0.0011023 ),
        unit("Long ton", "lt", "Weight",0.0009842 ),
        unit("Ounce", "oz", "Weight",35.2739619 ),
        unit("Grain", "gr", "Weight",15432.3583529 ),
        unit("Dram", "dr", "Weight", 564.3833912),
        unit("Short hundredweight", "sh cwt", "Weight", 0.0220462),
        unit("Pound", "lb", "Weight",2.2046226 ),
        unit("Stone", "st", "Weight",0.157473 ),
        unit("Long hundredweight", "lg cwt", "Weight",0.0196841 ),

            //power

        unit("Watt","W","Power",1.0),
            unit("Joules/second", "J/s", "Power", 1.0),
                    unit("British thermal unit/second", "Btu/s", "Power", 0.0009478171),
        unit("Metric horsepower", "PS", "Power",0.0013596216 ),
        unit("Kilogram-meter/second", "kg-m/s", "Power",0.1019716213 ),
        unit("Kilocalorie/second", "kcal/s", "Power",0.000239 ),
        unit("Imperial Horsepower", "hp", "Power",0.00134110221 ),
        unit("Foot-pound/second", "ft-lb/s", "Power", 0.7375621489),
        unit("Newton-meter/second", "N-m/s", "Power", 1.0),
        unit("Kilowatt", "kW", "Power",0.0001 ),


            //pressure

        unit("Kilopascal","kpa","Pressure",1.0),
            unit("Millimeter of water", "mmH2O", "Pressure",101.9716),
            unit("Pound/square foot", "psf", "Pressure",20.88543512),
            unit("Kilogram-force/square centimeter", "kgf/cm²", "Pressure",0.010197),
            unit("Pound/square inch", "psi", "Pressure",0.14503774),
            unit("Millimeter of mercury", "mmHg", "Pressure",7.500617),
            unit("Bar", "bar", "Pressure",0.01),
            unit("Inch of mercury", "inHg", "Pressure",0.2953),
            unit("Millibar", "mbar", "Pressure",10.0),
            unit("Hectopascal", "hPa", "Pressure",10.0),
            unit("Standard atmosphere", "atm", "Pressure",0.00986923),
            unit("Kilogram-force/square meter", "kgf/m²", "Pressure",101.971621),
            unit("Megapascal", "MPa", "Pressure",0.0001),

            //Speed

        unit("Kilometer/hour","km/h","Speed",1.0),
            unit("Speed of light", "c", "Speed",9.265555555555562968E-10),
            unit("Kilometer/second", "km/s", "Speed",0.000277777777777777777778),
            unit("Mile/hour", "mph", "Speed",0.62137111111111111608208),
            unit("Mach", "Mach", "Speed",0.000816277777777784308),
            unit("Inch/second", "in/s", "Speed",10.936133055555564304462),
            unit("Meter/second", "m/s", "Speed",0.2777777777777778),

            //energy

            unit("Joule", "J", "Energy",1000.00 ),
            unit("Kilojoule", "kJ", "Energy", 1.0),
            unit("Gram calorie", "cal", "Energy",239.006 ),
            unit("Kilocalorie", "kcal", "Energy",0.239006 ),
            unit("Watt hour", "Wh", "Energy", 0.277778),
            unit("Kilowatt hour", "kWh", "Energy",0.000277778 ),
            unit("Electronvolt", "eV", "Energy", 6.24151433599999961e+21),
            unit("British thermal unit", "BTU", "Energy",0.94781787849599685725 ),
            unit("US therm", "thm", "Energy",9.480441863370573345e-6 ),
            unit("Foot pound", "ft-lb", "Energy", 737.56273927172185267),


            //angle
            unit("Arcsecond", "arcsec", "Angle",206265.00 ),
            unit("Degree", "°", "Angle", 57.29583337126),
            unit("Gradian", "gon", "Angle", 63.662038164977154509),
            unit("Radian", "rad", "Angle",1.0 ),
            unit("Milliradian", "mrad", "Angle",1000.0 ),
            unit("Minute of arc", "arcmin", "Angle",3437.7500022637818802 ),

            //Temperature


        unit("Celsius","°C","Temperature",0.0 , "C" ),
        unit("Fahrenheit","°F","Temperature",0.0,"F"),
        unit("Kelvin","K","Temperature",0.0,"K")

            )
    }
}