package `1_variable`

fun main(args:Array<String>){
    // Any 타입 : 모든 탕비의 값을 대입할 수 있는 최상위 타입
    // 한번 타입이 정해진 변수에는 다른 타입의 값을 대입할 수 없지만
    // Any 타입으로 지정한 변수에는 이후에도 어떤 종류의 값이든 대입이 가능함.
    // Any 타입을 더 잘 이해하려면 클래스 상속 개념을 이해해야함.

    var anyValue : Any

    // 숫자(정수형) 대입
    anyValue = 100
    println(anyValue::class.simpleName) // Int

    // 문자열 대입
    anyValue = "Hello"
    println(anyValue::class.simpleName) // String

    // 숫자(실수형) 대입
    anyValue = 1.234 // f 없으므로 double 형
    println(anyValue::class.simpleName) // Double

    // Any 타입 배열 (모든 종류의 값을 저장할 수 있음)
    var anyTypeArr : Array<Any> = arrayOf(1,1.234,"Hello")

    // Any 타입은 어떤 값이든 대입가능하며 타입을 출력할 때마다 다른 타입이 출력됨
    // 이러한 Any 타입은 보통 "타입에 무관히 모든 값을 함수나 메서드 인자로 전달받을 때 사용"
    // Kotlin의 Any 타입은 자바의 Object 타입과 같은 역할을 하고
    // 디컴파일된 바이트코드를 살펴보면 자바의 Object 타입 값으로 변환되는 것을 확인할 수 있다.

}