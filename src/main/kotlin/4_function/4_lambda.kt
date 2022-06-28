package `4_function`

// 고차 함수(higher-order function)
// 아래의 3가지 특징 중 하나 이상을 충족하는 함수
// 1) 함수의 인자값으로 함수를 전달받는 함수
// 2) 함수의 반환값으로 함수를 반환하는 함수
// 3) 1,2의 특성을 모두 가진 함수(인자값으로 함수를 받고, 반환도 함수로)
// 코틀린은 고차함수를 지원한다.
// 이렇게 함수에 함수를 전달하거나 함수를 반환하거나 변수나 상수에 함수를 저장할 때
// 주로 사용하는 함수가 "람다함수(lambda function)"이다.

// lambda function define and create
// var(또는 val) 함수를 담연 변수나 상수 이름 : 함수반환타입
// = {인자이름:타입,... , 인자이름n:타입:n -> 코드}
val square: (Int) -> Int = { number: Int -> number * number }
// 1) val : 람다 함수를 상수에 대입하고 다른 람다 함수를 대입하지 않을 생각이므로 val 키워드로 상수로 지정
// 추후 값을 변경하고 싶다면 var 키워드를 사용해도 됨
// 2) square : 선언한 상수(val)의 이름이자 동시에 함수의 이름임.
// 3) (Int)-> Int : square 상수의 타입을 지정하는 부분
// 람다함수를 저장해야할 변수나 상수의 경우 특별히 '함수 타입 형식'으로 타입을 지정
// * 함수 타입을 지정하는 형식
// (인자값 타입1, 인자값 타입2, ..., 인자값 타입n) -> 반환값 타입
// () 안에는 함수로 전달받을 인자값의 타입을 순서대로 쓰고
// -> 다음에 반환값 타입을 씀
// 4) = {number : Int -> number * number }
// 대입문을 통해 함수의 내용을 작성함.
// -> 기준으로 왼쪽에는 전달받은 매개변수의 이름:타입을 쓰고 오른쪽엔 함수의 내용 작성
// 람다 함수는 return 키워드를 이용해 값을 반환하지 않고
// 마지막에 평가된 표현식의 결괏값을 반환값으로 사용함

// 람다함수는 반환값이 없어도 Unit 생략 불가능
var printHello: () -> Unit = { println("Hello") }
// var로 선언되어 변수이므로 이후에 함수 타입이 같은 다른 람다함수를 대입할 수 있다.

val sum: (Int, Int) -> Int = { x: Int, y: Int -> x + y }

// it을 이용한 람다 함수 내부에서의 인자값 접근
// 람다 함수에서 받는 *인자값의 개수가 하나*라면 특별히 인자값의 이름을 저장할 필요가 없음
// 대신 it 이라는 대명사를 통해 전달받은 인자값에 접근
val sayHelloTo: (String) -> Unit = { println("say hello to $it") }
// val sayHelloTo : (String) -> Unit = {name -> println("say hello to $name")}

// 함수 타입 추론
// 함수의 모양새와 함수 내용의 마지막 표현식을 보고 인자값의 개수와 타입, 반환값 타입을 모두 추론 가능해서 생략 가능함
// val square = {number : Int -> number*number}
// var printHello = {println("Hello")}
// val sum = {x : Int, y: Int -> x + y}
// val sayHelloTo = {target: String -> println("say Hello to $target)}
// 함수타입을 추론하도록 람다함수를 대입해서 it 값의 ㅏ입을 추론할 근거가 없어졌으므로
// 접근할 인자값의 이름과 타입을 직접 지정.

// == 함수의 반환값으로 람다 함수를 반환 ==
fun returnMultiplyFunc(multiplier : Int) : (Int)->Int{
    // 람다 함수를 반환
    // 인자값 x의 타입을 지정하지 않아도, 위의 반환 타입을 확인하고 인자값 타입 추론 가능
    return {x -> x * multiplier}
}

// 함수 인자로 전달받은 initial 값은 상수이므로 값 변경 불가
fun returnStringPrintFunc(str : String, initial : Int): () -> String{
    var n = initial // 따라서 값을 대입하고 그 안에서 변숫값을 증가 시킴
    return{
        var result = ""
        for( i in 1.. n)
            result+="${str}"
        n++

        // 마지막 표현식이 return값으로 사용됨
        result
    }
}

