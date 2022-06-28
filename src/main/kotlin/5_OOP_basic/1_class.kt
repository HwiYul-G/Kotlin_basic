package `5_OOP_basic`

/*
OOP(Object-Oriented Programming)
: 주변의 사물과 개념을 객체라는 형태로 모델링해서 프로그램의 기본 단위로 활용하고
해당 객체들의 협업 과정을 통해 큰 단위의 최종 과업을 수행할 수 있게 하는 프로그래밍 패러다임

OOP 패러다임을 도입의 장점
독립적인 역할을 부여받은 클래스로 나누어 구현
-> 복잡성 줄이고 코드의 관리가 쉬워짐
-> 함수를 기본단위로 사용하는 절차지향 프로그래밍 언어에 비해 버그가 적게 발생하고 테스트하기 쉬운 프로그램램*

각 객체는 객체가 가진 정보와 해당 정보와 밀접한 관련을 가진 함수를 이용해 자신에게 주어진 역할을 수행한다.
-> 객체에 부여할 역할이 무엇이고 그 역할을 수행하기위해 필요한 정보는 무엇이고 어떤 행동을 취할 수 있는 지를 먼저 생각하고 정의해야함

객체를 생성하는 과정에서 객체의 초기 상태값을 설정하는 과정도 수반됨.

실제로 클래스를 이용해서 만들어진 객체를 클래스의 인스턴스라고 하고, 클래스를 통해 실제 객체를 생성하는 과정을 인스턴스화라고 함.
클래스에는 객체의 정보를 대상으로 밀접한 작업을 수행하는 함수도 함께 정의할 수 있다.
*/

class Television(
    val manufacturere: String,
    var currentChannel: Int,
    var currentVolume: Int,
    var turnOn: Boolean = false
) {
    fun turnOn() {
        turnOn = true
    }

    fun turnOff() {
        turnOn = false
    }

    fun adjustChannel(newChannel: Int) {
        if (newChannel in 1..60) {
            currentChannel = newChannel
        }
    }

    fun increaseVolume() {
        if (currentVolume <= 100) {
            currentVolume++
        }
    }

    fun decreaseVolume() {
        if (currentVolume > 0) {
            currentVolume--
        }
    }

    fun adustVolume(newVolume: Int) {
        currentVolume = newVolume
    }

    fun mute() {
        currentVolume = 0
    }

    override fun toString(): String {
        return "Television(manufacturer=$manufacturere, currentChannel=$currentChannel, currentVolume = $currentVolume, turnOn = $turnOn)"
    }
}

fun main(){
    var samsungTV = Television("삼성",1,1)
    println(samsungTV)

    samsungTV.turnOn()
    println(samsungTV)

    samsungTV.adustVolume(50)
    samsungTV.increaseVolume()
    println(samsungTV)

    samsungTV.adjustChannel(42)
    println(samsungTV)

    samsungTV.mute()
    println(samsungTV)

    samsungTV.turnOff()
    println(samsungTV)
}
