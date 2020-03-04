package com.sohayb.tplistecontacts

import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Website.URL
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.sohayb.tplistecontacts.Model.Contact
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.content_main.*
import java.util.*
import kotlin.collections.ArrayList


class MainActivity : AppCompatActivity(), View.OnClickListener {

    var equipe = arrayOf(
        "OL",
        "OM",
        "PSG",
        "ASSE",
        "LOSC",
        "OL",
        "OM",
        "PSG",
        "ASSE",
        "LOSC",
        "OL",
        "OM",
        "PSG",
        "ASSE",
        "LOSC",
        "OL",
        "OM",
        "PSG",
        "ASSE",
        "LOSC"
    )
    var listeEquipes = ArrayList(Arrays.asList(*equipe))

    // @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //@TODO iNSERT CONTACTS FROM JSON


        val contacts: ArrayList<Contact> = ArrayList()

        //val image : ImageView= findViewById(R.drawable.person_icon)

        for (i in 1..50) {
            contacts.add(
                Contact(
                    "Chicco ${i - 1}",
                    "Troll ${i - 1}",
                    "06336745 ${i - 1}",
                    "https://eitrawmaterials.eu/wp-content/uploads/2016/09/person-icon.png",
                    "Rue de la croix ${i - 1}"
                )
            )
        }

        //setSupportActionBar(toolbar)

        initRecyclerView(contacts)


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
            startActivity(destination)
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

    }

    override fun onClick(v: View?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
