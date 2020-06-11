package com.e.kadai_07

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import androidx.fragment.app.FragmentActivity
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

        //apiコール
        val url = "https://qiita.com/api/v2/items?page=1&per_page=20"
        val request = Request.Builder().url(url).build()
        val client = OkHttpClient()

        client.newCall(request).enqueue(object : Callback {
            override fun onResponse(call: okhttp3.Call, response: okhttp3.Response) {
                val body = response.body?.string()
                val gson = GsonBuilder().create()
                val homeFeed = gson.fromJson(body, Array<HomeFeed>::class.java)

                runOnUiThread {
                    val adapter = MainAdapter(homeFeed)
                    val layoutManager = LinearLayoutManager(this@MainActivity)

                    fragment_recyclerview.layoutManager = layoutManager
                    fragment_recyclerview.adapter = adapter
                    fragment_recyclerview.setHasFixedSize(true)

                    //リスナーの設定
                    adapter.setOnItemClickListener(object : MainAdapter.OnItemClickListener {
                        override fun onItemClickListener(view: View, position: Int, url: String) {
                            val fragment = WebViewFragment.newInstance(url)
                            val fragmentTransaction = supportFragmentManager.beginTransaction()

                            fragmentTransaction.setCustomAnimations(
                                R.anim.slide_in_right, R.anim.slide_out_left,
                                R.anim.slide_in_left, R.anim.slide_out_right
                            )
                            fragmentTransaction.add(R.id.container, fragment, "fragment")
                            fragmentTransaction.addToBackStack("fragment")

                            fragmentTransaction.commit()
                        }
                    })
                }
            }
            override fun onFailure(call: okhttp3.Call, e: IOException) {
                println("Failed")
            }
        })
    }
}





