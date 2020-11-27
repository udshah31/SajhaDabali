package com.example.sajhadabali.ui.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Html
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.sajhadabali.ui.NewsViewModel
import com.example.sajhadabali.R
import com.example.sajhadabali.ui.HomeActivity
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_detail.*

class DetailFragment : Fragment(R.layout.fragment_detail) {

    private val args: DetailFragmentArgs by navArgs()
    lateinit var viewModel: NewsViewModel

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as HomeActivity).viewModel

        val news = args.news
        detail_title.text = Html.fromHtml(news.title.rendered)
        val requestOptions = RequestOptions.placeholderOf(R.drawable.ic_launcher_background)
        Glide.with(this)
            .setDefaultRequestOptions(requestOptions)
            .load(news.image)
            .into(detail_image)


        val date = news.date
        val newDate = date!!.split("T")
        detail_time.text = newDate[0] + "  " + newDate[1]

        detail_description.text = Html.fromHtml(news.content.rendered)

        fav_icon.setOnClickListener {
            viewModel.saveNews(news)
            Snackbar.make(view, "News Saved Successfully!!!", Snackbar.LENGTH_LONG).show()
        }

    }

}