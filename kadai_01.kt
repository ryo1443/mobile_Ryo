class kadai_01(){
    
    fun isLeap(year:Int):String{
        
        if(year % 4 == 0){
            if(year % 400==0) return "True"
            
            if(year % 100 ==0) return "False"
            
            return "True"
            
        }else{
            return "False"
        }
   }
}

fun main(){
    var a=kadai_01()
   
    println(a.isLeap(2000))
    println(a.isLeap(1209))
    println(a.isLeap(1980))
    println(a.isLeap(1790))
    println(a.isLeap(1993))
}