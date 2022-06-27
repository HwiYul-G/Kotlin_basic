package `1_variable`

fun main(args:Array<String>) {
    // 정수 타입 변수 선언 및 출력
    var n1 :Int = 2147483647 // 타입 명시적 선언
    println(n1)

    var n2 = 3.14 // 타입 묵시적 선언(Double)
    println(n2)

    // 문자열 타입 변수 선언 및 출력
    var greetMsg = "Hello"
    println(greetMsg)
}