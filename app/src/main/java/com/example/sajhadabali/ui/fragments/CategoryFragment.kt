package com.example.sajhadabali.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.sajhadabali.R
import com.example.sajhadabali.ui.HomeActivity
import com.example.sajhadabali.ui.NewsViewModel
import com.example.sajhadabali.ui.adapter.CategoryAdapter
import com.example.sajhadabali.utils.Resource
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_category.*

class CategoryFragment : Fragment(R.layout.fragment_category) {

    private lateinit var viewModel: NewsViewModel
    private val categoryAdapter by lazy { CategoryAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as HomeActivity).viewModel

        setUpRecyclerView()

        getData()

        categoryAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putParcelable("category", it)
            }

            findNavController().navigate(R.id.action_nav_category_to_nav_categoryListItem, bundle)
        }
    }

    private fun getData() {
        viewModel.getCategory()
        viewModel.categoryNews.observe(viewLifecycleOwner, { response ->

            when (response) {
                is Resource.Success -> {
                    loadingProgressBar.visibility = View.INVISIBLE

                    response.data?.let { categoryResponse ->
                        categoryAdapter.setData(categoryResponse)
                    }
                }

                is Resource.Error -> {
                    loadingProgressBar.visibility = View.INVISIBLE
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
                }

            }


        })
    }


    private fun setUpRecyclerView() {
        category_list.adapter = categoryAdapter
        category_list.layoutManager = GridLayoutManager(activity, 2)
    }


}