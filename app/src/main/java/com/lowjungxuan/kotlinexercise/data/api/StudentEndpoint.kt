package com.lowjungxuan.kotlinexercise.data.api

import com.lowjungxuan.kotlinexercise.student.data.Student
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.http.*

interface StudentEndpoint {
    //    https://stackoverflow.com/questions/58567053/how-to-add-url-parameter-in-a-retrofit-get-request-in-kotlin
    @GET("student/")
    suspend fun findAll(): List<Student>

    @POST("student/")
    suspend fun create(@Body requestBody: RequestBody): Student

    @PUT("student/{roll_no}")
    suspend fun update(@Body requestBody: RequestBody, @Path("roll_no") roll_no: Int): ResponseBody

    @DELETE("student/")
    suspend fun deleteAll(): ResponseBody

    @GET("student/{roll_no}")
    suspend fun findOne(@Path("roll_no") roll_no: Int): Student?
}