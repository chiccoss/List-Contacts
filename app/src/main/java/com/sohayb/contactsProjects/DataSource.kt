package com.sohayb.contactsProjects

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.sohayb.contactsProjects.Database.DataBaseHandler
import com.sohayb.contactsProjects.Model.Contact
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException

class DataSource {

    companion object {

        fun GetDataSet(
            context: Context,
            db: DataBaseHandler
        ): ArrayList<Contact?>? {
            val jsonString: String
            var contacts: ArrayList<Contact?>? = ArrayList()
            try {
                jsonString =
                    context.assets.open("Contacts.json").bufferedReader().use { it.readText() }

                val jsonContact = JSONArray(jsonString)

                var i: Int = 0
                val size: Int = jsonContact.length()
                var jsonObjectdetail: JSONObject?
                for (i in 0 until size) {
                    jsonObjectdetail = jsonContact.getJSONObject(i)
                    db.insertData(
                        Contact(
                            jsonObjectdetail.getString("surname"),
                            jsonObjectdetail.getString("name"),
                            jsonObjectdetail.getString("phone"),
                            jsonObjectdetail.getString("image"),
                            jsonObjectdetail.getString("address")

                        )
                    )
                    //contacts!!.add(

                    // )
                }

            } catch (ioException: IOException) {
                ioException.printStackTrace()
                Log.i("Tag", "file not found")
                return GetDataTestDataSet()
            } catch (Exception: Exception) {

                Log.i("begin of stacktrace", "ERROOR")
                Exception.printStackTrace()
                Log.i("end of stacktrace", "ERROOR")
                Log.i("Tag", "ERROOR")
                return GetDataTestDataSet()
            }
            Log.i("Tag", "size is " + contacts!!.size.toString())
            return contacts
        }

        fun GetDataTestDataSet(): ArrayList<Contact?>? {

            val contacts: ArrayList<Contact?>? = ArrayList()

            for (i in 1..50) {
                contacts!!.add(
                    Contact(
                        "Chicco ${i - 1}",
                        "Troll ${i - 1}",
                        "0622677575",
                        "https://eitrawmaterials.eu/wp-content/uploads/2016/09/person-icon.png",
                        "Rue de la croix ${i - 1}"
                    )
                )
            }
            return contacts
        }

        fun AddToDataSet(contact: Contact) {


        }

        fun DeleteFromDataSet(contact: Contact) {


        }

        fun modifyDataset() {

        }


    }
}