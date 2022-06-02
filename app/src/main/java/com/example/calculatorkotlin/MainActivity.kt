package com.example.calculatorkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.ArithmeticException

class MainActivity : AppCompatActivity() {

    var isNumeric: Boolean = false
    var isDecimal: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onDigit(view : View){
        var tvInput = findViewById<TextView>(R.id.tvInput)
        tvInput.append((view as Button).text)
        isNumeric = true
    }

    fun onClear(view: View){
        tvInput.text=""
        isNumeric = false
        isDecimal = false
    }

    fun onDecimal(view: View){
        if(isNumeric && !isDecimal){
            tvInput.append(".")
            isNumeric = false
            isDecimal = true
        }
    }

    private fun removeZeroAfterDot(result: String): String{
        var value = result
        if (result.contains(".0")){
            value = result.substring(0, result.length - 2)
        }
        return value
    }

    fun onEqual(view: View){
        if(isNumeric){
            var tvValue = tvInput.text.toString()
            var prifix = ""

            try {

                if(tvValue.startsWith("-")){
                    prifix = "-"
                    tvValue = tvValue.substring(1)
                }

                if(tvValue.contains("-")){
                    val value = tvValue.split("-")
                    var one = value[0]
                    var two = value[1]

                    if(!prifix.isEmpty()){
                        one = prifix + one
                    }

                    tvInput.text = removeZeroAfterDot((one.toDouble() - two.toDouble()).toString())

                }else if(tvValue.contains("+")){
                    val value = tvValue.split("+")
                    var one = value[0]
                    var two = value[1]

                    if(!prifix.isEmpty()){
                        one = prifix + one
                    }

                    tvInput.text = removeZeroAfterDot((one.toDouble() + two.toDouble()).toString())

                }else if(tvValue.contains("*")){
                    val value = tvValue.split("*")
                    var one = value[0]
                    var two = value[1]

                    if(!prifix.isEmpty()){
                        one = prifix + one
                    }

                    tvInput.text = removeZeroAfterDot((one.toDouble() * two.toDouble()).toString())

                }else if(tvValue.contains("/")){
                    val value = tvValue.split("/")
                    var one = value[0]
                    var two = value[1]

                    if(!prifix.isEmpty()){
                        one = prifix + one
                    }

                    tvInput.text = removeZeroAfterDot((one.toDouble() / two.toDouble()).toString())

                }

            }catch (e: ArithmeticException){
                e.printStackTrace()
            }

        }
    }

    fun onOperator(view: View){
        if(isNumeric && !isOperatorAdded(tvInput.text.toString())){
            tvInput.append((view as Button).text)
            isNumeric = false
            isDecimal = false
        }
    }

    fun onPop(view: View){
        tvInput.text = tvInput.text.substring(0, tvInput.text.length - 1)
    }

    private fun isOperatorAdded(value: String): Boolean{
        return if(value.startsWith("-")){
            false
        }else{
            value.contains("/") || value.contains("*") || value.contains("+") || value.contains("-")
        }
    }
}