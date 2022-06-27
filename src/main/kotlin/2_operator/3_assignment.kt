package `2_operator`

fun main() {
    // 대입 연산자 (assignment operator) '='
    var value1 = 100 // 단순 값 대입
    var value2 = (1+4)*(20/5) // 수식의 결과 대입
    var value3 = " H e l l o ! !".trim() // method 호출 결과 대입

    // 복합 대입 연산자 (augmented assignment operator) '+=, -=, *=, /=, %='
    var w1 = 10

    w1 += 20
    println("w1+=20 : $w1")

    w1 -=10
    println("w1-=10 : $w1")

    w1/=2
    println("w1/=2 : $w1")

    w1 %= 3
    println("w1%=3 : $w1")
}