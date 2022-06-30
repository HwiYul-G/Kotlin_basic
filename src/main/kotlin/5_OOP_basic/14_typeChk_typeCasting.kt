// 타입 확인과 타입변환
// 상속을 통해 필요한 속성과 메서드를 물려받을 수 있다는 사실 외에도
// 상속 과정을 통해 "자식 클래스는 부모 클래스로 변환 가능"한 자격을 부여 받는다.
// 즉 상위 타입으로 자유롭게 변환 가능하다.

// is, !is 키워드로 타입 확인

open class A{
    var a = 0
    fun aMethod() = println("A Method")
}
class B : A(){
    var b = 10
    fun bMethod() = println("B Method")
}

class C : A(){
    var c = 20
    fun cMethod() = println("C Method")
}

/* == as 키워드를 이용한 타입 변환 ==
타입 변환(캐스팅) :  특정 값의 타입을 다른 타입으로 변환하는 작업
var 변수이름 = 변환할변수이름 as 변환할타입이름
// A 타입의 객체를 Any 타입으로 반환할 때,
var any:Any = a as Any // A타입 객체인 a를 Any 타입으로 바꿔서 any에 넣음
모든 클래스는 자동으로 Any 클래스를 상속받으므로 어떤 타입이든 Any 타입으로 변환할 수 있다.
*/

fun main(){
    var a = A()
    var b = B()
    var c = C()

    // is 키워드 : 왼쪽엔 타입을 확인하고 싶은 변수나 상수, 오른족은 특정 클래스나 인터페이스 이름
    // 따라서 왼쪽 변수가 해당 클래스나 인터페이스를 상속하거나 구현하는 지 검사할 수 잇음
    // !is 는 아닌 지를 검사하는 것
    // 이는 자바에서 제공하는 instanceOf 연산자와 동일함
    println("a is A : "+(a is A)) // ture
    println("a is B : "+(a is B)) // B 클래스가 A를 상속받았지만 B는 아니므로 false
    println("a is C : "+(a is C)) // false

    println("b is A : "+(b is A)) // B는 A를 상속받아 B이면서 동시에 A이므로 true
    println("b is B : "+(b is B)) // true

    println("c is A : "+(c is A)) // C는 A를 상속받아 C이면서 동시에 A이므로 ture
    println("c is C : "+(c is C)) // true

    // Any는 모든 클래스에서 상속받는 클래스이므로 결과는 모두 true
    println("a is Any : "+(a is Any))
    println("b is Any : "+(b is Any))
    println("c is Any : "+(c is Any))

    // Shape 인터페이스를 상속받은 Rectangle, Circle 클래스
    var rectangle = Rectangle(10.0,20.0)
    var circle = Circle(10.0)

    println("rectangle is Shape : ${rectangle is Shape}") // true
    println("circle is Shape : ${circle is Shape}") // true
}