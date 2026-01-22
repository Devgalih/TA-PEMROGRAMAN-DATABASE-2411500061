package com.mobileapp.database

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {
    @GET("api.php")
    suspend fun getAllItems(): ApiResponse<List<Item>>

    @GET("api.php")
    suspend fun getItemById(@Query("id") id: Int): ApiResponse<Item>

    @POST("api.php")
    suspend fun createItem(@Body item: ItemRequest): ApiResponse<Item>
}
