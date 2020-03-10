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
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.sohayb.contactsProjects.Database.DataBaseHandler
import com.sohayb.contactsProjects.Model.Contact
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.create_contact.*
import kotlinx.android.synthetic.main.modify_contact.*
import java.io.*
import java.net.URI
import java.util.*


class CreateNewContact : Activity() {

    var imageBitmap: Bitmap? = null
    var selectedFile: Uri? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.create_contact)
        ButtonImage.setVisibility(View.VISIBLE);
        var nom: String? = null
        var prenom: String? = null
        var address: String? = null
        var numPhone: String? = null
        var image: String? = null


        //var db: DataBaseHandler?= DataBaseHandler(this)
        //db!!.getAllData()?.let { initRecyclerView(it) }
        //recycler_view.adapter!!.notifyDataSetChanged()
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
                image = selectedFile.toString()
            }

            val intent = Intent().apply {
                putExtra("ContactName", nom)
                putExtra("ContactSurname", prenom)
                putExtra("ContactAddress", address)
                putExtra("ContactNumber", numPhone)
                putExtra("ContactImage", image)
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
                    .setAction(Intent.ACTION_OPEN_DOCUMENT)

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
        }
        if (requestCode == 1 && resultCode == AppCompatActivity.RESULT_OK) {
            //WITH  CAMERA
            imageBitmap = data!!.extras!!.get("data") as Bitmap
            CimageView.setImageBitmap(imageBitmap)
            CimageView.adjustViewBounds = true
            selectedFile = bitmapToFile(imageBitmap)
            ButtonImage.setVisibility(View.GONE);
        }
    }


    private fun bitmapToFile(bitmap: Bitmap?): Uri {
        // Get the context wrapper
        val wrapper = ContextWrapper(applicationContext)

        // Initialize a new file instance to save bitmap object
        var file = wrapper.getDir("Images", Context.MODE_PRIVATE)
        file = File(file, "${UUID.randomUUID()}.jpg")

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

    private fun initRecyclerView(contacts: ArrayList<Contact?>) {


        recycler_view.apply {
            layoutManager = LinearLayoutManager(this@CreateNewContact)
            val topSpacingDecorator = TopSpacingItemDecoration(16)
            addItemDecoration(topSpacingDecorator)
            adapter = ContactRecyclerAdapter(contacts)
            (adapter as ContactRecyclerAdapter).notifyDataSetChanged()
        }
    }



}