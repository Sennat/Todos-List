package com.project.todoslist.network

import com.project.todoslist.model.TodosItems
import retrofit2.Response
import retrofit2.http.GET

interface RetrofitApi {
    @GET("todos")
    suspend fun getTodos(): Response<List<TodosItems>>
}