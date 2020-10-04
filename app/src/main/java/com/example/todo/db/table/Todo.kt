package com.example.todo.db.table


import androidx.room.Entity
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
@Entity(primaryKeys = ["id"], tableName = "todo_table")
data class Todo(
    @Json(name = "completed")
    val completed: Boolean,
    @Json(name = "id")
    val id: Int,
    @Json(name = "title")
    val title: String,
    @Json(name = "userId")
    val userId: Int
)
