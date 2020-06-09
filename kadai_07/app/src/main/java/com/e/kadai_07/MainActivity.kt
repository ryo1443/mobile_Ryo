package com.e.kadai_07

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import kotlin.Unit.toString
import kotlin.coroutines.EmptyCoroutineContext.toString

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fragment_recyclerview.layoutManager = LinearLayoutManager(this)
        fetchJson()
    }

    fun fetchJson() {
        val url = "https://qiita.com/api/v2/items?page=1&per_page=20"
        val request = Request.Builder().url(url).build()
        val client = OkHttpClient()

        client.newCall(request).enqueue(object: Callback {
            override fun onResponse(call: okhttp3.Call, response: okhttp3.Response) {
                val body = response.body?.string()
                println(body)

                val gson = GsonBuilder().create()
                val homeFeed = gson.fromJson(body, Array<HomeFeed>::class.java)

                runOnUiThread{
                    fragment_recyclerview.adapter = MainAdapter(homeFeed)
                }

            }
            override fun onFailure(call: okhttp3.Call, e: IOException) {
                println("Failed")
            }
        })
    }
}
