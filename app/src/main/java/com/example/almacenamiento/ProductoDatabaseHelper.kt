package com.example.almacenamiento

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class ProductoDatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "baseproducto"
        const val TABLE_PRODUCT = "productos"
        const val COLUMN_ID = "id"
        const val COLUMN_NAME = "nombre"
        const val COLUMN_PRECIO = "precio"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createTable = ("CREATE TABLE $TABLE_PRODUCT (" +
                "$COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "$COLUMN_NAME TEXT," +
                "$COLUMN_PRECIO REAL)")
        db.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, versionAnterior: Int, versionNueva: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_PRODUCT")
        onCreate(db)
    }

    fun agregarProducto(nombre: String, precio: Double) {
        val db = this.writableDatabase
        val valores = ContentValues().apply {
            put(COLUMN_NAME, nombre)
            put(COLUMN_PRECIO, precio)
        }
        db.insert(TABLE_PRODUCT, null, valores)
        db.close()
    }

    fun obtenerProductos(): Cursor {
        val db = this.readableDatabase
        // Usar alias en la consulta para renombrar 'id' a '_id'
        return db.rawQuery("SELECT $COLUMN_ID AS _id, $COLUMN_NAME, $COLUMN_PRECIO FROM $TABLE_PRODUCT", null)
    }

    fun updateProductos(id: Int, nombre: String, precio: Double) {
        val db = this.writableDatabase
        val valores = ContentValues().apply {
            put(COLUMN_NAME, nombre)
            put(COLUMN_PRECIO, precio)
        }
        db.update(TABLE_PRODUCT, valores, "$COLUMN_ID=?", arrayOf(id.toString()))
        db.close()
    }

    fun eliminarProducto(id: Int) {
        val db = this.writableDatabase
        db.delete(TABLE_PRODUCT, "$COLUMN_ID=?", arrayOf(id.toString()))
        db.close()
    }
}
