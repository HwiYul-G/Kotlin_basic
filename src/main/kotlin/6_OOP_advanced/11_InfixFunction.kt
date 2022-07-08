//314 - 317
/*
    중위 표기법(infix notation)
    : 연산자를 두 피연산자 사이에 위치하도록 표기하는 방법
    1 + 2 처럼 우리에게 익숙한 표기법

    코틀린에선 infix 키워드를 지정해서 정의한 메서를 중위 표기법 방식으로 호출할 수 있도록 허용 가능.
    단 중위 표기법으로 호출할 수 잇는 메서드나 확장함수를 정의할 때 제약조건이 있다.
    - 클래스 내부에 정의된 메서드 혹은 확장 함수(Extension function)만 중위 표기법을 적용해 호출할 수 있다.
    - 메서드(확장 함수)는 오직 하나의 인자값만 전달받을 수 있다.
    - 매개변수의 기본값을 지정할 수 없다.
*/

class InfixDemoPerson(var name : String){
    // 중위 표기법을 지원
    // 클래스 내부의 메소드 + 인자값 하나 + 인자의 기본값X
    infix fun sayHelloTo(target : InfixDemoPerson){
        println("${this.name} say hello to ${target.name}")
    }
}
class InfixDemoNumber(var num : Int){
    infix fun add(target: InfixDemoNumber) : InfixDemoNumber{
        return InfixDemoNumber(num + target.num)
    }
    infix fun add(target: Int) : Int{
        return num + target
    }
}
infix fun String.sayHelloTo(to : String){
    println("${this} say hello to ${to}")
}

fun main(args : Array<String>) {

    var p1 = InfixDemoPerson("김철수")
    var p2 = InfixDemoPerson("이영희")

    // 중위 표기법을 통해 클래스 내부의 함수인 메서드와 확장 함수 호출
    p1 sayHelloTo p2

    var n1 = InfixDemoNumber(100)
    var n2 = InfixDemoNumber(200)

    // 중위 표기법을 통해 클래스 내부의 함수인 메서드와 확장 함수 호출
    var sum1 = n1 add n2
    println(sum1)
    var sum2 = n1 add 10
    println(sum2)

    // 중위 표기법을 통해 클래스 내부의 함수인 메서드와 확장 함수 호출
    "김철수" sayHelloTo "이영희"

    // infix 키워드를 이용해서 정의했어도 기존 호출 방식으로 호출 가능
    p1.sayHelloTo(p2)
    n1.add(n2)
    n1.add(10)
    "김철수".sayHelloTo("이영희")

    // 코틀린에서 기본적으로 제공하는 함수 중 중위 표기법 호출을 지원하는 함수가 종종 있음
    // 범위 객체를 생성하는데 사용되는 함수(until, downTo, step) 등
    // public infix fun Int.until(to : Int) : IntRange{}
    // public infix fun Int.downTo(to : Int) : IntProgression{}
    // public infix fun IntProgression.step(step : Int) : IntProgression{}
    // public infix fun <A, B> A.to(that: B) : Pair<A,B> = Pair(this, that)
    // var r1 = 1 until 10
    // var r2 = 10 downTo 1
    // var r3 = range1 step 2
    // var pair = 1 to "Hello"

    var range1 = 1 until 10
    var range2 = 10 downTo 1
    var range3 = range1 step 2
    var pair = 1 to "Hello"
}