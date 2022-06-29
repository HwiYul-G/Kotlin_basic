/* 아무 클래스도 상속받지 않는 클래스를 정의해도
클래스는 자동으로 Any 클래스를 상속받음 = 모든 클래스는 Any 클래스를 상속받음

Any class의 정의
public open class Any{
    public open operator fun equals(other : Any?) : Boolean
    public open fun hashCode() : Int
    public open fun toString() : String
}

보통 Any 클래스에 포함된 toString, hashCOde, equals 메서드를 자식 클래스에서 모두
재정의(override) 하는 것이 권장됨

toString() : 객체의 class name과 해시코드 값을 보여줌
hashCode() : 객체의 레퍼런스 값을 가지고 있음.
equals() : hashCode로 객체를 비교함

타입과는 상관없이 모든 종류의 값을 전달받고자 하는 함수나 메서드에서는
다음과 같이 값의 타입을 Any로 지정한 경우가 많다.
public actual inline fun println(message: Any?){
    System.out.println(message)
}
println 함수는 Any 타입의 객체를 전달받도록 정의돼 있다.
즉 null 값을 포함한 모든 값을 전달받아 출력하는 기능을 제공한다.

또한 여러 값을 저장하기 위핸 컬렉션에 다양한 타입의 값을 저장할 경우에도 타입 추론 과정에서
공통 조상인 ANy 타입으로 결정하게 됨

예를들어, 정수, 실수, 문자열이 저장된 리스트와 맵을 선언하면, 리스트와 맵에 저장하는 자료의 타입은 Any로 설정됨
val list : List<Any> = listOf(1, 1.234, "Hello")
val map : Map<Any, Any> = mapOf(1 to "Hello", "Hello" to 100, 1.234 to 100L)

명시적으로 제네릭 타입을 Any타입으로 설저했지만 타입 추론이 가능하므로
타입 선정 부분을 생략해도 괜찮다
val list = listOf(1, 1.234, '"Hello")
val map = mapOf(1 to "Hello", "Hello" to 100, 1.234 to 100L)
*/

fun main(){
    var num = "Hello"
    println(num == "Hello") // true
    println(num.hashCode())
    println(num.toString())
}
