package com.example.todo.base.ui

import android.content.Context
import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import dagger.android.support.DaggerAppCompatActivity
import io.github.inflationx.viewpump.ViewPumpContextWrapper

abstract class BaseActivity<T : ViewDataBinding> constructor(@LayoutRes private val mContentLayoutId: Int) :
    DaggerAppCompatActivity() {

    protected val mBinding: T by lazy(LazyThreadSafetyMode.NONE) {
        DataBindingUtil.setContentView<T>(this, mContentLayoutId)
    }

    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding.lifecycleOwner = this
    }
}
