enum class Result{
 	win,
    lose,
    draw
}
enum class Hand{
    Rock,
    Scissorce,
    Paper
}
class kadai_02(){
    fun janken(HandType:Hand):String{
        val cp =(0..2).random()
        var CP:Hand?=null
        
        when(cp){
            0->{CP=Hand.Rock}
            1->{CP=Hand.Scissorce}
            2->{CP=Hand.Paper}
        }
        
        if(HandType==CP){
            return "You ${Result.draw}! cp is ${CP}" //あいこ
        }else if((HandType==Hand.Rock && CP==Hand.Scissorce)||(HandType==Hand.Scissorce && CP==Hand.Paper)||(HandType==Hand.Paper && CP==Hand.Rock)){
            return "You ${Result.win}! cp is ${CP}"  //勝ち
        }else{
            return "You ${Result.lose}! cp is ${CP}"  //負け
        }
    }
    
}
    fun main(){
       var a=kadai_02()
       println(a.janken(Hand.Paper))
        
    }