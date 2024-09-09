package com.example.almacenamiento

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)

        val nombreUsuario = findViewById<EditText>(R.id.txt_usuario_registro)
        val contrasena = findViewById<EditText>(R.id.txt_contrasena_registro)
        val contrasena2 = findViewById<EditText>(R.id.txt_registro_nuevamente)
        val botonRegistrar = findViewById<Button>(R.id.btn_registrar)
        val botonIrALogin = findViewById<Button>(R.id.btn_ir_a_login)

        val dbHelper = DatabaseHelper(this)

        botonRegistrar.setOnClickListener {
            val nombre = nombreUsuario.text.toString()
            val contra = contrasena.text.toString()
            val contra2 = contrasena2.text.toString()
            
            if (nombre.isEmpty() || contra.isEmpty() || contra2.isEmpty()) {
                Toast.makeText(this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (contra == contra2) {
                val isInserted = dbHelper.insertUser(nombre, contra)
                if (isInserted) {
                    Toast.makeText(this, "Usuario registrado correctamente", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(this, "Error al registrar usuario", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show()
            }
        }

        botonIrALogin.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
