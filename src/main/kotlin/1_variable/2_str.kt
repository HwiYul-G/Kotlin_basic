package `1_variable`

fun main(args:Array<String>){
    // 이스케이프 문자 (검색으로 추가해서 볼 것)
    var strWithEscapes = "안녕하세요.\n반갑습니다.\t^^"
    println(strWithEscapes)

    //Raw String : 3개의 큰따옴표(""")를 이용해 선언하는 문자열
    // raw string은 문자열 안에서 따로 이스케이프 문자를 입력할 필요 없이 '엔터, 탭' 이 포함된 문자열을 적어 줌
    var raw_str ="""안녕하세요.
반갑습니다.  ^^
    끝!"""
    println(raw_str)

    // 문자열 템플릿(String template) : 문자열 안에 특정 변수(혹은 상수) 값이나 계산한 결괏값을 그대로 삽입할 수 있음.
    // 변수값을 표시하기 위해 달러 표시($)와 출력할 변수값의 이름을 쓰면 됨
    // $와 이어지는 {} 안에 계산식을 쓰면 됨
    var num1 = 100
    var num2 = 200
    val strWithNum = "Hello, $num1" // $을 이용해 num1 변수값 표시
    println(strWithNum)

    var greeting = "\n\t Hello \t\n"
    var trimmed = "trimmed : ${greeting.trim()}" // 문자열 템플릿의 내부에 수식의 결괏값을 삽입
    println(trimmed)

    var sum = "sum : ${num1 + num2}"
    println(sum)
}
