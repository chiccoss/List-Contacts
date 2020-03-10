package com.sohayb.contactsProjects

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.util.Patterns
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.modify_contact.*
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.OutputStream
import java.util.*

class ModifyContact : AppCompatActivity() {
    var imageBitmap: Bitmap? = null
    var selectedFile: Uri? = null

    var nom: String? = null
    var prenom: String? = null
    var address: String? = null
    var numPhone: String? = null
    var image: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.modify_contact)

        var bundles = intent.extras

        var Nom: String? = bundles!!.getString("ContactSurname")
        var Prenom: String? = bundles!!.getString("ContactName")
        var phoneNum: String? = bundles!!.getString("ContactNumber")
        var Image: String? = bundles!!.getString("ContactImage")
        var Address: String? = bundles!!.getString("ContactAddress")

        EimageView.setOnClickListener {
            dispatchTakePictureIntent()
        }

        Picasso.get().load(Image).into(EimageView);
        EAdressTextView.setText(Address)
        ENumTextView.setText(phoneNum)
        EPrenomTextView.setText(Prenom)
        ENomTextView.setText(Nom)


        buttonModify.setOnClickListener {
            if (EAdressTextView.text.toString() != "") {
                address = EAdressTextView.text.toString()
                setResult(Activity.RESULT_OK, intent);
            }
            if (ENomTextView.text.toString() != "") {
                nom = ENomTextView.text.toString()
                Nom = nom
            }
            if (ENumTextView.text.toString() != "") {


                if (PhoneIsValid(ENumTextView.text.toString())) {
                    numPhone = ENumTextView.text.toString()
                } else {

                    setResult(Activity.RESULT_CANCELED)
                    onBackPressed()
                }

            }
            if (EPrenomTextView.text.toString() != "") {

                prenom = EPrenomTextView.text.toString()
            }

            if (EimageView != null) {
                image = selectedFile.toString()
            }

            val intent = Intent().apply {
                putExtra("ContactName", nom)
                putExtra("ContactSurname", prenom)
                putExtra("ContactAddress", address)
                putExtra("ContactNumber", numPhone)
                putExtra("ContactImage", image)
            }
            setResult(Activity.RESULT_OK, intent)
            onBackPressed()
        }

    }

    private fun PhoneIsValid(phone: String): Boolean =
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
            EimageView.setImageURI(selectedFile)
            EimageView.adjustViewBounds = true

        }
        if (requestCode == 1 && resultCode == AppCompatActivity.RESULT_OK) {
            //WITH  CAMERA
            imageBitmap = data!!.extras!!.get("data") as Bitmap
            EimageView.setImageBitmap(imageBitmap)
            EimageView.adjustViewBounds = true
            selectedFile = bitmapToFile(imageBitmap)

        }
    }

    private fun bitmapToFile(bitmap: Bitmap?): Uri {
        // Get the context wrapper
        val wrapper = ContextWrapper(applicationContext)

        // Initialize a new file instance to save bitmap object
        var file = wrapper.getDir("Images", Context.MODE_PRIVATE)
        file.let { File(it, "${UUID.randomUUID()}.jpg") }

        try {
            // Compress the bitmap and save in jpg format
            val stream: OutputStream = FileOutputStream(file)
            bitmap!!.compress(Bitmap.CompressFormat.JPEG, 100, stream)
            stream.flush()
            stream.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }

        // Return the saved bitmap uri
        return Uri.parse(file.absolutePath)
    }
}