package com.example.todo.api

import androidx.lifecycle.LiveData
import com.example.todo.db.table.Todo
import retrofit2.http.GET


interface ApiService {

    @GET("todos")
    fun getTodo(): LiveData<ApiResponse<List<Todo>>>

}
