package com.sohayb.contactsProjects

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.sohayb.contactsProjects.Database.DataBaseHandler
import com.sohayb.contactsProjects.Model.Contact
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.view_contact.*
import org.jetbrains.anko.internals.AnkoInternals.getContext


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
            Log.i("Logg", fileUri.toString())
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
        //var db:DataBaseHandler?=DataBaseHandler(this)
        //db!!.getAllData()?.let { initRecyclerView(it) }

    }

    private fun initRecyclerView(contacts: ArrayList<Contact?>) {


        recycler_view.apply {
            layoutManager = LinearLayoutManager(this@ViewContact)
            val topSpacingDecorator = TopSpacingItemDecoration(16)
            addItemDecoration(topSpacingDecorator)
            adapter = ContactRecyclerAdapter(contacts)
            (adapter as ContactRecyclerAdapter).notifyDataSetChanged()
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