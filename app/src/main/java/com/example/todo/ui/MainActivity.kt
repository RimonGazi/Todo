package com.example.todo.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todo.R
import com.example.todo.base.ui.BaseActivity
import com.example.todo.databinding.ActivityMainBinding
import com.example.todo.di.modules.viemodel.AppViewModelFactory
import com.example.todo.domain.EventObserver
import com.example.todo.network.Status
import com.example.todo.ui.adapter.TodoAdapter
import com.example.todo.util.dividerItemDecorationVertical
import com.example.todo.util.snack
import javax.inject.Inject

class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    @Inject
    lateinit var viewModelFactory: AppViewModelFactory
    private val viewModel by viewModels<MainViewModel> { viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding.swipeRefresh.setOnRefreshListener {
            viewModel.refresh()
        }
        viewModel.refreshState.observe(this, Observer {
            mBinding.swipeRefresh.isRefreshing = it == Status.LOADING
        })

        mBinding.swipeRefresh.setOnRefreshListener {
            viewModel.refresh()
        }
        viewModel.messageEvent.observe(this, EventObserver {
            mBinding.root.snack(it)
        })
        val todoAdapter = TodoAdapter()
        mBinding.todoRecyclerView.apply {
            adapter = todoAdapter
            addItemDecoration(context.dividerItemDecorationVertical)
        }

        viewModel.todoItemLiveData.observe(this, Observer {
            todoAdapter.submitList(it) {
                val layoutManager = (mBinding.todoRecyclerView.layoutManager as LinearLayoutManager)
                val position = layoutManager.findFirstCompletelyVisibleItemPosition()
                if (position != RecyclerView.NO_POSITION) mBinding.todoRecyclerView.scrollToPosition(
                    position
                )
            }
        })
    }
}
