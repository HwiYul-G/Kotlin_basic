package `1_variable`

fun main() {
    val weekdayNames = arrayOf("월요일", "화요일", "수요일", "목요일", "금요일", "토요일", "일요일")

    for(i in weekdayNames.indices){
        println(weekdayNames[i])
    }
}