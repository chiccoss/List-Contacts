package com.sohayb.tplistecontacts

import android.app.AlertDialog
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ListView
import android.widget.Toast
import java.util.*

class MainActivity : AppCompatActivity() {

    var equipe = arrayOf("OL", "OM", "PSG", "ASSE", "LOSC","OL", "OM", "PSG", "ASSE", "LOSC","OL", "OM", "PSG", "ASSE", "LOSC","OL", "OM", "PSG", "ASSE", "LOSC")
    var listeEquipes = ArrayList(Arrays.asList(*equipe))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
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
}
