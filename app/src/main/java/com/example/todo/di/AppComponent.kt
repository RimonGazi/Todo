package com.example.todo.di

import com.example.todo.di.modules.app.AppModule
import com.example.todo.di.modules.api.ApiModule
import com.example.todo.di.modules.viemodel.ViewModelModuleFactory
import com.example.todo.TodoApplication
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        AppModule::class,
        ApiModule::class,
        ViewModelModuleFactory::class,
        ActivityBindingModule::class]
)
interface AppComponent : AndroidInjector<TodoApplication> {

    @Component.Factory
    interface Factory : AndroidInjector.Factory<TodoApplication>
}
