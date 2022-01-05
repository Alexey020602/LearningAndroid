package com.example.learningandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val rollButton: Button = findViewById(R.id.roll_button)
        rollButton.setOnClickListener { rollDice() }
        val textView: TextView = findViewById(R.id.text_view)
        textView.text=(1..6).random().toString()

    }

    private fun rollDice() {
        Toast.makeText(this, "button clicked",
            Toast.LENGTH_SHORT).show()
    }
}