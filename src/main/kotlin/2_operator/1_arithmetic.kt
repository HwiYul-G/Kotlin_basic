package `2_operator`

fun main(args : Array<String>) {
    // 산술 연산자 (mathematical operator)
    var sum = 3 + 8
    var sub = 4 - 6
    var mul = 3 * 5
    println("sum : $sum , sub : $sub, mul : $mul")

    // 나누기 연산자(/)는 피연산자가 모두 정수 타입이면 반환되는 값도 정수 타입이라
    // 소수점 값이 사라진다는 점에 유의해야한다.
    // 이 문제를 해결하기 위해선 두 피연산자 중 하나의 피 연산자를 실수로 캐스팅 후 연산하면 된다.
    var div = 6/5 // 두 연산자가 모두 정수이므로 나머지가 삭제될 것
    var divDouble1 = 6.0 / 5.0
    var divDouble2 = 6/ 5.toDouble()
    println("div : $div, divDouble : $divDouble1, divDouble2 : $divDouble2")

    // 나머지 연산자 (%)
    var mod1 = 6%5 // 6나누기5 나머지가 1이므로 1을 반환
    var mod2 = 3%3 // 0을 반환할 예정
    println("mod1 : $mod1 , mod2 : $mod2")

    // 어떤 수식을 먼저 계산해야 할지 명시적으로 우선순위를 정하기 위해 소괄호를 적절히 이용할 수 있다.
    var complex = ((5+3) * (4/2)) % 3
    println("complex : $complex")

    // 변수도 사용 가능
    var v1 = 1
    var v2 = 2
    var result = (v1 + v2) + 10
    println("result : $result")
}