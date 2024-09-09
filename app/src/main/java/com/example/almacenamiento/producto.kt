package com.example.almacenamiento

import android.database.Cursor
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.SimpleCursorAdapter
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class producto : AppCompatActivity() {
    private lateinit var dbHelper: ProductoDatabaseHelper
    private lateinit var listaProductos: ListView
    private lateinit var nombreProducto: EditText
    private lateinit var precioProducto: EditText
    private lateinit var btn_insertar: Button
    private lateinit var btn_actualizar: Button
    private lateinit var btn_eliminar: Button
    private var idProductoSeleccionado: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_producto)

        // Inicializar las vistas después de setContentView
        listaProductos = findViewById(R.id.lv_productos)
        nombreProducto = findViewById(R.id.txt_nombreP)
        precioProducto = findViewById(R.id.txt_precio)
        btn_insertar = findViewById(R.id.btn_insertar)
        btn_actualizar = findViewById(R.id.btn_actualizar)
        btn_eliminar = findViewById(R.id.btn_eliminar)

        dbHelper = ProductoDatabaseHelper(this)

        btn_insertar.setOnClickListener {
            val nombreP = nombreProducto.text.toString()
            val precioP = precioProducto.text.toString().toDoubleOrNull()

            if (nombreP.isEmpty() || precioP == null) {
                Toast.makeText(this, "Ingrese Valores", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            dbHelper.agregarProducto(nombreP, precioP)
            Toast.makeText(this, "Producto Registrado", Toast.LENGTH_SHORT).show()
            mostrarProductos()
        }

        btn_eliminar.setOnClickListener{
            val id= idProductoSeleccionado
            if(id == null){
                Toast.makeText(this,"Seleccione Un registro",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            dbHelper.eliminarProducto(id)
            Toast.makeText(this,"Producto Seleccionado",Toast.LENGTH_SHORT).show()
            mostrarProductos()
        }
        btn_actualizar.setOnClickListener {
            val nombre = nombreProducto.text.toString()
            val precio = precioProducto.text.toString().toDoubleOrNull()
            val id = idProductoSeleccionado
            if (nombre.isEmpty() || precio == null || id == null) {
                Toast.makeText(this, "Seleccionar un registro", Toast.LENGTH_SHORT).show()
            }
            dbHelper.updateProductos(id,nombre,precio)
            Toast.makeText(this, "Producto actualizado", Toast.LENGTH_SHORT).show()
            mostrarProductos()
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun mostrarProductos() {
        val cursor = dbHelper.obtenerProductos()
        val adapter = SimpleCursorAdapter(
            this,
            android.R.layout.simple_list_item_2,
            cursor,
            arrayOf(ProductoDatabaseHelper.COLUMN_NAME, ProductoDatabaseHelper.COLUMN_PRECIO),
            intArrayOf(android.R.id.text1, android.R.id.text2),
            0
        )
        listaProductos.adapter = adapter

        listaProductos.setOnItemClickListener { _, _, posicion, _ ->
            val cursor = listaProductos.adapter.getItem(posicion) as Cursor
            val id = cursor.getInt(cursor.getColumnIndexOrThrow(ProductoDatabaseHelper.COLUMN_ID))
            val nombre = cursor.getString(cursor.getColumnIndexOrThrow(ProductoDatabaseHelper.COLUMN_NAME))
            val precio = cursor.getDouble(cursor.getColumnIndexOrThrow(ProductoDatabaseHelper.COLUMN_PRECIO))

            idProductoSeleccionado = id
            nombreProducto.setText(nombre)
            precioProducto.setText(precio.toString())
        }
    }
}
