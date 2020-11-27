package com.example.sajhadabali.ui.adapter


import android.annotation.SuppressLint
import android.os.Build
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.sajhadabali.R
import com.example.sajhadabali.model.news.News
import kotlinx.android.synthetic.main.fragment_detail.*
import kotlinx.android.synthetic.main.item_news.view.*
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class NewsAdapter : RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    private var newsList = emptyList<News>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_news, parent, false)
        )
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val news = newsList[position]
        val requestOptions = RequestOptions.placeholderOf(R.drawable.ic_launcher_background)

        holder.itemView.apply {
            Glide.with(this)
                .setDefaultRequestOptions(requestOptions)
                .load(news.image)
                .into(item_image)

            item_title.text = Html.fromHtml(news.title.rendered)
            item_short_desc.text = Html.fromHtml(news.excerpt.rendered)

            val date = news.date
            val newDate = date!!.split("T")
            item_date.text = newDate[0] + "  " + newDate[1]



            setOnClickListener {
                onItemClickListener?.let { it(news) }
            }
        }
    }

    override fun getItemCount(): Int {
        return newsList.size
    }

    fun setData(news: List<News>) {
        newsList = news
        notifyDataSetChanged()
    }

    fun getAdapterPosition(position: Int): News {
        return newsList[position]
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    private var onItemClickListener: ((News) -> Unit)? = null

    fun setOnItemClickListener(listener: (News) -> Unit) {
        onItemClickListener = listener
    }


}