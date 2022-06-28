package `5_OOP_basic`
/* class 클래스이름{
        속성(property) 및 메서드(method) 정의
        속성 : 클래스 내부에 정의한 변수 혹은 상수
        메서드 : 클래스 내부에 정의한 함수
    }
*/
class Car {
    // 프로퍼티(속성) 정의
    var speed: Int = 0
    var isMoving: Boolean = true

    // 메서드
    fun accelerate() {
        speed += 10
    }

    fun decelerate() {
        speed -= 10
        if (speed < 0) speed = 0
    }

    fun move() {
        isMoving = true
    }

    fun stop() {
        isMoving = false
    }

    fun showSpeed() {
        println("current speed : $speed")
    }
}

fun main(args: Array<String>) {
    var c = Car() // 인스턴스화 : 클래스를 인스턴스(객체)로
    c.move()
    println(c.isMoving)

    c.accelerate()
    c.showSpeed()
    c.decelerate()

    c.stop()
    println(c.isMoving)
}


