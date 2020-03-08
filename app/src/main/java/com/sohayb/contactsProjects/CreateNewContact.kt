package com.sohayb.contactsProjects

import android.app.Activity
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.util.Patterns
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.create_contact.*
import kotlinx.android.synthetic.main.modify_contact.*
import java.io.ByteArrayOutputStream
import java.net.URI


class CreateNewContact : Activity() {

    var imageBitmap: Bitmap? = null
    var selectedFile: Uri? = null
    var B: Boolean = false
    var U: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.create_contact)
        ButtonImage.setVisibility(View.VISIBLE);
        var nom: String? = null
        var prenom: String? = null
        var address: String? = null
        var numPhone: String? = null
        var image: String? = null



        ButtonImage.setOnClickListener {
            dispatchTakePictureIntent()


        }
        CimageView.setOnClickListener {
            dispatchTakePictureIntent()
        }

        CButton.setOnClickListener {

            if (CAdressEditText.text.toString() != "") {
                address = CAdressEditText.text.toString()
                setResult(RESULT_OK, intent);
            }
            if (CNomEditText.text.toString() != "") {
                nom = CNomEditText.text.toString()
            }
            if (CNumEditText.text.toString() != "") {


                if (PhoneIsValid(CNumEditText.text.toString())) {
                    numPhone = CNumEditText.text.toString()
                } else {

                    setResult(RESULT_CANCELED)
                    onBackPressed()
                }

            }
            if (CPrenomEditText.text.toString() != "") {

                prenom = CPrenomEditText.text.toString()
            }

            if (CimageView != null) {
                Log.i("tagB", imageBitmap.toString())
                Log.i("tagS", selectedFile.toString())

                if (imageBitmap == null) {
                    image = selectedFile.toString()

                } else {
                    image = imageBitmap.toString()
                }


            }


            val intent = Intent().apply {
                putExtra("ContactName", nom)
                putExtra("ContactSurname", prenom)
                putExtra("ContactAddress", address)
                putExtra("ContactNumber", numPhone)
            }

            if (B && !U) {
                intent.apply {
                    putExtra("ContactImage", imageBitmap)
                }
            } else {
                intent.apply {
                    putExtra("ContactImage", image)
                }
            }
            setResult(RESULT_OK, intent)
            onBackPressed()
        }
    }

    fun PhoneIsValid(phone: String): Boolean =
        when {
            Patterns.PHONE.matcher(phone).matches() -> true
            else -> false
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

        alert.setTitle("SELECT IMAGE ")

        alert.show()


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 111 && resultCode == AppCompatActivity.RESULT_OK) {
            // FROM STORAGE
            selectedFile = data?.data //The uri with the location of the file
            CimageView.setImageURI(selectedFile)
            CimageView.adjustViewBounds = true
            ButtonImage.setVisibility(View.GONE);
            B = false
            U = true
        }
        if (requestCode == 1 && resultCode == AppCompatActivity.RESULT_OK) {
            //WITH  CAMERA
            imageBitmap = data!!.extras!!.get("data") as Bitmap
            CimageView.setImageBitmap(imageBitmap)
            CimageView.adjustViewBounds = true
            ButtonImage.setVisibility(View.GONE);
            B = true
            U = false
        }
    }


}