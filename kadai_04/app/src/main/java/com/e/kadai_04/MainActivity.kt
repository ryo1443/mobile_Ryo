package com.e.kadai_04

import android.os.Bundle
import android.view.View
import android.widget.Switch
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    fun btn(v:View){
        when(v.getId()){
            Paper_btn.id->textView.text="Paper"
            Rock_btn.id->textView.text="Rock"
            Scissorce_btn.id->textView.text="Scissorce"

        }
    }


}

enum class Hand{
    Rock,
    Paper,
    Scissorce
}

enum class result{
    win,
    lose
}
class btn_input(val name:Hand){

}