package `4_function`

fun sum(a: Int, b: Int): Int {
    // a = 100 은 불가. 즉 인자값으로 전달받은 값은 변경 불가.
    // 값을 변경할경우 Val cannot be reassigned 오류 발생

    // 전달된 인자 값을 변경하고 싶다면, 함수 내부에 새로운 값을 받을 변수나 상수
    // 선언 후 변경된 값을 대입하는 방식으로 처리해야함.
    var c = a + 1
    return a + b
}

fun sum2(a: Int, b: Int): Int {
    var c = a * 2
    var d = b * 2
    return c + d
}

// 함수에 명시적인 반환값이 없다면 반환값의 타입은 Unit 타입
// Unit 타입은 생략가능
fun printHell(): Unit {
    println("Hello")
}

// 함수 축약 정의
// fun 함수이름(매개변수 정보) : 반환값 = 표현식
fun simpleSum(a: Int, b: Int): Int = a + b
fun getBigger(a: Int, b: Int) = if (a > b) a else b
fun getGrade(score : Int) = when(score){
    in 91..100 ->"A"
    in 81..90->"B"
    in 71..80->"C"
    else->"D"
}

fun main() {
    var result1 = sum(1, 2)
    println(result1)
    println(sum2(1, 2))
}