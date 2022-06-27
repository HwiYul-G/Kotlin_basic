package `2_operator`

fun main() {
    // 단항 연산자 (unary operator) : 피연산자가 하나인 연산자
    // 증가연산자 ++, 감소연산자 -- , - 값의 부호 변경
    var value1 = 100
    value1++
    println("after value1++ : "+value1)

    value1--
    println("after value-- : " + value1)

    println("-value : "+(-value1))
    println("-(-value) : " + (-(-value1)))

    // 비교 연산자 : 결과값으로 논리값(true, false)를 반환함
    // == 같은지 비교, != 다른지 비교, === 참조가 같은지 비교, !== 참조가 다른지 비교교
    // >, >=, <, <=
    var r1 = (1 == 1)
    var r2 = (1 != 2)
    // 문자열의 값이 서로 일치하는지 여부를 판단하고 있으므로 true 반환
    var r3 = ("Hello" == "Hello")
    var r4 = ("Hello".length == "World".length)
    var r5 = 2 > 1
    var r6 = 2 >= 2

    println("(1==1) : $r1")
    println("(1!=2) : $r2")
    println("""Hello" == "Hello" : $r3""")
    println(""" "Hello".length == "World".length  : $r4""")
    println("(2 > 1) : $r5")
    println("(2 >= 2) : $r6")

    // 참조 비교 연산자 ===과 !==은 두 객체가 같은 메모리 주소를 점유하고 있는 지 여부로 true, false를 반환함
    // 따라서 클래스를 공부할 때 볼 것!

    // 논리 연산자 (logical operator) : 논리값(true, false)을 이용해 연산을 수행하는 연산자
    // 비교 연산자와 함께 쓰이며 조건문의 조건식에 포함되어 특정 코드 블록을 수행할지 여불르 판단하는 데 사용
    // && : AND 연산자, || OR 연산자 , ! 부정 연산자
    var r7 = true && true
    var r8 = true && false
    var r9 = false || false
    var r10 = false || true
    var r11 = !true
    var r12 = !false
    println("true && ture : $r7")
    println("true && false : $r8")
    println("false || false : $r9")
    println("false || ture : $r10")
    println("!ture : $r11")
    println("!false : $r12")

    var result = ((2 > 1) && ("Hello"!="World"))
    println("$result")
}