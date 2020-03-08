package com.sohayb.contactsProjects

import com.sohayb.contactsProjects.R
import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.sohayb.contactsProjects.Model.Contact
import kotlinx.android.synthetic.main.content_main.*
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.OutputStream
import java.util.*
import kotlin.collections.ArrayList


class MainActivity : AppCompatActivity() {
    var contacts: ArrayList<Contact>? = null

    // @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        var bunldes: Bundle? = intent.extras

        contacts = DataSource.GetDataSet(this) //get(this,"Contacts.json")
        contacts?.let { initRecyclerView(it) } ?: Log.i("tag", "contacts is null")
    }


    private fun initRecyclerView(contacts: ArrayList<Contact>) {

        val fab: View = findViewById(R.id.fab)
        fab.setOnClickListener { view ->
            val destination = Intent(this@MainActivity, CreateNewContact::class.java)
            startActivityForResult(destination, 4)
        }

        recycler_view.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            val topSpacingDecorator = TopSpacingItemDecoration(16)
            addItemDecoration(topSpacingDecorator)
            adapter = ContactRecyclerAdapter(contacts)
            (adapter as ContactRecyclerAdapter).notifyDataSetChanged()
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 1) {
            Toast.makeText(this, "Done back", Toast.LENGTH_LONG).show()
        }
        if (requestCode == 23) {
            Toast.makeText(this, "Done BACKKK FROM 23", Toast.LENGTH_LONG).show()
        }
        if (requestCode == 4) {

            if (resultCode == Activity.RESULT_OK) { // Get String data from Intent
                val nom = data!!.getStringExtra("ContactSurname")
                val prenom = data.getStringExtra("ContactName")
                val address = data.getStringExtra("ContactAddress")
                val numero = data.getStringExtra("ContactNumber")
                val image = data.getStringExtra("ContactImage")
                val im = data.getParcelableExtra<Bitmap>("ContactImage")
                // var fileUri: Uri = Uri.parse(image)
                val uri = bitmapToFile(im)
                contacts!!.add(0, Contact(nom!!, prenom!!, numero!!, uri.toString(), address!!))
                recycler_view.adapter!!.notifyDataSetChanged()

                toastS(uri.toString())
            }


        }
    }

    fun Context.toastL(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    fun Context.toastS(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }


    private fun bitmapToFile(bitmap: Bitmap?): Uri {
        // Get the context wrapper
        val wrapper = ContextWrapper(applicationContext)

        // Initialize a new file instance to save bitmap object
        var file = wrapper.getDir("Images", Context.MODE_PRIVATE)
        file = File(file, "${UUID.randomUUID()}.jpg")

        try {
            // Compress the bitmap and save in jpg format
            val stream: OutputStream = FileOutputStream(file)
            bitmap!!.compress(Bitmap.CompressFormat.JPEG, 100, stream)
            stream.flush()
            stream.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }

        // Return the saved bitmap uri
        return Uri.parse(file.absolutePath)
    }


}
