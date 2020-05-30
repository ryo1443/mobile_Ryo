package com.e.kadai_04

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Paper_btn.setOnClickListener {
            janken(Hand.Paper)
        }
        Rock_btn.setOnClickListener {
            janken(Hand.Rock)
        }
        Scissors_btn.setOnClickListener {
            janken(Hand.Scissors)
        }
    }

    fun janken(HandType:Hand){
        val cp =(0..2).random()
        var CP:Hand?=null

        when(cp){
            0->{CP=Hand.Rock}
            1->{CP=Hand.Scissors}
            2->{CP=Hand.Paper}
        }
        CP?.let{
            if(HandType==it){
                textView.text="You ${Result.Draw}! cp is $it" //あいこ
            }else if((HandType==Hand.Rock && it==Hand.Scissors)||(HandType==Hand.Scissors && it==Hand.Paper)||(HandType==Hand.Paper && it==Hand.Rock)){
                textView.text="You ${Result.Win}! cp is $it"  //勝ち
            }else{
                textView.text="You ${Result.Lose}! cp is $it"  //負け
            }
        }
        if(CP==null) textView.text="Error"
    }
}
enum class Hand{
    Rock,
    Paper,
    Scissors
}

enum class Result{
    Win,
    Lose,
    Draw
}
class Test:AppCompatActivity(){
    fun test(){
        textView.text="test"
    }
}