// scope function 166p - 171p

/* 범위함수(scope function)
: 람다 함수(익명 함수)를 인자로 전달받는 함수
일종의 임시 환경 속에서 실행되도록 도와주는 함수
임시환경 : 해당 코드 블록에서 생성된 변수 혹은 상수가 외부에 영향을 주지 않는 단느 것을 의미
즉 일종의 일회용 변수, 상수를 생성할 수 있으며 해당 코드 블록 외부에는 해당 변수, 상수에 접근할 수 없다.

람다함수를 전달받는 함수이므로 범위함수는 고차함수이다.

코틀린에 있는 5가지 범위함수
let, run, with, apply, also
 */

data class Point(var x: Int = 0, var y: Int = 0)

fun main(args: Array<String>) {
    // let 함수는 안전호출연산자와 같이 쓰여 null 허용값을 이용해 진행해야 할 코드 작성에 유용
    // 이때 let 함수를 호출한 객체는 람다함수의 인자로 전달된 it을 통해 접근할 수 있다.
    // 또한 let 함수는 전달한 람다 함수의 맨 마지막 표현식이 평가된 값을 반환값으로 사용함

    // null 값 대입이 허용되는 문자열 타입 객체를 통해 let 함수를 호출
    // let 함수 호출 과정에서 안전 호출 연산자를 사용하므로
    // 해당 변수에 null값이 할당되어 있다면 let 함수에 전달한 람다 함수의 코드 블록이 실행X
    // 또한 람다 함수의 코드 블록 내부에서 it을 통해 let 함수를 호출한 객채(nullableStrig) 값에 접근
    var nullableString: String? = "Hello"
    nullableString?.let {
        println(it.length)
    } // null이 아닌 경우에만 전달한 람다 함수가 실행됨

    //
    var upper = nullableString?.let {
        it.toUpperCase()
    }
    println(upper)


    var p1 = Point(1, 2)
    p1?.let {
        it.x *= 2
        it.y *= 2
    }
    println(p1)

    // with 함수
    // with 함수는 let 함수와 다르게 인자로 객체를 전달하는 방식으로 호출하고,
    // it이 아닌 this 키워드를 통해 객체에 접근하는 점을 제외하곤 let과 동일하게 작동함
    var str = "Hello, KOTLIN"
    var lower = with(str){
        // this 키워드는 생략되어도 괜찮다.
        this.toLowerCase() // 해당 표현식의 반환값이 최종 반환값으로 사용됨(let과 동일)
    }
    println(lower)

    // run 함수는 this 키워드를 통해 객체에 접근한다.
    var pointToPair = p1.run{
        Pair(x,y) // Pair(this.x, this.y)에서 this 생략
    }
    println(pointToPair) // (2,4)가 출력됨

    // 앞에 선언한 객체를 Pair 객체로 변환흔 데 run 함수를 사용한다.
    // run 함수는 확장함수(Extension function)처럼 사용할 수도 있고
    // 아래와 같이 일반 함수처럼 단독으로도 사용할 수도 있고
    // 객체 초기화 작업에 사용할 임시 변수를 활용하고 싶을 때 유용하게 사용된다.
    var p3 = run{
        // 람다 함수 내부에서만 유효한 임시 변수를 선언
        var x = 100
        var y = 200
        Point(x,y)
    }
    println(p3)

    // apply 함수
    // this 키워드를 통해 객체에 접근할 수 있도록 허용하되 반환값으로 객체 자체를 반환한다는 특징이 있는 범위 함수
    // apply 함수는 주로 반환값을 전달 받기 위해 사용하기보다는
    // 객체의 내용을 초기화하거나 설정값을 적용하기 위해 사용
    var p2 = p1.apply{
        x = 100
        y = 200
        // 람다 함수의 마지막 표현식이 결괏값으로 사용되지 않음에 유의 (apply 함수는 객체(p1)을 반환)
        "Hello"
    }
    println(p2)
    // apply 함수를 호출한 결과로 돌려받은 객체는 완전히 참조가 같은 객체임에 유의
    println(p1 == p2) // 둘은 true
    // 여기에서 포인트 객체의 x, y 정보를 변경하기 위해서 pply 함수를 사용합니다.
    // 이전에 살펴본 함수와 달리, 람다 함수의 마지막 표현식이 아닌 객체가 반환된다 **

    // also 함수
    // apply 함수와 같이 반환값으로 '객체 자체를 반환'하지만 it을 통해 객체에 접근한다는 차이가 있다.
    // 단, 일반적으로 also 함수에 전달한 ㄹ마다 함수 내부에서는 객체의 내용을 조회하는 작업만 수행하며
    // 객체에 포함된 값을 변경하거나 조작하는 작업을 하지 않는 것을 권장
    var words = mutableListOf("Hello", "World")
    // also 내부에서는 가능하면 객체를 변경하는 작업을 하지 않는 것을 권장
    words.also{
        // 값을 변경하지 않는 조회 작업과 관련된 메서드만 실행
        println("first item : ${it.first()}")
        println("last item : ${it.last()}")
        println("list size : ${it.size}")
    }.add("Kotlin")
    println(words)
}

/* scope function 정리
: let, with, run, apply, also
        객체참조    함수 반환값      주 용도
let     it         마지막 표현식      객체가 null이 아닐 경우 실행할 코드 블록 작성
with    this       마지막 표현식      임시 환경에서의 객체 조작
run     this       마지막 표현식      임시 환경에서의 객체 조작
apply   this       객체              객체 속성 초기화 및 설정 작업 진행
also    it         객체              객체 값 조회 및 필요한 조회 작업 수행
*
용도는 그저 지침일뿐이고 다른 종류의 범위함수로 특정 범위 함수의 기능을 대체할 수 있어서
꼭 용도가 정해져 있는 것은 이다.

다만, let과 apply 함수의 경우 안드로이드 공식 문서의 API 활용법에서 보여주는 코드에도 자주 등장하므로
사용법을 익혀야한다.
* */