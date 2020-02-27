package com.sohayb.tplistecontacts

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.sohayb.tplistecontacts.Model.Contact

class ContactRecyclerAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>()
{

    private var contacts: List<Contact> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getItemCount(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    /* private val TAG: String = "AppDebug"

     private var items: List<BlogPost> = ArrayList()

     override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
         return BlogViewHolder(
             LayoutInflater.from(parent.context).inflate(R.layout.riciclo_view, parent, false)
         )
     }

     override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
         when(holder) {

             is BlogViewHolder -> {
                 holder.bind(items.get(position))
             }

         }
     }

     override fun getItemCount(): Int {
         return items.size
     }



     fun submitList(blogList: List<BlogPost>){
         items = blogList
     }

     inner class BlogViewHolder
     constructor( itemView: View):
         RecyclerView.ViewHolder(itemView), View.OnClickListener, View.OnLongClickListener{


         val blog_image = itemView.blog_image
         val blog_title = itemView.blog_title
         val blog_author = itemView.blog_author

         fun bind(blogPost: BlogPost){

             val requestOptions = RequestOptions()
                 .placeholder(R.drawable.ic_launcher_background)
                 .error(R.drawable.ic_launcher_background)

             Glide.with(itemView.context)
                 .applyDefaultRequestOptions(requestOptions)
                 .load(blogPost.image)
                 .into(blog_image)
             blog_title.setText(blogPost.title)
             blog_author.setText(blogPost.username)

         }


 /*
         override fun onClick(view: View) {
             listen.onItemClick(blogPoste)
         }*/

         override fun onLongClick(view: View): Boolean {
             Toast.makeText(view.context, "long click", Toast.LENGTH_SHORT).show()
             // Return true to indicate the click was handled
             return true
         }

         override fun onClick(v: View?) {
             Toast.makeText(v!!.context, "Short click", Toast.LENGTH_SHORT).show()
         }


     }


 */
}