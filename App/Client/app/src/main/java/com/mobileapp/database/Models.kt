package com.mobileapp.database

import com.google.gson.annotations.SerializedName

data class Item(
    val id: Int,
    val name: String,
    val description: String?,
    val price: String,
    @SerializedName("created_at")
    val createdAt: String?,
    @SerializedName("updated_at")
    val updatedAt: String?
)

data class ItemRequest(
    val name: String,
    val description: String?,
    val price: Double
)

data class ApiResponse<T>(
    val status: String,
    val message: String? = null,
    val data: T,
    val count: Int? = null
)
