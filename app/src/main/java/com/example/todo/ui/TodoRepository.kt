package com.example.todo.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.todo.api.ApiService
import com.example.todo.db.dao.TodoDao
import com.example.todo.db.table.Todo
import com.example.todo.network.NetworkBoundResource
import com.example.todo.network.Resource
import com.example.todo.util.AppExecutors
import javax.inject.Inject

class TodoRepository @Inject constructor(
    private val appExecutors: AppExecutors,
    private val apiService: ApiService,
    private val todoDao: TodoDao
) : ViewModel() {

    fun getTodo(): LiveData<Resource<List<Todo>>> {
        return object : NetworkBoundResource<List<Todo>, List<Todo>>(appExecutors) {

            override fun saveCallResult(item: List<Todo>) = todoDao.insert(item)

            override fun shouldFetch(data: List<Todo>?) = true

            override fun loadFromDb(): LiveData<List<Todo>> =
                todoDao.getTodoListDataDistinctLiveData()

            override fun createCall() = apiService.getTodo()

        }.asLiveData()
    }
}
