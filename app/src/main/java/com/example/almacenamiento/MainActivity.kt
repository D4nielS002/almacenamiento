package com.example.almacenamiento

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inicializar vistas
        val nombreUsuario = findViewById<EditText>(R.id.txt_usuario)
        val contrasena = findViewById<EditText>(R.id.txt_contrasena)
        val botonLogin = findViewById<Button>(R.id.btn_registrar2)
        val linkregistro = findViewById<TextView>(R.id.txt_registroventana)
        // Inicializar UserDatabaseHelper
        val dbHelper = UserDatabaseHelper(DatabaseHelper(this))

        // Evento del botón
        botonLogin.setOnClickListener {
            val nombre = nombreUsuario.text.toString()
            val contra = contrasena.text.toString()
            if (dbHelper.validar(nombre, contra)) {
                Toast.makeText(this, "Ingreso Correcto", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Usuario no Registrado", Toast.LENGTH_SHORT).show()
            }
        }
        linkregistro.setOnClickListener{
            startActivity(Intent(this,RegisterActivity::class.java))
        }


        // Configurar ajustes de los insets
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}
