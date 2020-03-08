package com.sohayb.contactsProjects

import android.content.DialogInterface
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.modify_contact.*

class ModifyContact : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.modify_contact)

        var bundles = intent.extras

        val Nom: String? = bundles!!.getString("ContactSurname")
        val Prenom: String? = bundles!!.getString("ContactName")
        val phoneNum: String? = bundles!!.getString("ContactNumber")
        val image: String? = bundles!!.getString("ContactImage")
        val address: String? = bundles!!.getString("ContactAddress")

        Picasso.get().load(image).into(EimageView);
        EAdressTextView.setText(address)
        ENumTextView.setText(phoneNum)
        EPrenomTextView.setText(Prenom)
        ENomTextView.setText(Nom)


        buttonModify.setOnClickListener {

            if (ENomTextView.text.toString() != "") {
                /* val destination = Intent(this, MainActivity::class.java).apply {
                     putExtra("ContactName", "contact.Nom")
                     putExtra("ContactSurname", "contact.Prenom")
                     putExtra("ContactAddress", "contact.address")
                     putExtra("ContactImage", "contact.image")
                     putExtra("ContactNumber", "contact.phoneNum")
                 }*/
                //startActivity(destination)
                //@todo set contact name to this : contact.Nom.text = ENomTextView.text.toString()
                Log.i("tag", "From 1")
                Toast.makeText(this, "You changed nada", Toast.LENGTH_SHORT).show();
            }
            if (EAdressTextView.text.toString() != "") {
                Log.i("tag", "From 2")
                Toast.makeText(this, "You changed nada", Toast.LENGTH_SHORT).show();
            }
            if (EPrenomTextView.text.toString() != "") {
                Log.i("tag", "From 3")
                Toast.makeText(this, "You changed nada", Toast.LENGTH_SHORT).show();
            }
            if (ENumTextView.text.toString() != "") {
                Log.i("tag", "From 4")
                Toast.makeText(this, "You changed nada", Toast.LENGTH_SHORT).show();
            }
            onBackPressed()

        }

        EimageView.setOnClickListener {
            dispatchTakePictureIntent()
        }


    }


    private fun dispatchTakePictureIntent() {

        //Alert dialog
        val dialogBuilder = AlertDialog.Builder(this)
        dialogBuilder.setMessage("From where would you like to get the picture ?")
            .setPositiveButton("Storage", DialogInterface.OnClickListener { _, _ ->
                //it, id ->
                val intent = Intent()
                    .setType("*/*")
                    .setAction(Intent.ACTION_GET_CONTENT)

                startActivityForResult(Intent.createChooser(intent, "Select a file"), 111)

            })
            .setNegativeButton("Take picture", DialogInterface.OnClickListener { _, _ ->
                //it, id ->
                val REQUEST_IMAGE_CAPTURE = 1
                Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
                    takePictureIntent.resolveActivity(packageManager)?.also {
                        startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
                    }
                }
            })

        val alert = dialogBuilder.create()

        alert.setTitle("You are connected")

        alert.show()


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 111 && resultCode == RESULT_OK) {
            val selectedFile = data?.data //The uri with the location of the file

            EimageView.setImageURI(selectedFile)
            EimageView.adjustViewBounds = true
        }
        if (requestCode == 1 && resultCode == RESULT_OK) {
            val imageBitmap = data!!.extras!!.get("data") as Bitmap
            EimageView.setImageBitmap(imageBitmap)
            EimageView.adjustViewBounds = true
        }


    }


    override fun onBackPressed() {
        finish()
        //super.onBackPressed()


    }

}