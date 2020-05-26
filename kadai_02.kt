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
        
        CP?.let{
            if(HandType==it){
                return "You ${Result.draw}! cp is ${it}" //あいこ
            }else if((HandType==Hand.Rock && it==Hand.Scissorce)||(HandType==Hand.Scissorce && it==Hand.Paper)||(HandType==Hand.Paper && it==Hand.Rock)){
                return "You ${Result.win}! cp is ${it}"  //勝ち
            }else{
                return "You ${Result.lose}! cp is ${it}"  //負け
            }
        }
        
        return "Error"
    } 
    
}
fun main(){
       var a=kadai_02()
       println(a.janken(Hand.Paper))
        
}