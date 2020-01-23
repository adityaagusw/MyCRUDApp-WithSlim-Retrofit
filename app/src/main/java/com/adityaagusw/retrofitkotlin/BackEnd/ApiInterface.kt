package com.adityaagusw.retrofitkotlin.BackEnd

import com.adityaagusw.retrofitkotlin.Model.DefaultResponse
import com.adityaagusw.retrofitkotlin.Model.GetUsers
import com.adityaagusw.retrofitkotlin.Model.Mahasiswa
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiInterface {

    @FormUrlEncoded
    @POST("membuatuser")
    fun membuatUser(
        @Field("nama") nama:String,
        @Field("jurusan") jurusan:String,
        @Field("alamat") alamat:String
    ): Call<DefaultResponse>

    @GET("allusers")
    fun dapatUser(): Call<GetUsers>

    @FormUrlEncoded
    @POST("updateuser")
    fun updateUser(
        @Field("nama") nama:String,
        @Field("jurusan") jurusan:String,
        @Field("alamat") alamat:String
    ): Call<DefaultResponse>

    @FormUrlEncoded
    @POST("cariuser")
    fun cariUser(
        @Field("nama") nama: String
    ): Call<GetUsers>

}