package com.example.sajhadabali.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sajhadabali.R
import com.example.sajhadabali.ui.HomeActivity
import com.example.sajhadabali.ui.NewsViewModel
import com.example.sajhadabali.ui.adapter.NewsAdapter
import com.example.sajhadabali.utils.Resource
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_top.*


class TopFragment : Fragment(R.layout.fragment_top) {

    lateinit var viewModel: NewsViewModel
    private val newsAdapter by lazy { NewsAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as HomeActivity).viewModel

        setUpRecyclerView()

        getData()

        newsAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putParcelable("news", it)
            }

            findNavController().navigate(R.id.action_nav_top_to_nav_detail, bundle)
        }


        pagination()


    }

    private fun getData() {
        viewModel.getTopNews()
        viewModel.topNews.observe(viewLifecycleOwner, { response ->

            when (response) {
                is Resource.Success -> {
                    loadingProgressBar.visibility = View.INVISIBLE
                    paginationProgressBar.visibility = View.INVISIBLE

                    response.data?.let { topNews ->
                        newsAdapter.setData(topNews.toList())
                    }
                }
                is Resource.Error -> {
                    loadingProgressBar.visibility = View.INVISIBLE
                    paginationProgressBar.visibility = View.INVISIBLE

                    no_internet.visibility = View.VISIBLE

                    response.message?.let { message ->
                        Snackbar.make(
                            requireView(),
                            "An error occurred: $message ",
                            Snackbar.LENGTH_LONG
                        )
                            .show()
                    }
                }

                is Resource.Loading -> {
                    loadingProgressBar.visibility = View.VISIBLE
                    paginationProgressBar.visibility = View.INVISIBLE
                }
            }
        })

    }

    private fun pagination() {
        top_list.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(1)) {
                    viewModel.getNextTopNews()
                    viewModel.topNews.observe(viewLifecycleOwner, { response ->
                        when (response) {
                            is Resource.Success -> {
                                loadingProgressBar.visibility = View.INVISIBLE
                                paginationProgressBar.visibility = View.INVISIBLE
                                response.data?.let { topNews ->
                                    newsAdapter.setData(topNews.toList())
                                }
                            }
                            is Resource.Error -> {
                                loadingProgressBar.visibility = View.INVISIBLE
                                paginationProgressBar.visibility = View.INVISIBLE

                                no_internet.visibility = View.VISIBLE

                                response.message?.let { message ->
                                    Snackbar.make(
                                        requireView(),
                                        "An error occurred: $message ",
                                        Snackbar.LENGTH_LONG
                                    )
                                        .show()
                                }
                            }

                            is Resource.Loading -> {
                                loadingProgressBar.visibility = View.INVISIBLE
                                paginationProgressBar.visibility = View.VISIBLE
                            }
                        }
                    })

                }
            }
        })
    }


    private fun setUpRecyclerView() {
        top_list.adapter = newsAdapter
        top_list.layoutManager = LinearLayoutManager(activity)
    }

}