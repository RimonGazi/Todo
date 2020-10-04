package com.example.todo.util

import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout

@BindingAdapter("swipeRefreshColors")
fun setSwipeRefreshColors(swipeRefreshLayout: SwipeRefreshLayout, colorResIds: IntArray) {
    swipeRefreshLayout.setColorSchemeColors(*colorResIds)
}
