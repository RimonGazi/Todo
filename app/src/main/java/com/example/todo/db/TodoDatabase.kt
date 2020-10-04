package com.example.todo.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.todo.db.dao.TodoDao
import com.example.todo.db.table.Todo

@Database(
    entities = [Todo::class],
    version = 1,
    exportSchema = false
)
abstract class TodoDatabase : RoomDatabase() {
    abstract fun todoDao(): TodoDao
}
