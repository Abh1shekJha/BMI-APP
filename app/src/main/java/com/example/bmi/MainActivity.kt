package com.example.bmi

import android.content.Context
import android.inputmethodservice.Keyboard
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button: Button = findViewById(R.id.calculate_button)
        val weight_txt: EditText = findViewById(R.id.Weight)
        val height_txt: EditText = findViewById(R.id.Height)
        val BMI : TextView = findViewById(R.id.BMI_output)
        val BMI_imgview : ImageView = findViewById(R.id.img_view)
        button.setOnClickListener {
            // Do something in response to button click
            if(weight_txt.text.isNotEmpty() && height_txt.text.isNotEmpty()) {
                val weight = weight_txt.text.toString().toFloat()
                val height = height_txt.text.toString().toFloat()
                val myBMI = weight / (height * height)

                BMI.text = myBMI.toString()

                if (myBMI < 18.5)
                    BMI_imgview.setImageResource(R.drawable.underweight)
                else if (myBMI >= 18.5 && myBMI <= 24.9)
                    BMI_imgview.setImageResource(R.drawable.healthy)
                else if (myBMI >= 25 && myBMI <= 29.9)
                    BMI_imgview.setImageResource(R.drawable.overweight)
                else
                    BMI_imgview.setImageResource(R.drawable.obesity)


                closeKeyboard(height_txt)

            }
            else
                Toast.makeText(this, "The above fields cannot be empty!", Toast.LENGTH_SHORT).show()
        }

    }

    private fun closeKeyboard(view: View){
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)

    }



}