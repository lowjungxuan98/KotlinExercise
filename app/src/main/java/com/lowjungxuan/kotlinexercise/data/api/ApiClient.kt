package com.lowjungxuan.kotlinexercise.data.api

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {
    companion object {
        fun apiService(): StudentEndpoint {
            val gson = GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:sss").create()
            return Retrofit.Builder()
                .baseUrl("http://10.0.2.2:3000/api/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build().create(StudentEndpoint::class.java)
        }
    }
}
