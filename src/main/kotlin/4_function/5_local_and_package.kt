package `4_function`

// 153 - 159p
// 로컬 함수(local function) : 코틀린은 함수 내부에 함수를 정의할 수 있다.
// 로컬 함수는 복잡한 작업을 수행하는 함수의 기능을 분할한 후
// 여러 함수로 기능을 나누어 구현할 때 사용
// 단, 함수 내용에 정의한 로컬 함수는 외부에서는 사용할 수 없습니다.
fun outerFunc(target: String) : String{
    fun localFunc(str : String) : String{
        return "Hello from local $str"
    }
    return localFunc(target)
}

// 패키지
// 패키지를 이용하면 서로 관련 있는 여러 함수와 클래스를 모아놓고 관리함
// 패키지를 만들면 패키지의 이름과 동일한 폴더가 생성됨
// 그리고 그 패키지 내에 하위 패키지를 넣을 수 있다.

/* package mypackage
    val MY_CONSTANT = 1234
    fun sayHello(to: String) = println("Hello! ${to}")

    class MyClass{
        fun print(){ println("print") }
    }

위에서 정의된 상수, 함수, 클래스는 다른 곳에서 불러와 자유롭게 사용할 수 있다.
외부에서 이를 불러올 때는 import 구문을 사용한다.
import mypackage.MY_CONSTANT
import mypackage.sayHello
import mypacakge.MyClass
import mypackage.* // 패키지에 포함된 모든 것을 불러옴

as 키워드를 사용해 불러올 상수, 함수, 클래스의 이름에 별명(별칭)을 부여할 수 있음
import mypackage.MY_CONSTANT as M_C
이런 식으로 하면 M_C 자체로 상수를 표현할 수 있음.
그리고 기존의 본인 이름 (MY_CONSTANT)으로는 접근할 수 없음

이러한 별칭은 같은 이름의 상수, 함수, 클래스가 정의된 상황에서 발생할 수 있는 이름충동ㄹ
피하기 위해서 사용함
import mypackage.MY_CONSTANT as M_C
fun main(){
    val MY_CONSTANT = 200
    println(MY_CONSTANT) // 200
    println(M_C) // 1234
}
* */

fun main(args : Array<String>){
    // local function 사용
    var result = outerFunc("Outer")
    println(result)
}