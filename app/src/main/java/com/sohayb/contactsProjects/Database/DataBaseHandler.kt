package com.sohayb.contactsProjects.Database

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast
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
        db!!.execSQL("DROP TABLE IF EXISTS $TABLE_NAME");
        onCreate(db);
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


    fun getAllData(): ArrayList<Contact?>? {

        val selectQuery = "SELECT  * FROM $TABLE_NAME"
        val db = this.readableDatabase
        val cursor: Cursor = db.rawQuery(selectQuery, null)
        val data: ArrayList<Contact?>? = null
        if (cursor.moveToFirst()) {
            do {
                // get the data into array, or class variable
                data!!.add(
                    Contact(
                        cursor.getString(cursor.getColumnIndex(COL_SURNAME)),
                        cursor.getString(cursor.getColumnIndex(COL_NAME)),
                        cursor.getString(cursor.getColumnIndex(COL_PHONE)),
                        cursor.getString(cursor.getColumnIndex(COL_IMAGE)),
                        cursor.getString(cursor.getColumnIndex(COL_ADDRESS))
                    )
                )
                Toast.makeText(cont, "GOT ALL DATA", Toast.LENGTH_SHORT).show()


            } while (cursor.moveToNext())
        }
        cursor.close()
        return data


    }

    fun getContact(name: String): Contact? {
        val db = this.writableDatabase
        val selectQuery = "SELECT  * FROM $TABLE_NAME WHERE $COL_NAME = ?"
        db.rawQuery(selectQuery, arrayOf(name)).use { // .use requires API 16
            if (it.moveToFirst()) {

                return Contact(
                    it.getString(it.getColumnIndex(COL_SURNAME)),
                    it.getString(it.getColumnIndex(COL_NAME)),
                    it.getString(it.getColumnIndex(COL_PHONE)),
                    it.getString(it.getColumnIndex(COL_IMAGE)),
                    it.getString(it.getColumnIndex(COL_ADDRESS))

                )
            }
        }
        return null
    }

    fun deleteFromDatabase(contact: Contact) {
        val db = writableDatabase
        var phone = contact.phoneNum
        db.execSQL("DELETE FROM $TABLE_NAME WHERE $COL_PHONE LIKE '$phone'");
        Toast.makeText(cont, "DEETE FROM DB", Toast.LENGTH_SHORT).show()
    }
}


