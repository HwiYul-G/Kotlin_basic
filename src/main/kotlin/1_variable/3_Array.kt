package `1_variable`

fun main(args:Array<String>){
    // 배열(array) : 같은 타입의 값을 여러개 저장하고 관리하기 위함
    // var 배열이름 : Array<배열에 포함될 값의 타입> = Array<값 타입>(배열크기){초깃값}
    // 배열에 담을 값의 타입은 초깃값을 통해 추론 가능하므로 생략가능

    // Int 타입의 값 3개를 저장할 수 있는 초기값이 0인 정수 배열 선언
    var arr1 : Array<Int> = Array<Int>(3){0}
    // 배열의 인덱스(값의 위치)는 0부터 시작함함

    // arr1처럼 전부 쓰지 않고 아래처럼 단순화해서 적을 수 있음
    var arr2 = Array(3){0}

    // 배열 초깃값을 100으로 하는 3개의 정수를 저장할 수 있는 배열 선언
    var arrInitHundred = Array(3){100}
    println(arrInitHundred[0])
    println(arrInitHundred[1])
    arrInitHundred[2] = 200
    println(arrInitHundred[2])

    // Int 값만 가능한 것은 아님
    var stringArr = Array(2){"Hello"}
    stringArr[1]="World"
    println(stringArr[0])
    println(stringArr[1])

    // arrayOf()를 이용해 배열에 저장할 초깃값을 미리 지정하는 형태로 배열을 선언할 수 있다.
    // Kotlin에서 제공하는 arrayOf()는 자바의 프리미티브 타입의 wrapper 클래스(Integer, Character)
    // wrapper 클래스의 객체를 저장할 수 있는 배열을 반환함.
    // 그러므로 자바로 작성된 메서드에서 프리미티브 타입 배열을 인자값으로 요구할 경우
    // arrayOf 함수로 생성한 배열을 전달할 수 없음.
    var arr3 = arrayOf(100,200,300)

    // 아래의 함수들을 이용해 원시 타입값을 저장하는 배열을 생성할 수 있음
    var intArr = intArrayOf(1,2,3)
    // 자바 언어에 대응하는 타입을 확인하기 위해 canonicalName 속성에 접근
    println(intArr.javaClass.canonicalName) // 실행 결과 : int[]
    var charArr = charArrayOf('a','b','c')
    println(charArr.javaClass.canonicalName) // char[]
    // 위의 intArrayOf, charArrayOf 같은 경우
    // 프리미티브 타입 배열 생성함수로 생성한 배열의 타입은 원시 타입 배열 int[], char[] 임을 확인할 수 있음

    // 이미 생성한 wrapper 클래스 타입의 배열이 있는 경우
    // 아래의 메소드를 이용해서 프리미티브 타입의 배열로 변환할 수 있음.
    // 1. wrapper type array create
    var intWrapperArr = arrayOf(1,2,3)
    var charWrapperArr = arrayOf('a','b','c')

    // 2. 변환할 배열에 저장할 값의 타입이 Int이므로 toIntArray를 호출해서
    // 원시 타입 객체를 저장하는 배열로 변환
    var intPrimitiveArr = intWrapperArr.toIntArray()
    // 2. 변환할 배열의 저장할 자료형이 Char 이므로 toCharArray를 호출해서
    // 원시 타입 객체를 저장하는 배열로 변환
    var charPrimitiveArr = charWrapperArr.toCharArray()
}