package com.sohayb.contactsProjects

import com.sohayb.contactsProjects.R
import android.app.Activity
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


class MainActivity : AppCompatActivity(), View.OnClickListener {
    var contacts: ArrayList<Contact>? = null

    // @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        var bunldes: Bundle? = intent.extras


        //@TODO iNSERT CONTACTS FROM JSON


        contacts = DataSource.GetDataSet(this) //get(this,"Contacts.json")

        //val image : ImageView= findViewById(R.drawable.person_icon)


        //setSupportActionBar(toolbar)

        contacts?.let { initRecyclerView(it) } ?: Log.i("tag", "contacts is null")


        //val lv = findViewById<ListView>(R.id.listevue)
        //final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, listeEquipes);
        //val adapter = ContactRecyclerAdapter(this@MainActivity, listeEquipes)
        //lv.adapter = adapter
        /* lv.onItemClickListener =
             AdapterView.OnItemClickListener { adapterView, view, i, l ->
                 Toast.makeText(
                     this@MainActivity,
                     listeEquipes[i],
                     Toast.LENGTH_LONG
                 ).show()
             }
         lv.onItemLongClickListener =
             AdapterView.OnItemLongClickListener { adapterView, view, i, l ->
                 val dialogBuilder = AlertDialog.Builder(this)
                 dialogBuilder.setMessage("Do you want to close this application ?")
                     .setPositiveButton("Proceed", DialogInterface.OnClickListener { it, id ->
                         listeEquipes.removeAt(i)
                         adapter.notifyDataSetChanged()
                         //finish()
                     })
                     .setNegativeButton("Cancel", DialogInterface.OnClickListener { it, id ->
                         it.cancel()
                     })

                 val alert = dialogBuilder.create()

                 alert.setTitle("You are connected")

                 alert.show()
                 //

                 true
             }*/

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
                val prenom = data!!.getStringExtra("ContactName")
                val address = data!!.getStringExtra("ContactAddress")
                val numero = data!!.getStringExtra("ContactNumber")
                val image = data.getStringExtra("ContactImage")
                var fileUri: Uri = Uri.parse(image)
                // val imageBitmap = data!!.extras!!.get("ContactImage") as Bitmap

                contacts!!.add(0, Contact(nom!!, prenom!!, numero!!, image!!, address!!))
                recycler_view.adapter!!.notifyDataSetChanged()

                Toast.makeText(this, "Done BACKKK FROM 4", Toast.LENGTH_LONG).show()
            }


        }
    }

    override fun onClick(v: View?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}
