package com.sohayb.tplistecontacts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.sohayb.tplistecontacts.Model.Contact
import kotlinx.android.synthetic.main.content_main.*
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //@TODO iNSERT CONTACTS FROM JSON


        val contacts: ArrayList<Contact> = ArrayList()

        for (i in 1..50) {
            contacts.add(
                Contact("Chicco $i", "Troll $i", "06336745 $i" + i, null, "Rue de la croix $i")
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
            Snackbar.make(view, "Here's a Snackbar", Snackbar.LENGTH_LONG)
                .setAction("Action", null)
                .show()
        }

        recycler_view.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            val topSpacingDecorator = TopSpacingItemDecoration(50)
            addItemDecoration(topSpacingDecorator)
            adapter = ContactRecyclerAdapter(contacts)
        }
    }
}
