package com.example.almacenamiento

import android.content.ContentValues
import android.database.Cursor
import com.example.almacenamiento.DatabaseHelper.Companion.COL_2
import com.example.almacenamiento.DatabaseHelper.Companion.COL_3
import com.example.almacenamiento.DatabaseHelper.Companion.TABLE_NAME

class UserDatabaseHelper(private val dbHelper: DatabaseHelper) {
fun agregarUsuario(NombreUsuario: String, contrasena: String){
    val db = dbHelper.readableDatabase
    val values =ContentValues().apply {
        put(COL_2,NombreUsuario)
        put(COL_3,contrasena)
    }
    db.insert(TABLE_NAME,null,values)

}
    fun validar(nombreUsuario: String, contrasena: String): Boolean {
        val db = dbHelper.readableDatabase
        val consultaSeleccionar = "$COL_2=? AND $COL_3=?"
        val seleccionarArgumentos = arrayOf(nombreUsuario, contrasena)

        val cursor: Cursor = db.query(
            DatabaseHelper.TABLE_NAME,
            null,
            consultaSeleccionar,
            seleccionarArgumentos,
            null,
            null,
            null
        )

        val exists = cursor.count > 0
        cursor.close()
        return exists
    }
}
