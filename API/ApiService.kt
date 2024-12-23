package com.example.testridenow.API

import com.google.gson.annotations.SerializedName
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

// Data classes should match backend field names
data class User(
    @SerializedName("_id") val id: String?,  // MongoDB generates `_id`
    @SerializedName("name") val name: String,
    @SerializedName("password") val password: String
)

data class Motorcycle(
    @SerializedName("_id") val id: String?,  // MongoDB generates `_id`
    @SerializedName("name") val name: String,
    @SerializedName("model") val model: String,
    @SerializedName("year") val year: Int,
    @SerializedName("power") val power: Int
)

interface ApiService {
    @GET("api/users")
    fun getUsers(): Call<List<User>>

    @GET("api/motorcycles")
    fun getMotorcycles(): Call<List<Motorcycle>>

    @POST("api/users")
    @FormUrlEncoded
    fun addUser(
        @Field("name") name: String,
        @Field("password") password: String
    ): Call<User>

    @POST("api/motorcycles")
    @FormUrlEncoded
    fun addMotorcycle(
        @Field("name") name: String,
        @Field("model") model: String,
        @Field("year") year: Int,
        @Field("power") power: Int
    ): Call<Motorcycle>
}
