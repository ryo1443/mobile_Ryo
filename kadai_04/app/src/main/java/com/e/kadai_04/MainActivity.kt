package com.e.kadai_04

import android.os.Bundle
import android.os.Handler
import android.view.animation.AlphaAnimation
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Paper_btn.setOnClickListener {
            button_anim(Paper_btn)
            janken(Hand.Paper)
        }
        Rock_btn.setOnClickListener {
            button_anim(Rock_btn)
            janken(Hand.Rock)
        }
        Scissors_btn.setOnClickListener {
            button_anim(Scissors_btn)
            janken(Hand.Scissors)
        }
    }

    fun janken(HandType:Hand){
        val cp =(0..2).random()
        var CP:Hand?=null
        var image:Int=R.drawable.paper

        when(cp){
            0-> {
                CP = Hand.Rock
                image = R.drawable.rock
            }
            1->{CP=Hand.Scissors
                image = R.drawable.scissorce
            }
            2->{CP=Hand.Paper
                image = R.drawable.paper
            }
        }
        CP?.let{
            if(HandType==it){
                hand_anim(it,image,Result.Draw)//あいこ
            }else if((HandType==Hand.Rock && it==Hand.Scissors)||(HandType==Hand.Scissors && it==Hand.Paper)||(HandType==Hand.Paper && it==Hand.Rock)){
                hand_anim(it,image,Result.Win)//勝ち
            }else{
                hand_anim(it,image,Result.Lose)//負け
            }
        }
        if(CP==null) textView.text="Error"
    }

    fun hand_anim(CP:Hand,image:Int,result: Result){
        var handler=Handler()
        var runnable= Runnable {}
        var i=0

        runnable= Runnable {
            i++
            if (i % 3 == 0) {
                ResultImage.setImageResource(R.drawable.paper)
            } else if (i % 3 == 1) {
                ResultImage.setImageResource(R.drawable.rock)
            } else if (i % 3 == 2) {
                ResultImage.setImageResource(R.drawable.scissorce)
            }

            if(i>=20){
                handler.removeCallbacks(runnable)
                textView.text="You ${result}!"
                ResultImage.setImageResource(image)
            }else{
                handler.postDelayed(runnable, 100)
                textView.text="JANKEN..."
            }
        }
        handler.post(runnable)
    }

    fun button_anim(imagebutton: ImageButton) {
            val fadeinAnim = AlphaAnimation(0.0f, 1.0f)
            val fadeoutAnim = AlphaAnimation(1.0f, 0.0f)

            fadeoutAnim.duration = 500
            fadeoutAnim.fillAfter = true
            imagebutton.animation = fadeoutAnim
            fadeinAnim.duration = 500
            fadeinAnim.fillAfter = true
            imagebutton.animation = fadeinAnim
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