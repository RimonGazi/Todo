package com.example.todo.di

import com.example.todo.di.modules.main.MainModule
import com.example.todo.di.scope.ActivityScoped
import com.example.todo.ui.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class ActivityBindingModule {

    @ActivityScoped
    @ContributesAndroidInjector(modules = [MainModule::class])
    internal abstract fun mainActivity(): MainActivity
}
