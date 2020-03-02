package com.sohayb.tplistecontacts

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.Nullable
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.sohayb.tplistecontacts.Model.Contact
import kotlinx.android.synthetic.main.contacts_view.view.*

class ContactRecyclerAdapter(val contacts: ArrayList<Contact>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>()
{

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ContactsViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.contacts_view, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ContactsViewHolder -> {
                holder.bind(contacts.get(position))
            }

        }
    }

    override fun getItemCount(): Int = contacts.size


    inner class ContactsViewHolder
    constructor(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener,
        View.OnLongClickListener {


        val contactName = itemView.name
        val ContactImage = itemView.ContactImages

        fun bind(contact: Contact) {

            val requestOptions = RequestOptions()
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)

            Glide.with(itemView.context)
                .applyDefaultRequestOptions(requestOptions)
                .load(contact.image)
                .into(ContactImage)
            contactName.text = contact.Nom
            // blog_author.setText(blogPost.username)

        }

        override fun onLongClick(view: View): Boolean {
            Toast.makeText(view.context, "long click", Toast.LENGTH_SHORT).show()
            // Return true to indicate the click was handled
            return true
        }

        override fun onClick(v: View?) {
            Toast.makeText(v!!.context, "Short click", Toast.LENGTH_SHORT).show()
        }


    }

}