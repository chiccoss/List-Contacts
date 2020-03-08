package com.sohayb.contactsProjects.Database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast
import com.sohayb.contactsProjects.Database.*
import com.sohayb.contactsProjects.Model.Contact


class DataBaseHandler(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, 1) {

    var cont = context
    override fun onCreate(db: SQLiteDatabase?) {


        val createTable =
            "CREATE TABLE $TABLE_NAME ( " +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT" +
                    ", $COL_NAME VARCHAR(200) " +
                    ", $COL_SURNAME VARCHAR(200) " +
                    ", $COL_ADDRESS VARCHAR(200) " +
                    ", $COL_PHONE VARCHAR(200) " +
                    ", $COL_IMAGE VARCHAR(200) )"

        db?.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }

    fun insertData(user: Contact) {
        val db = writableDatabase
        var cv = ContentValues()
        cv.put(COL_NAME, user.Prenom)
        cv.put(COL_SURNAME, user.Nom)
        cv.put(COL_ADDRESS, user.address)
        cv.put(COL_IMAGE, user.image)
        cv.put(COL_PHONE, user.phoneNum)

        var risultato = db.insert(TABLE_NAME, null, cv)
        if (risultato == -1.toLong()) {

            Toast.makeText(cont, "FAILED IN DB", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(cont, "success in insert IN DB", Toast.LENGTH_SHORT).show()
        }

    }


}
