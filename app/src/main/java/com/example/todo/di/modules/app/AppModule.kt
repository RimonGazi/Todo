package com.example.todo.di.modules.app

import android.content.Context
import androidx.room.Room
import com.example.todo.TodoApplication
import com.example.todo.db.TodoDatabase
import com.example.todo.db.dao.TodoDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    fun provideApplicationContext(mApplication: TodoApplication): Context =
        mApplication.applicationContext

    @Singleton
    @Provides
    fun provideDatabase(app: TodoApplication): TodoDatabase {
        return Room
            .databaseBuilder(app, TodoDatabase::class.java, "todoDatabase.db")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideTodoDao(db: TodoDatabase): TodoDao {
        return db.todoDao()
    }
}
