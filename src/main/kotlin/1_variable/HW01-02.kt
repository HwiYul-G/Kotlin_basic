package `1_variable`

fun main() {
    var n1 : Int = 1
    var n2 : Double = 2.5

    // 타입 변환 이후 더하기
    var n3 = n1.toDouble() + n2
    println(n3)

    // String template을 이용해 더하기 작업 수행
    println("${n1.toDouble() + n2}")

}