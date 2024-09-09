package com.example.almacenamiento

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ciclo : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_ciclo)



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
    override fun onStart(){
        super.onStart()
        Toast.makeText(this,"Fase onStart", Toast.LENGTH_SHORT).show()
    }
    override fun onPause(){
        super.onPause()
        Toast.makeText(this,"Fase onPause", Toast.LENGTH_SHORT).show()
    }
    override fun onStop(){
        super.onStop()
        Toast.makeText(this,"Fase onStop", Toast.LENGTH_SHORT).show()
    }
    override fun onResume(){
        super.onResume()
        Toast.makeText(this,"Fase onResume", Toast.LENGTH_SHORT).show()
    }
    override fun onDestroy(){
        super.onDestroy()
        Toast.makeText(this,"Fase onDestroy", Toast.LENGTH_SHORT).show()
    }
    override fun onRestart(){
        super.onRestart()
        Toast.makeText(this,"Fase onRestart", Toast.LENGTH_SHORT).show()
    }
}