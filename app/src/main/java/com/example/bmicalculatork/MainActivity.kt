package com.example.bmicalculatork

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var eWeight = findViewById<EditText>(R.id.etWeight)
        var eHeight = findViewById<EditText>(R.id.etHeight)
        var calcBtn = findViewById<Button>(R.id.btnCalculate)

        calcBtn.setOnClickListener{
            var tWt = eWeight.text.toString()
            var tHt = eHeight.text.toString()
            if(validateInput(tWt,tHt)) {
                    var bmi = (tWt.toFloat()) / ((tHt.toFloat() / 100) * (tHt.toFloat() / 100))
                    var bmi2Digit = String.format("%.2f", bmi).toFloat()
                    printResult(bmi2Digit)
                }
        }
    }

    private fun validateInput(weight:String?,height:String?): Boolean{       // here we use '?' (safe call operator) ,in case to avoid NullPointerException error if user enters any null input
        return when {
            weight.isNullOrEmpty()->{
                Toast.makeText(this,"Weight is empty!!!",Toast.LENGTH_LONG).show()
                return false
            }
            height.isNullOrEmpty()->{
                Toast.makeText(this,"Height is empty!!!",Toast.LENGTH_LONG).show()
                return false
            }
            else ->{
                return true
            }
        }
    }

    private fun printResult(bmi: Float){
        var result = findViewById<TextView>(R.id.tvNum)
        var message = findViewById<TextView>(R.id.tvResult)
        var information = findViewById<TextView>(R.id.tvInfo)

        result.text = bmi.toString()
        information.text = "(Normal range is 18.5 - 24.9)"

        var msg = ""
        var color = 0
        when {
            bmi<18.50 ->{
                msg = "Underweight"
                color = R.color.under_weight
            }
            bmi in 18.50..24.99->{
                msg = "Normal"
                color = R.color.normal
            }
            bmi in 25.00..29.99->{
                msg = "Overweight"
                color = R.color.over_weight
            }
            bmi > 29.99 ->{
                msg = "Obese"
                color = R.color.obese
            }
        }
        message.setTextColor(ContextCompat.getColor(this,color))
        message.text = msg
    }
}