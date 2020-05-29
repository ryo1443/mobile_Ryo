package kadai_03.com

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.EditText
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        RUN_button.setOnClickListener(){
            val cal = Cal()
            val year:Int?=textbox.getText().toString().toIntOrNull()

            textView.text=cal.isLeap(year)
        }


    }

}


class Cal{

    fun isLeap(year:Int?):String{

        if(year == null){
            return "please enter year"
            //nullのとき
        }

        if(year % 4 == 0){
            if(year % 400==0) return "$year is leapyear!"

            if(year % 100 ==0) return "$year is not leapyear!"

            return "$year is leapyear!"


        }else{
            return "$year is not leapyear!"
        }
        //うるう年判定
    }
}
