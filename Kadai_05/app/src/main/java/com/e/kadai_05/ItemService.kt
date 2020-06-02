package com.e.kadai_05

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

data class ItemEntity (
    val id: String,
    val title: String
)
interface ItemService {
    //GETで下のapiを叩けという命令を出す
    @GET("items")
    //中カッコ内をItemEntityに詰めろという命令
    fun items(@Query("page") page: Int, @Query("per_page") per_Page: Int): Call<List<ItemEntity?>?>?
}