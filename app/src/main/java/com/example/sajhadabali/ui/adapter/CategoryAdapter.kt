package com.example.sajhadabali.ui.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.sajhadabali.R
import com.example.sajhadabali.model.category.Category
import kotlinx.android.synthetic.main.item_category.view.*
import kotlinx.android.synthetic.main.item_news.view.*


class CategoryAdapter : RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    private var categoryList = emptyList<Category>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_category, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val category = categoryList[position]
        //val requestOptions = RequestOptions.placeholderOf(R.drawable.ic_launcher_background)

        holder.itemView.apply {
            //Glide.with(this)
                //.setDefaultRequestOptions(requestOptions)
                //.load("")
                //.into(item_image)

            category_name.text = category.name

            setOnClickListener {
                onItemClickListener?.let { it(category) }
            }
        }
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }

    fun setData(category: List<Category>) {
        categoryList = category
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)


    private var onItemClickListener: ((Category) -> Unit)? = null

    fun setOnItemClickListener(listener: (Category) -> Unit) {
        onItemClickListener = listener
    }


}