// == 함수의 인자로 람다 함수를 전달 ==
// 람다 함수는 함수의 인자로 함수를 전달할 때 특히 유용
fun calculate(calcFunc:(Int, Int) -> Int, x:Int, y:Int) : Int{
    // calcFunc는 Int값 2개를 받아서 정수를 반환하는 함수 (파라미터 x, y)
    // calcuate는 calcFunc을 파라미터로 받아서 Int를 반환함 (반환값은 마지막 : Int를 보면 됨)
    return calcFunc(x, y)
}

// == 여러 개의 람다 함수를 전달받는 함수 ==
fun multipleLambdaFunc(lambda1 : () -> Unit,
                        lambda2: (Int) -> Unit,
                        lambda3 : (String, String) -> String){
    lambda1()
    lambda2(100)
    println(lambda3("Hello", "World"))
}

// 반환된 람다 함수에서 바깥에 존재하는 변수(str, counter)에 접근해서 값을 변경하는 예제
fun makeCounter():()->Int{
    var counter = 0
    return{
        counter++
        counter
    }
}
// 전달 받은 문자열을 계속 이어 붙이고 그 결과로 만들어진 문자열을 반환하는 람다 함수
fun makeAppender(init:String=""):(String)-> String{
    var str = init
    return{
        str = str + it
        str
    }
}

fun main() {
    // 람다함수 호출
    println(square(4))
    printHello()
    // * 변수이므로 "함수 타입이 같은" 다른 람다함수 대입 가능
    // printHello 변수는 전달받는 인자값이 없고 반환값도 없는 함수를 저장할 수 있는 변수
    printHello = { println("Bye") }
    printHello()

    // 람다함수의 이름
    // 람다함수는 익명 함수(Anonymous function)라고도 불리고 말그대로 이름이 없는 함수를 의미함
    // 지금까지 예제를 통해 이름이 없는 함수를 하나 선언 후, 해당 함수를 특정 변수나 상수에 대입했다고 표현해도 틀린 표현이 아님
    // 람다함수는 이름이 없는 함수이므로 호출할 때 변수나 함수 이름을 통해 호출됨
    // 따라서 람다함수가 대입된 변수나 상수의 이름이 함수의 이름이라 해석해도 무방함.

    val multiplyFunc1 = returnMultiplyFunc(3)
    println(multiplyFunc1(2)) // 2*3 = 6
    println(multiplyFunc1(3)) // 3 * 3 = 9
    val multiplyFunc2 = returnMultiplyFunc(10)
    println(multiplyFunc2(2)) // 10*2 = 20

    val countFunc1 = returnStringPrintFunc("Hello", 1)
    println(countFunc1()) // Hello 1번 출력
    println(countFunc1()) // Hello 2번 출력

    val countFunc2 = returnStringPrintFunc("Bye", 3)
    println(countFunc2()) // Bye 3번 출력
    println(countFunc2()) // Bye 4번 출력

    val calcResult1 = calculate({x,y -> x + y},2,3)
    val calcResult2 = calculate({x,y->x*y},2,3)
    println(calcResult1)
    println(calcResult2)

    // 3개의 람다 함수를 전달받는 함수에 람다함수를 전달
    multipleLambdaFunc(
        {println("from lambda 1")},
        {println("from lambda 2 $it")},
        {str1, str2 -> "$str1 $str2"}
    )

    val counter = makeCounter()
    println(counter())
    println(counter())

    val appender = makeAppender()
    println(appender("Hello"))
    println(appender(" World"))
}

/* == 자바의 람다 함수 지원 ==
자바8 이전에는 언어 차원에서 람다 함수를 지원하지 않았다.
대신 익명 클래스의 객체를 생성하고, 메서드를 바로 정의하는 방식으로 람다 함수와 비슷한 기능을 흉내 냄
이 방법의 경우 익명 클래스 객체 밖의 변수를 참조할 수 없다는 제약이 있었다.

자바 8 이후 언어 차원에서 람다함수를 지원할 수 있게 되었다.
변수의 값을 변경할 수 없기에 상수만 참조할 수 있다는 제약은 바뀌지 않았다.

But, 코틀린에서는 기본적으로 람다 함수를 제공하며 바깥 영역에 있는 변수의 값을 참조하고 변경하는 작업도 허용됨
*/