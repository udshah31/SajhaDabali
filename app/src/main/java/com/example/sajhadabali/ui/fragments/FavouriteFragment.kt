package com.example.sajhadabali.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.core.view.isNotEmpty
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sajhadabali.ui.NewsViewModel
import com.example.sajhadabali.R
import com.example.sajhadabali.ui.HomeActivity
import com.example.sajhadabali.ui.adapter.NewsAdapter
import com.example.sajhadabali.utils.Resource
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_favourite.*

class FavouriteFragment : Fragment(R.layout.fragment_favourite) {

    lateinit var viewModel: NewsViewModel
    private val newsAdapter by lazy { NewsAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = (activity as HomeActivity).viewModel

        setUpRecycleView()

        newsAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putParcelable("news", it)
            }

            findNavController().navigate(R.id.action_nav_favourite_to_nav_detail, bundle)
        }

        val itemTouchHelperCallback = object :
            ItemTouchHelper.SimpleCallback(
                ItemTouchHelper.UP or ItemTouchHelper.DOWN,
                ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
            ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val news = newsAdapter.getAdapterPosition(position);
                viewModel.deleteNew(news)
                Snackbar.make(view, "Successfully deleted Saved News!!!", Snackbar.LENGTH_LONG)
                    .show()
            }
        }

        ItemTouchHelper(itemTouchHelperCallback).apply {
            attachToRecyclerView(favourite_list)
        }


        viewModel.getSavedNews().observe(viewLifecycleOwner, { news ->
            if (news.isEmpty()) {
                favourite_empty.visibility = View.VISIBLE
                favourite_list.visibility = View.INVISIBLE
            } else {
                favourite_list.visibility = View.VISIBLE
                newsAdapter.setData(news)
            }

        })
    }


    private fun setUpRecycleView() {
        favourite_list.adapter = newsAdapter
        favourite_list.layoutManager = LinearLayoutManager(activity)
    }
}