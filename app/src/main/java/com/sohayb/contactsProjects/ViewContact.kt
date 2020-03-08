package com.sohayb.contactsProjects

import android.content.DialogInterface
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.view_contact.*

class ViewContact : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.view_contact)

        val im = "https://eitrawmaterials.eu/wp-content/uploads/2016/09/person-icon.png"


        var bundles = intent.extras

        val Nom: String? = bundles!!.getString("ContactSurname")
        val Prenom: String? = bundles!!.getString("ContactName")
        val phoneNum: String? = bundles!!.getString("ContactNumber")
        val image: String? = bundles!!.getString("ContactImage")
        val address: String? = bundles!!.getString("ContactAddress")

        Picasso.get().load(image).into(imageView);
        PrenomTextView.text = Prenom
        AdressTextView.text = address
        NumTextView.text = phoneNum

        NomTextView.text = Nom

        imageView.setOnClickListener {
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

        alert.setTitle("Choose image")

        alert.show()


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 111 && resultCode == RESULT_OK) {
            val selectedFile = data?.data //The uri with the location of the file

            imageView.setImageURI(selectedFile)
            imageView.adjustViewBounds = true

        }
        if (requestCode == 1 && resultCode == RESULT_OK) {
            val imageBitmap = data!!.extras!!.get("data") as Bitmap
            imageView.setImageBitmap(imageBitmap)
            imageView.adjustViewBounds = true
        }


    }
}