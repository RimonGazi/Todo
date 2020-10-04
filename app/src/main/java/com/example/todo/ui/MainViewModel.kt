package com.example.todo.ui

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.example.todo.db.table.Todo
import com.example.todo.domain.Event
import com.example.todo.network.Resource
import com.example.todo.network.Status
import timber.log.Timber

import javax.inject.Inject

class MainViewModel @Inject constructor(private val repository: TodoRepository) : ViewModel() {

    private val result = MediatorLiveData<Resource<List<Todo>>>()
    val todoItemLiveData = MediatorLiveData<List<Todo>>()
    val refreshState = MutableLiveData<Status>()
    val messageEvent = MutableLiveData<Event<String>>()

    init {
        result.observeForever(todoResultObserver())
        loadTodo()
    }

    private fun todoResultObserver(): Observer<Resource<List<Todo>>> {
        return Observer {
            Timber.d(it.status.toString())
            refreshState.value = it.status
            if (it.status == Status.ERROR) {
                messageEvent.value = Event(it.message ?: "Unknown error")
            }
            it.data.let { data -> todoItemLiveData.value = data }
        }
    }

    fun refresh() = loadTodo()

    private fun loadTodo() {
        val source = repository.getTodo()
        result.removeSource(source)
        result.addSource(source) {
            result.value = it
            if (it.status == Status.ERROR || it.status == Status.SUCCESS)
                result.removeSource(source)
        }
    }

    override fun onCleared() {
        super.onCleared()
        result.removeObserver(todoResultObserver())
    }
}
