package com.e.kadai_07

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.IOException

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fragment_recyclerview.layoutManager = LinearLayoutManager(this)
        fetchJson()

        AnimationUtils.loadAnimation(this, R.anim.slide_in_right)
        AnimationUtils.loadAnimation(this, R.anim.slide_out_left)
        AnimationUtils.loadAnimation(this, R.anim.slide_in_left)
        AnimationUtils.loadAnimation(this, R.anim.slide_out_right)
    }

    private fun fetchJson() {
        val url = "https://qiita.com/api/v2/items?page=1&per_page=20"
        val request = Request.Builder().url(url).build()
        val client = OkHttpClient()

        client.newCall(request).enqueue(object: Callback {
            override fun onResponse(call: okhttp3.Call, response: okhttp3.Response) {
                val body = response.body?.string()
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
