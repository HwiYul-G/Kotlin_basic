import jdk.jfr.DataAmount

// interface와 다중상속
// 추상클래스와 달리 인터페이스는 '다중상속' 이 가능하다.

// 인터페이스 2개 정의, 두 인터페이스에 이름이 동일하고 내용이 이미 구현된 메서드 calc도 정의
interface MyInterface1 {
    fun methodA()

    fun calc(x: Int, y: Int) = x + y
}

interface MyInterface2 {
    fun methodB()

    fun calc(x: Int, y: Int) = x * y
}

// 두 개의 인터페이스를 모두 구현해야하는 클래스
class MultipleInterfaceExtender : MyInterface1, MyInterface2 {
    // 각 인터페이스의 추성 메서드를 모두 구현해야함
    override fun methodA() {
        println("methodA 구현")
    }

    override fun methodB() {
        println("methodB 구현")
    }

    // 이름이 같은 메서드를 포함한 여러 인터페이스를 상속받은 경우
    // 인터페이스를 구현한 클래스를 통해 해당 메서드를 호출할 때 어떤 메서드를 호출해야 할지 모호해지는 문제가 발생
    // val calc = super.calc(x, y) // 이러한 식으로 된경우에 모호함
    override fun calc(x: Int, y: Int): Int {
        // 따라서 자식 클래스에서 메서드를 재정의해서 어떤 인터페이스의 메서드를 호출할지 결정하도록 구현해야함
        // 아래와 같이 super와 어떤 클래스인지를 명세해야한다.
        val calc1 = super<MyInterface1>.calc(x, y)
        val calc2 = super<MyInterface2>.calc(x, y)
        return calc1 + calc2
    }
}

// 게임에 등장하는 유닛을 구현하기 위한 추상 클래스와 인터페이스
abstract class Unit(val name: String, var dead: Boolean = false, var health: Int = 100) {
    fun printAttackMessage(other: Unit) {
        println("${name}이 ${other.name}을 공격합니다.")
    }

    fun printRepairMessage(other: Unit) {
        println("${name}이 ${other.name}을 수리합니다.")
    }
}

interface Attackable {
    fun attack(other: Unit)
}

interface Repairable {
    fun repair(other: Unit)
}

// 추상클래스 1개와 인터페이스 1개 상속
class AttackUnit(name: String, val attackAmount: Int) : Unit(name), Attackable {
    override fun attack(other: Unit) {
        printAttackMessage(other)
        other.health -= attackAmount
        if (other.health <= 0) other.dead = true
    }
}

// 추상 클래스 1개와 인터페이스 1개 상속
class RepairUnit(name: String, val repairAmount: Int) : Unit(name), Repairable {
    override fun repair(other: Unit) {
        if (!other.dead) {
            printRepairMessage(other)
            other.health += repairAmount
        }
    }
}

// 추상클래스 1개와 인터페이스 2개 상속받음
class GodLikeUnit(name: String) : Unit(name), Attackable, Repairable {
    init {
        health = 1000000 // Unit을 상속받아 그 속성값 초기화
    }

    override fun attack(other: Unit) {
        printAttackMessage(other)
        other.health = 0
        other.dead = true
    }

    override fun repair(other: Unit) {
        printRepairMessage(other)
        other.dead = false
        other.health = 100
    }
}

fun main(args: Array<String>) {
    val unit1 = AttackUnit("공격 유닛 a", 80)
    val unit2 = RepairUnit("수릿 유닛 b", 50)
    val godUnit = GodLikeUnit("만능 유닛 c")

    unit1.attack(godUnit)
    unit1.attack(unit2)
    unit2.repair(godUnit)
    godUnit.attack(unit1)

    println("godUnit.health : " + godUnit.health)
    println("unit2.health : " + unit2.health)
    println("unit1.health : " + unit1.health)
    println("unit2.health : " + unit2.health)
    println("unit1.health : " + unit1.health)
}
