package com.sohayb.contactsProjects

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.view_contact.*

class ViewContact : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.view_contact)

        val im = "https://eitrawmaterials.eu/wp-content/uploads/2016/09/person-icon.png"

        val fileUri: Uri?
        var bundles = intent.extras

        val Nom: String? = bundles!!.getString("ContactSurname")
        val Prenom: String? = bundles!!.getString("ContactName")
        val phoneNum: String? = bundles!!.getString("ContactNumber")
        val image: String? = bundles!!.getString("ContactImage")
        val address: String? = bundles!!.getString("ContactAddress")
        if (image!!.contains("http")) {
            Picasso.get().load(image).into(imageView);
        } else {
            fileUri = Uri.parse(image)
            imageView.setImageURI(fileUri)

        }
        PrenomTextView.text = Prenom
        AdressTextView.text = address
        NumTextView.text = phoneNum
        NomTextView.text = Nom

        buttonCall.setOnClickListener {
            callPhone(this, phoneNum!!) //contact.phoneNum)
        }
        btnSendSMS.setOnClickListener {
            sendSMS(phoneNum)
        }



    }

    fun callPhone(context: Context?, phoneNum: String) {
        val intent = Intent(Intent.ACTION_DIAL)
        intent.data = Uri.parse("tel:$phoneNum")
        (context as Activity).startActivity(intent)

    }

    fun sendSMS(num: String?) {
        val number = num // The number on which you want to send SMS
        startActivity(Intent(Intent.ACTION_VIEW, Uri.fromParts("sms", number, null)))
    }


}