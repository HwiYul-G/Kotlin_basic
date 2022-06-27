package `1_variable`

fun main(args : Array<String>){
    // 정수 타입 - Byte(1byte=8bit), Short(2B), Int(4B), Long(8B)
    // 실수 타입 - Float(4B), Double(8B)
    var n1 : Int =10
    var n2 = 3
    var n3 = 2.14f // f혹은 F를 넣어야함

    print("n1 : "+n1+", n2 : "+n2+", n3 :"+n3)

    // val은 '상수'
    val PI = 3.14 // 기본으로 double형
    val CONSTANT_CHAR = 'C' // constant는 이런 방식으로 표기
    print("PI : "+PI+", CONSTNAT_CHAR : "+CONSTANT_CHAR)

    // 문자 타입 - Char(2B, 0-65535 양수값)
    var c : Char = 'C'
    var ga : Char = '가'
    println("c :"+ c)
    println("c.toInt() : "+c.toInt()) // toInt()로 각 문자에 대응하는 숫자로 변경
    println("ga.toInt() : "+ga.toInt())

    // 문자열 타입 - String
    var str1 : String = "Hello"
    var str2 : String = "Kotlin"
    println(str1+str2) // 띄어쓰기 없이 붙어짐

    // 논리값 = 불리언 타입입
    var b1 = true
    var b2 = false
    println(b1)
    println(b2)
}