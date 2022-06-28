// reference Operator : 참조연산자 // 161-165

// Kotlin 언어에서는 일반 함수도 람다 함수처럼 변수나 상수에 대입하거나 인자로 전달 가능
fun multiplyTwo(n : Int) = n*2
fun multiplyThree(n : Int) = n*3
fun isEven(n: Int) = (n%2) == 0

// 함수 참조 연산자는 이후 컬렉션 클래스에서 제공하는 다양한 메서드에 전달하는 과정에 자주 사용
fun printReverse(s: String) = println(s.reversed())

// 객체의 메서드, 클래스 함수도 함수 참조 형태(::)로 참조 가능
class StringAppender(init:String){
    var result = init

    fun append(s:String){
        result +=s
    }

    class Inner{
        // companion object 키워드를 이용해 클래스와 내부 클래스에 클래스 함수 정의
        companion object{
            fun myPrintln(any:Any) = println("${any.toString()}")
        }
    }

    companion object{
        fun myPrintln(any:Any) = println("${any.toString()}")
    }
}

fun main() {
    // 정의한 함수를 참조하려면 '함수 참조 연산자 (::)'와 함수명을 지정하면 됨

    // 정의한 함수를 참조할 타입 변수로 선언하고 함수를 대입함
    var myMultiplyFuncRef : (Int) -> Int = :: multiplyTwo
    println(myMultiplyFuncRef(2))

    // 변수의 타입(함수 타입)이 같으므로 multiplyThree 함수를 대입하는 것도 가능
    myMultiplyFuncRef = :: multiplyThree
    println(myMultiplyFuncRef(2))

    //함수 타입을 추론할 수 있으므로 변수타입 지정을 생략하고 함수를 대입
    var isEvenRef = ::isEven
    println(isEvenRef(2))

    // forEach()는 리스트에 담긴 값을 하나씩 / 전달받은 함수의 인자값으로 전달하는 방식으로 작동동
    listOf("Hello","World").forEach(::printReverse)

    // StringAppender 클래스를 사용해보기

    // 객체를 생성하고 메서드를 함수 참조 연산자를 통해 forEach 메서드에 전달함.
    // 이 경우 전달할 메서드의 소속을 밝히기 위해 변수or 상수 이름을 연산자 앞에 지정함함
    val appender = StringAppender("")
    listOf("Hello", "World").forEach(appender::append)
    println(appender.result)

    // companion object 블록 내부에 정의한 클래스 함수도 참조할 수 있다.
    // 단 클래스의 이름은 소괄호로 감싸야 함수 참조가 가능하다.
    var myPrintlnRef = (StringAppender)::myPrintln
    myPrintlnRef("Hello World")

    myPrintlnRef = (StringAppender.Inner)::myPrintln
    myPrintlnRef("Hello World")

}
/* 콘솔 화면에 무언가 출력하기 위해 사용한 println()을 참조하기 위해 변수에 대입하는 코드
var printlnRef : (Any) -> Unit = ::println
이 변수 타입의 추론을 유도하기 위해 다음과 같이 대입을 시도하면 'Overload resolution ambiguity' error 발생
var printlnRef = ::println

println 오버로딩된 정의분
fun println(message: Any?)
fun println(message: Int)
fun println(message: Boolean)
fun println(message: Double)
fun println(message: String)
..

이처럼 인자의 타입이 다양하게 정의돼 있기 때문에
타입을 추론하는 과정에서 잔달받을 인자의 타입을 어떤 타입으로 결정해야 할지 알수 없어 모호함 발생
따라서, 이러한 경우에는 다음과 같이 함수 타입을 직접 명시해야함

Overload resolution ambiguity error 발생
함수 오버로딩의 결과로 여러 타입의 ㄱ밧을 받을 수 있는 다양한 함수가 정의돼 있어서 임의로 타입 추론 불가능
var printlnRef = ::println // 따라서 직접 함수 타입을 명시하는 방식으로 함수 대입
var printlnRef : (Any) -> Unit= ::println
printlnRef("Hello World By Function Reference")
* */