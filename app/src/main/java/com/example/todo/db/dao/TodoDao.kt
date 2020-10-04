package com.example.todo.db.dao

import androidx.lifecycle.LiveData
import androidx.lifecycle.distinctUntilChanged
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.example.todo.db.table.Todo

@Dao
abstract class TodoDao : BaseDao<Todo> {

    @Transaction
    @Query("SELECT * FROM todo_table ORDER BY id ASC")
    abstract fun getTodoListData(): LiveData<List<Todo>>

    fun getTodoListDataDistinctLiveData(): LiveData<List<Todo>> =
        getTodoListData().distinctUntilChanged()
}
