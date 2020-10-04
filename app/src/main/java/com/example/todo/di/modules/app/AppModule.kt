package com.example.todo.di.modules.app

import android.content.Context
import com.example.todo.TodoApplication
import dagger.Module
import dagger.Provides


@Module
class AppModule {

    @Provides
    fun provideApplicationContext(mApplication: TodoApplication): Context =
        mApplication.applicationContext
}
