package com.prog2sem.androidClient

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

fun greet(): String {
    return "ЕПТ!"
}

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tv: TextView = findViewById(R.id.text_view)
        tv.text = greet()
    }
}
