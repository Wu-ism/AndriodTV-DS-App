package com.example.myapplication.data.api

import com.example.myapplication.data.model.ContentModel
import retrofit2.http.GET

interface ApiService {
    @GET("api/content")
    suspend fun getContent(): List<ContentModel>
}