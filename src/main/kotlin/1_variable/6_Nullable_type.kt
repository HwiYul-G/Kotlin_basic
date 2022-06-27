package `1_variable`

fun main() {
    // 변수에 유효한 값이 없는 상황을 명시적으로 표시하기 위해, 변수에 null 값을 대입할 수 있음
    // 자바의 경우 Integer num = null; 이런 식으로 대입함
    // null 값을 이용해 유효한 값이 있는 지 여부를 통해 코드를 작성하는 경우가 많음
    //  이를 테면 if(num != null){...} null이 아니면 실행하도록
    // 하지만 이러한 방식의 경우 실수로 null 값 을 확인하는 과정을 생략하거나
    // null 값을 고려하지 않고 코드를 짜서 NullPointerException이 발생하기도 함
    // 따라서, 코틀린에선 기본적으로 null 값의 대입을 허용하지 않음
    // 이른바, non-null type

    var num = 100 // 기본적으로 null 값을 허용하지 않는 타입(Int)로 선언 및 초기화
    // num = null // Error : Null can not be a value of a non-null type Int

    // 하지만 null 값의 대입이 꼭 필요한 상황이라면
    // 타입명? 를 통한 null 허용 타입(nullable type)으로 변수를 정의하면 됨
    var a : Int? = 100 // nullable type
    a = null // null 값 대입 가능

    var s : String = "Hello" // String도 non-null
    // s = null // Error
    println(s.length) // NPE 없이 안전하게 호출 가능

    var s2 : String? = "Hello"
    s2 = null
    // println(s2.length) // Only safe or non-null asserted calls are allowed on a nullable receiver of type String?
    // 이처럼 유효한 값이 없어 오류가 발생할 여지가 있어서
    // 코틀린에서는 null 허용 타입에 접근할 때 null 안전 연산자(null safety operator)를 써서 값에 접근하도록 강제함

    // null 허용 타입 속성에 접근하거나 메서드를 호출하는 방법
    // 1. 조건문에서 null 값 대입 여부 검사 후 접근
    var c : String? = null
    if( c!= null){
        println(c.length)
    }
    // 2. let 함수를 이용한 null값 접근
    // 엄밀하게 하면 if 블록 내부 진입 후 다른 스레드에 의해 값이 null이 될 수 있으므로
    // let 함수를 이용해 null 허용 타입의 값에 접근하는 것이 더 좋은 방법
    // let 함수 : scope function(범위 함수) 중 하나로,
    //          변수값이 null이 아닌 경우 실행할 코드 블록을 작성할 수 있게 도와주는 함수
    c?.let{
        println(it.length)
    }

    // 3. 안전 호출 연산자(Safe-call operator)를 사용해 값에 접근
    // safe-call operator : '?.' 을 붙인 연산자로, 이 연산자를 통해 접근한 변수 값이 null 인 경우
    // 변수 값에 접근하지 않고 곧바로 null 값을 반환함.
    // 따라서 객체의 속성값에 접근하거나 메서드 호출을 시도하지 않아 null 관련 오류 발생 X
    // * safe-call operator로 반환받을 수 있는 값을 저장할 변수타입은 nullable type이여야함.
    var c1 : String? = null
    var len : Int? = c1?.length // c1이 null이면 length 속성에 접근X. 바로 return null
    println(len)

    // 4. 엘비스 연산자(Elvis operator) 이용
    // Elvis operator는 '?:' 형태이고, 왼쪽의 피연산자가 null이 아니면 해당 값 반환
    // null 이면 오른쪽 피연산자를 반환
    var one = null ?: 1
    print(one) // 왼쪽 피연산자 값이 null 이므로 1이 출력
    var two = 2?:1 // 왼쪽 피연산자 값이 null이 아니므로 2
    // 이러한 Elvis 연산자는 null 값 대신 대안으로 사용할 기본값을 반환받을 때 사용
    var c2 : String? = null
    var result = c2?.length ?: 0 // c2 는 null 이라 safe-call 로 length에 접근 X이고 null이 반환되므로 대안 값인 0을 return
    
    // 5. null 값이 아님을 보장하는 연산자를 사용해 값에 접근
    // not-null assertion 연산자 이용
    // 형태는 '!!'으로 null 대입을 허용하지 않는 타입으로 강제 변환을 수행하는 연산자
    // 이 연산자는 값이 절대 null이 아님을 보장할 수 있을 때 사용
    // 만약 변수의 값이 null 인데 이 연산자 사용시 타입 변환 과정에서
    // KotlinNullPointerException (자바의 NPE) 을 발생시킴킴
    var nullableStr : String? = null
    var str2 : String = nullableStr!! // nullableStr이 null 이므로 !! 연산자 이용해 타입 변환 중 Error
    var nullableStr2 : String? = "Hello"
    var str3 : String = nullableStr2!!
    println(str3.length)
    // 변수를 새로 선언하지 않고 축약된 형태로도 사용 가능함
    nullableStr2!!.length

    // 6. Null 타입을 return 하는 타입 변환 메서드
    // to타입이름OrNull 메서드를 사용하면 반환한 값이나 null 값을 반환 받을 수 있음.
    // 이때 null 값은 변환이 실패했을 시에 반환받게 됨.
    var wrongNumberString = "숫자아님"
    var wrongInt1 = wrongNumberString.toInt() // NumberFomatingException 발생
    var wrongInt2 = wrongNumberString.toIntOrNull() // null이 반환 될 예정

}