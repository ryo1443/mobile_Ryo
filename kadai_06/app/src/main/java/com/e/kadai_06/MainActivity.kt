package com.e.kadai_06

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_fragment.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var isFragmentShow: Boolean = false
        val fragmentManager = supportFragmentManager

        button.setOnClickListener() {
            if (isFragmentShow == false) {
                val fragmentTransaction = fragmentManager.beginTransaction()

                fragmentTransaction.add(R.id.container, FragmentActivity(), "test")
                fragmentTransaction.addToBackStack(null)
                fragmentTransaction.commit()
                isFragmentShow = true
            } else {
                val fragmentTransaction = fragmentManager.beginTransaction()

                fragmentManager.findFragmentByTag("test")?.let{
                    fragmentTransaction.remove(it)
                }

//                fragmentManager.popBackStack()
                fragmentTransaction.commit()
                isFragmentShow = false
            }
        }
    }
}
