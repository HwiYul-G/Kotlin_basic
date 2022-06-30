/* 인터페이스
인터페이스는 추상 클래스와 비슷한 부분이 많은데 구체적인 동작 방식은 조금 다르다.
인터페이스는 추상 클래스와 마찬가지로 추상 메서드와 구현 메서드를 포함할 수 있고,
인터페이스를 통해 객체를 생성하는 것은 불가능하다.
그래서 반드시 인터페이스를 구현(상속)하는 클래스를 통해 객체를 생성해야한다.

단, 인터페이스는 생성자를 정의할 수 없다. (<-> 추상클래스는 객체 생성이 안되도 생성자 정의 가능)

인터페이스에는 구현이 완료된 메서드와 해당 인터페이스를 구현(상속)하는 자식 클래스에서 재정의(override)해야할 추상 메서드를 포함할 수 있다.
인터페이스 내부에 필요한 속성도 정의할 수 있다.
하지만 인터페이스 내부에 정의한 속성은 값을 가질 수 없는 추상 속성이므로 인터페이스 내부에서 초기화하는 것이 불가능하다.
반드시 해당 인터페이스를 상속받는 클래스에서 해당 속성을 구현해야 한다.
*/
interface MyInterface {
    // 인터페이스 내부에 속성 정의
    // 이 속성은 추상 속성이므로 값을 대입하거나 게터, 세터X
    var prop: Int

    // 내용이 구현된 메서드 정의 가능
    fun concreteMethod(): Int {
        return 100
    }

    // 인터페이스의 추상메서드는 abstract 키워드를 생략해도 됨
    // 그저 함수 정의 후 몸체를 쓰지 않으면 추상메서드로 인식됨
    // 단, 재정의할 수 없는 메서드를 정의할 수 있는 추상 클래스와 달리 인터페이스에서 정의한 함수는 모두 재정의가 가능함
    fun abstractMethod(): Int
}

// 인터페이스를 구현하는 구현 클래스(인터페이스를 상속받는 자식 클래스)
class ClassImplementsMyInterface(prop: Int) : MyInterface {
    // 부모 인터페이스에 정의한 추상 속성을 재정의 (게터 세터가 필수는 아님)
    override var prop: Int = prop
        get() = field - 1
        set(value) {
            field = if (value <= 0) value * -1 else value
        }

    // 부모 인터페이스에서 정의한 추상 메서드를 구현
    // 인터페이스는 해당 인터페이스를 구현하는 자식 클래스에서 메서드 재정의(override)를 통해 특정 동작(기능)이 수행되는 것을 보장하기 위해 사용
    // 인터페이스를 상속받은 경우 그 추상 메서드를 구상 클래스에서 구현하지 않으면 오류가 발생하므로 반드시 메소드를 재정의해서 구현할 것!
    override fun abstractMethod(): Int {
        return 100
    }
}

// 인터페이스 Shape에서 정의된 메소드에 구현부가 없으므로 모두 추상 메서드이다.
interface Shape {
    fun calculateArea(): Double
    fun calculatePerimeter(): Double
}

//
class Rectangle(var width: Double, var height: Double) : Shape {
    override fun calculateArea(): Double {
        return width * height
    }

    override fun calculatePerimeter(): Double {
        return (width * 2) + (height * 2)
    }
}

//3
class Circle(var radius: Double) : Shape {
    override fun calculateArea(): Double {
        return Math.PI * (radius * radius)
    }

    override fun calculatePerimeter(): Double {
        return Math.PI * (2 * radius)
    }
}

fun main(){
    // 사각형 클래스에서 재정의한 메서드가 출력됨
    var r = Rectangle(10.0,20.0)
    println("Area : ${r.calculateArea()}, Perimeter : ${r.calculatePerimeter()}")

    // 원 클래스에서 재정의한 메서드가 호출됨
    var c = Circle(10.0)
    println("Area : ${c.calculateArea()}, Perimeter : ${c.calculatePerimeter()}")
}