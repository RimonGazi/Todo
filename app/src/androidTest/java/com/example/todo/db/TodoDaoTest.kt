package com.example.todo.db

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.runner.AndroidJUnit4
import com.example.todo.db.table.Todo
import com.example.todo.util.getOrAwaitValue
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class TodoDaoTest : DbTest() {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Test
    fun insertAndLoad() {
        val todo = Todo(completed = true, id = 1, title = "title", userId = 2)
        val todoList = ArrayList<Todo>()
        todoList.add(todo)
        db.todoDao().insert(todoList)

        val loaded = db.todoDao().getTodoListDataDistinctLiveData().getOrAwaitValue()
        assertThat(loaded.size, `is`(todoList.size))

        val mRetrieveTodo: Todo = loaded[0]

        assertThat(mRetrieveTodo.completed, `is`(true))
        assertThat(mRetrieveTodo.id, `is`(1))
        assertThat(mRetrieveTodo.title, `is`("title"))
        assertThat(mRetrieveTodo.userId, `is`(2))
    }
}
