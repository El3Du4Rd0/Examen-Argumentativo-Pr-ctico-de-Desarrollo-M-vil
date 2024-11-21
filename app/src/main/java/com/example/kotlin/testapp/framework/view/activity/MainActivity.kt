package com.example.kotlin.testapp.framework.view.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity
import com.example.kotlin.testapp.R
import com.example.kotlin.testapp.framework.view.activity.RecyclerViewActivity

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main)

        val button: Button = findViewById(R.id.button)
        button.setOnClickListener {
            try {
                val intent = Intent(this, RecyclerViewActivity::class.java)
                startActivity(intent)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
