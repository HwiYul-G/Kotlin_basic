// 160p - 161p
// 함수 오버로딩(function overloading)
// : 이름이 같은 함수를 만들고 함수의 인자값의 개수나 타입을 다르게 정의하는 것

// square 함수 오버로딩 (인자의 타입이 모두 다름)
// 호출 시에는 인자의 타입에 따라 그에 대응하는 함수가 호출 됨
fun square(x: Int) = x * x
fun square(x: Long) = x * x
fun square(x: Float) = x * x
fun square(x: Double) = x * x

// 인자의 개수를 다르게 하는 방식으로 함수 오버로딩
fun overloadingTest() = println("overloading test 1")
fun overloadingTest(x: Int) = println("overloading test 2")
fun overloadingTest(x:Int,y:Int) = println("overloding test 3")

// 인자의 개수는 같지만 타입을 다르게
fun overloadingTest(x : Int, y: Double) = println("overloading test 4")
fun overloadingTest(x:Double, y:Int) = println("overloading test 5")

fun overloadingTest(x:Int, y:Int, z:Int) : Int = x+y+z
// 반환값을 다르게 하는 것은 오버로딩에 영향을 주지 않음
// fun overloadingTest(x:Int, y:Int, z:Int) : String = "Hello"

fun main(args: Array<String>) {

}