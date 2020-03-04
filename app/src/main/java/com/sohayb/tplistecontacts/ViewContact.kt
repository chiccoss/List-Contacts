package com.sohayb.tplistecontacts

import android.os.Bundle
import android.os.PersistableBundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.view_contact.*

class ViewContact : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.view_contact)

        val im = "https://eitrawmaterials.eu/wp-content/uploads/2016/09/person-icon.png"


        Picasso.get().load(im).into(imageView);
        //var bundles=intent.extras

        // val Nom: String=bundles.get("nom")
        //val Prenom: String,
        //val phoneNum : String,
        //val image: String,
        //val address : String
    }
}