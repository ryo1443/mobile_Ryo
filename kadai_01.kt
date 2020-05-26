class kadai_01(){
    
    fun isLeap(year:Int):Boolean{
        
        if(year % 4 == 0){
            if(year % 400==0) return true
            
            if(year % 100 ==0) return false
            
            return true
            
        }else{
            return false
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