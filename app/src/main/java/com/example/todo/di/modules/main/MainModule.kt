package com.example.todo.di.modules.main

import androidx.lifecycle.ViewModel
import com.example.todo.di.modules.viemodel.ViewModelKey
import com.example.todo.ui.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Suppress("unused")
@Module
internal abstract class MainModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindLaunchViewModel(viewModel: MainViewModel): ViewModel
}
