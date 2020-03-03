package com.sohayb.tplistecontacts

import android.app.Activity
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.request.RequestOptions
import com.sohayb.tplistecontacts.Model.Contact
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.contacts_view.view.*


class ContactRecyclerAdapter(val contacts: ArrayList<Contact>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    var context: Context? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        context = parent.getContext();
        return ContactsViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.contacts_view,
                parent,
                false
            )
        )


    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ContactsViewHolder -> {
                holder.bind(contacts.get(position), position)


            }


        }
    }

    override fun getItemCount(): Int = contacts.size


    inner class ContactsViewHolder
    constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {


        val contactName = itemView.name
        val ContactImage = itemView.ContactImages

        fun bind(contact: Contact, position: Int) {


            val requestOptions = RequestOptions()
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)

            /*Glide.with(itemView.context)
                .applyDefaultRequestOptions(requestOptions)
                .load(contact.image)
                .into(ContactImage)*/

            Picasso.get().load(contact.image).into(ContactImage);
            contactName.text = contact.Nom
            // blog_author.setText(blogPost.username)


            itemView.setOnClickListener(View.OnClickListener {
                /*  val destination = Intent(context, ShowContact::class.java)
                      .also { it.putExtra("name", contactName?.text)
                           }
                  (context as Activity).startActivityForResult(destination, 1)*/
                Toast.makeText(it!!.context, position.toString(), Toast.LENGTH_SHORT).show()
            })

            itemView.setOnLongClickListener(View.OnLongClickListener {


                Toast.makeText(it.context, "long click", Toast.LENGTH_SHORT).show()
                true
            })


        }


    }
}


/*    val dialogBuilder = context?.let { it1 -> AlertDialog.Builder(it1) }
                dialogBuilder!!.setMessage("Do you want to close this application ?")
                    .setPositiveButton("Proceed", DialogInterface.OnClickListener { it, id ->
                        //listeEquipes.removeAt(i)
                        //adapter.notifyDataSetChanged()
                        //finish()
                    })
                    .setNegativeButton("Cancel", DialogInterface.OnClickListener { it, id ->
                        it.cancel()
                    })

                val alert = dialogBuilder.create()

                alert.setTitle("You are connected")

                alert.show()*/