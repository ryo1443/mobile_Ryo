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

        PaperButton.setOnClickListener {
            buttonAnim(PaperButton)
            janken(Hand.Paper)
        }
        RockButton.setOnClickListener {
            buttonAnim(RockButton)
            janken(Hand.Rock)
        }
        ScissorsButton.setOnClickListener {
            buttonAnim(ScissorsButton)
            janken(Hand.Scissors)
        }
    }

    fun janken (HandType: Hand) {
        val cp = (0..2).random()
        var CP: Hand? = null
        var image: Int = R.drawable.paper

        when (cp) {
            0 -> { //0~2となる乱数が0の時の処理
                CP = Hand.Rock
                image = R.drawable.rock
            }
            1 -> { //1の時の処理
                CP = Hand.Scissors
                image = R.drawable.scissorce
            }
            2 -> { //2の時の処理
                CP = Hand.Paper
                image = R.drawable.paper
            }
        }
        CP?.let  {
            if (HandType == it) { //あいこ
                handAnim(it, image, Result.Draw)
            } else if ( (HandType == Hand.Rock && it == Hand.Scissors) ||
                      (HandType == Hand.Scissors && it == Hand.Paper)||
                      (HandType == Hand.Paper && it == Hand.Rock)
            ) { //勝ち
                handAnim(it, image, Result.Win)
            } else { //負け
                handAnim(it, image, Result.Lose)
            }
        }
        if (CP == null) TextView.text = "Error" //nullチェック
    }

    fun handAnim(CP: Hand, image: Int, result: Result){ //相手の手のロール
        var handler = Handler()
        var runnable = Runnable {}
        var i = 0

        PaperButton.isClickable = false
        RockButton.isClickable = false
        ScissorsButton.isClickable = false

        runnable = Runnable { //100ミリ秒ごとに表示させる手の画像を変える
            i++

            when (i % 3) {
                0 -> ResultImage.setImageResource(R.drawable.paper)
                1 -> ResultImage.setImageResource(R.drawable.rock)
                2 -> ResultImage.setImageResource(R.drawable.scissorce)
            }

            if (i >= 20) { //二秒経過で画像のロールの終了
                handler.removeCallbacks(runnable)
                TextView.text = "You ${result}!"
                ResultImage.setImageResource(image)

                PaperButton.isClickable = true
                RockButton.isClickable = true
                ScissorsButton.isClickable = true
            } else {
                handler.postDelayed(runnable, 100)
                TextView.text = "JANKEN..."
            }
        }
        handler.post(runnable)
    }

    fun buttonAnim(imageButton: ImageButton) { //ボタンのフェードインとフェードアウト
        val fadeInAnim = AlphaAnimation(0.0f, 1.0f)
        val fadeOutAnim = AlphaAnimation(1.0f, 0.0f)


        fadeOutAnim.duration = 500
        fadeOutAnim.fillAfter = true
        imageButton.animation = fadeOutAnim
        fadeInAnim.duration = 500
        fadeInAnim.fillAfter = true
        imageButton.animation = fadeInAnim

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