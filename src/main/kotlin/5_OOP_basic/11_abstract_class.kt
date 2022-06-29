// 추상 클래서 225 - 231
/*
추상(Abstract)
: 일부분만 구현된 클래스로 상속을 통해 나머지 부분을 오나성해야 하는 클래스
자식 클래스로 전달해야 할 속성과 메서드가 존재하는데
일부 구현을 미완성인 상태로 남겨두고 자식 클래스에서 메서드 재정의를 통해 반드시 구현되도록 강제하고 싶은 경우에 사용

추상 클래스는 일부만 구현된 클래스이므로
해당 클래스 타입의 객체를 생성하는 것은 불가능하다.
상속을 통해 완전히 정의한 자식 클래스를 통해서 실제 객체를 생성할 수 있다.

특정 클래스를 추상 클래스로 선언하려면 class 키워드 앞에
abstract 키워드를 지정해 해당 클래스가 추상 클래스임을 선언해야합니다.
추상 클래스는 상속을 통해서 완성될 수 있는 클래스이므로 open 키워드는 생략 가능하다.
*/

abstract class AbstractClass(var a: Int, var b: Int) {
    // 일반적인 클래스를 정의할 때 처럼 메서드를 정의함
    fun concreteMethod() {
        println("from concrete method")
    }

    // 기능이 구현되지 않은 추상 메서드를 정의
    // 추상 메서드 : 메서드의 이름, 매개변수, 반환타입만 정의된 실제 구현내용이 없는 메서드
    // 자식클래스에서 꼭 이 기능을 재정의 해서 써야한다는 가이드라인을 설정하는 메서드
    abstract fun abstractMethod(arg: Int): Int
}

class ConcreteClass(a: Int, b: Int, var c: Int) : AbstractClass(a, b) {
    // 추상 메서드는 반드시 구현해야함!
    // 구현하지 않으면 컴파일 에러 발생
    override fun abstractMethod(arg: Int): Int {
        println("자식 클래스에서 추상 메서드 구현")
        return (arg + a + b + c)
    }
}

abstract class Phone{
    var turnOn = false

    // 실제 구현이 포함된 메서드
    fun turnOn(){
        turnOn = true
        boot()
    }
    fun turnOff(){turnOn = false}

    // 자식 클래스에서 구현할 추상 메서드를 호출하는 메서드
    // 이러한 방식으로 앞으로 자식 크래스에서 구현할 추상 메서드를 구현 메서드 내부에서 호출하는 것도 가능함.
    fun boot(){
        checkSystem()
        checkUpdate()
        showMainUI()
    }

    //자식 클래스에서 구현해야할 추상메서드들
    // 이 추상 메서드의 특징은 '메서드에서 수행해야 할 구체적인 내용을 일반화하기 어렵다'
    // 제각기 달라지는 부분은 추상 메서드로 정의하고, 구체적으로 행동 방식이 정해진 메서드만 추상 클래스에서 정의하면
    // 구현이 달라지는 부분만 상속을 통해 코드를 작성하면 되므로 중복없이 효율적으로 코드를 작성할 수 있다.
    abstract fun checkUpdate()
    abstract fun checkSystem()
    abstract fun showMainUI()
}

class GalaxyS : Phone(){
    override fun checkSystem() {
        println("CPU, RAM, 하드, 카메라, 삼성 핸드폰 전용 기능을 체크")
    }

    override fun checkUpdate() {
        println("삼성 서버로 업데이트 가능 여부 호출하기")
    }

    override fun showMainUI() {
        println("삼성 UI 보여주기.")
    }
}
//5
class V30: Phone(){
    override fun checkSystem() {
        println("CPU, RAM, 하드, 카메라, LG 핸드폰 전용 기능을 체크")
    }

    override fun checkUpdate() {
        println("Lg 서버로 업데이트 가능 여부 호출하기")
    }

    override fun showMainUI() {
        println("LG UI 보여주기.")
    }
}

fun main() {
    // 추상클래스는 미완성된 클래스이므로 객체를 생성할 수 있다.
    // var a = AbstractClass(1,2)

    // 자식 클래스의 객체를 생성한 후
    // 부모 클래스에서 물려받은 메서드를 호출하는데
    // 이미 부모 클래스에서 구현이 완료된 메서드이므로 문제 없이 호출 됨
    var c = ConcreteClass(1, 2, 3)
    c.concreteMethod()

    // 부모 클래스를 통해 물려받은 추상 메서드를 호출함
    // 이 메서드는 상속 과정에서 재정의를 통해 메서드 내용을 구현했으므로 호출할 수 있다.
    var r = c.abstractMethod(100)
    println(r)

    // Phone 관련
    val p1 = GalaxyS()
    val p2 = V30()

    p1.turnOn()
    p1.turnOff()

    p2.turnOn()
    p2.turnOff()
}