package `2_operator`

fun main(){
    var intVal1 : Int = 2147483647
    println("before overflow : $intVal1")

    intVal1+=1
    println("after overflow : $intVal1") // -2147483648

    var intVal2 : Int = -2147483648
    println("before underflow : $intVal2")

    intVal2-=1
    println("after underflow : $intVal2") //2147483648

}