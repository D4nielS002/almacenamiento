package com.example.almacenamiento

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper (context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, 1) {

    companion object {
        public const val DATABASE_NAME = "login.db"
        public const val TABLE_NAME = "users"
        public const val COL_1 = "ID"
        public const val COL_2 = "USERNAME"
        public const val COL_3 = "PASSWORD"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE $TABLE_NAME (ID INTEGER PRIMARY KEY AUTOINCREMENT, USERNAME TEXT, PASSWORD TEXT)")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    // Método para insertar un nuevo usuario
    fun insertUser(username: String, password: String): Boolean {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(COL_2, username)
        contentValues.put(COL_3, password)
        val result = db.insert(TABLE_NAME, null, contentValues)
        return result != -1L
    }

    // Método para verificar si el usuario y la contraseña son correctos
    fun checkUser(username: String, password: String): Boolean {
        val db = this.readableDatabase
        val cursor: Cursor = db.rawQuery("SELECT * FROM $TABLE_NAME WHERE USERNAME = ? AND PASSWORD = ?", arrayOf(username, password))
        val exists = cursor.count > 0
        cursor.close()
        return exists
    }
}
