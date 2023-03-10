package com.reift.toasting

import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.core.content.res.ResourcesCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.btn_sample_toasting).setOnClickListener {
            Toasting.Builder()
                .show(supportFragmentManager)
        }

        findViewById<Button>(R.id.btn_custom_toasting).setOnClickListener {
            Toasting.Builder(Toasting.ERROR_TYPE)
                .setTitleText("Validation Error!")
                .setContentText("Please try to validate again")
                .setButtonMessage("Try again")
                .setOnButtonClick {
                    Toast.makeText(applicationContext, "Clicked", Toast.LENGTH_SHORT).show()
                }
                .setTitleFont(getFont(R.font.nunito_bold))
                .setContentFont(getFont(R.font.nunito_regular))
                .setButtonFont(getFont(R.font.nunito_seminold))
                .show(supportFragmentManager)
        }
    }

    private fun getFont(font: Int): Typeface? {
        return ResourcesCompat.getFont(this, font)
    }
}