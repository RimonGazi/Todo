package com.example.todo.di.modules.viemodel

import androidx.lifecycle.ViewModel
import dagger.MapKey
import kotlin.reflect.KClass

@MustBeDocumented
@Retention
@MapKey
annotation class ViewModelKey(val value: KClass<out ViewModel>)
