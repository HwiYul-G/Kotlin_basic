package `2_operator`

fun main() {
    var height = 180
    var weight = 80
    var BMI = weight/height.toDouble()
    
    // 혹은 height 자체를 180.0 이런식으로 해도 됨
    println(BMI)
}