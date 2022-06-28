// == 기본 인자값 정의 ==
fun sumWithDefault(a: Int = 10, b: Int = 20): Int {
    return a + b
}

fun sumWithDefault2(a: Int, b: Int = 20): Int = a + b

// == 가변 인자 전달 ==
// vararg 키워드 : 가변적인 개수의 인자
// 가변 인자는 리스트 객체를 생성하는 listOf()나 집합 객체를 생성하는 setOf()에서 유용함
// 리스트나 집합 객체를 만들 때 몇 개의 값을 저장해야 할지 미리 알 수 없기 때문
fun sumWithVargs(vararg nums: Int): Int {
    var total = 0
    for (num in nums)
        total += num
    return total
}
// 실제로 listOf의 함수 선언부를 보면 인자값 이름 앞에 varage 키워드가 지정됨
// public fun <T> listOf(varage elements : T) : List<T>

// 일반 인자와 가변인자(vararg)를 섞을 수도 있음
fun sumWithVarageWithBase(base:Int, vararg nums : Int) : Int{
    var total = base
    for(num in nums)
        total += num
    return total
}

// 전개 연산자(Spread) : '*'
// 가변 인자를 전달받는 함수에 배열에 포함된 모든 값을 인자값으로 전달하고 싶을 때 사용

// spread operator and 객체 타입 배열
// arrayOf()을 이용해 배열 생성시 기본적으로 객체 타입(Int) 배열을 생성
// 그런데, 전개 연산자는 객체 타입의 배열이 아닌 프리미티브 타입의 배열을 피연산자로 활용함
// 따라서 객체 배열에 직접 전개 연산자 사용시 Type mismatch 에러 발생

// - 아래는 type mismatch 에러 발생 -
// val objArr = arrayOf(1,2,3)
// var sum3 = sumWithVargs(*objArr)

// 따라서 to타입이름Array() 를 사용해 프리미티브 타입 배열로 변경한 후 전개 연산자를 적용해야 함
// var sum3 = sumWithVargs(*(objArr.toIntArray())
// 반면 arrayOf()가 아니라 intArrayOf() 처럼 프리미티브타입 배열을 생성하는 함수를 통해 생성한 배열은
// 곧바로 전개 연산자를 적용할 수 있다.

// intArrayOf()는 프리미티브 타입 배열 생성하므로 바로 spread 연산자 사용가능
// val primArr = intArrayOf(1,2,3)
// var sum4 = sumWithVargs(*primArr)

// 단 이러한 제약 사항은 자바에서 사용되는 프리미티브 타입과 대응하는 타입인
// Byte, Char, Short, Int, Long, Float, Double, Boolean
// 이러한 것의 가변 인자를 받아야하는 경우에만 적용됨
// 전달받은 모든 문자열을 이어붙이는 아래와 같은 함수는 문자열 타입의 가변 인자값을 전달받음
fun concatAllString(vararg strs:String) : String{
    var result = ""
    for(s in strs)
        result += s
    return result
}

/* == 이름을 통한 인자값 전달 ==
함수에 전달해야하는 인자값의 개수가 많다면, 인자값의 순서와 타입을 기억해야하는 불편함이 있음
이를 해결하고자 함수를 호출할 때 매개변수 이름과 인자값을 동시에 전달하는 형태로 함수 호출 가능
이른바 '명명인자 (Named Arguments)'
*/
fun sayHelloTo(from :String, to : String, times: Int){
    var i = 0
    while(i<times){
        println("from : $from")
        println("Hello, $to")
        i++
    }
}

fun main(args: Array<String>) {
    // == 기본 인자값 ==
    println(sumWithDefault())
    println(sumWithDefault(1, 2))
    println(sumWithDefault(3))
    println(sumWithDefault(b = 10))

    println(sumWithDefault2(1))
    println(sumWithDefault2(1,2))

    // == 가변 인자값 ==
    println(sumWithVargs(1, 2, 3, 4)) // 몇개가 들어가든 괜찮다.

    // == 일반 인자 + 가변인자 ==
    println(sumWithVarageWithBase(100, 1,2,3))

    // = 전개 연산자 ==
    // 전개 연산자(Spread) : '*'
    // 가변 인자를 전달받는 함수에 배열에 포함된 모든 값을 인자값으로 전달하고 싶을 때 사용
    val arr = intArrayOf(1,2,3)
    var sum3 = sumWithVargs(*arr) // arr의 모든 값을 인자값으로 전달
    println(sum3)

    // concatAllString function의 경우 원시타입이 아닌 객체 타입의 값을 전달받으므로
    // arrayOf 함수를 이용해 생성한 배열에 전개 연산자를 바로 적용 가능
    concatAllString(*(arrayOf("Hello", "Kotlin","World")))

    // 매개변수의 이름을 이용해 인자값을 전달
    sayHelloTo(from = "김철수", to="이영희", times = 3)
    sayHelloTo(times=3,to="이영희",from="김철수")
    sayHelloTo("김철수", times = 3, to="이영희")
    //위처럼 하면 매개변수의 전달 순서를 바꿔도 함수 호출에 지장을 주지 않는다.
    // 단 sayHelloTo(times = 3, "김철수", "이영희")
    // 위와 같은 경우 from, to 매개변수의 인자값으로 어떤 문자열을 전달해야할지
    // 모호하기 때문에 함수를 호출할 수 없다.
    // 따라서 sayHelloTo("김철수", times=3, to = "이영희)
    // 이런 방식으로 일부 인자값(from)은 함수에 정의된 순서대로 하고
    // 그 뒤에 정의된 인자값을 전달할 때만 매개변수 이름과 함께 지정하는 식으로 호출해야함
}