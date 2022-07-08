/*
    내부 클래스(inner class) = 중첩 클래스(nested class)
*/

// Inner 클래스는 외부 클래스의 이름을 통해 접근해야 한다는 점을 제외하면
// 기본적으로 별개의 독립된 클래스와 동일하게 동작
// 따라서 Inner 클래스에서 Outer 클래스의 속성이나 메서드에 접근할 수 없다.
class Outer(var x: Int) {
    fun outerFunctin() {
        println("x : $x")
    }

    // Outer 클래스에 포함된 내부 클래스 정의
    class Inner(var y: Int) {
        fun innerFunction() {
            // outerFunction() // 불가능
            // println("x : $x) // 불가능
            println("y : $y")
        }
    }
}

// 내부 클래스로 정의할 경우
// 외부 클래스의 이름을 통해 내부 클래스에 접근해야 하기 때문에
// 내부 클래스가 외부 르래스에 종속되거나
// 외부 클래스와 관련성이 높다는 것을 코드를 통해 유추할 수 있다.

// 내부 클래스에서 외부 클래스의 속성과 메서드에 접근해야 한다면
// inner 키워드를 사용해서 내부 클랫를 정의해야 한다.
class Outer2(var x: Int, var z: Int) {
    fun outerFunction() {
        println("x : $x")
    }

    inner class Inner(var x: Int, var y: Int) {
        fun innerFunction() {
            // 'this@외부클래스명' 으로 외부 클래스 객체에 접근
            // @(앳 기호)를 이용해 어떤 this인지(스스로를 가리키는 this 인지 외부 클래스의 객치인 this 인지)
            // 명확하게 명시한 this를 자격 있는 this(Qualified this)라고 한다.
            // 외부 클래스에 정의도니 것과 이름이 같은 속성이나 메서드가 없다면
            // 자격 있는 this를 이용해 외부 객체에 명시적으로 접근할 수 있다.
            this@Outer2.outerFunction()
            // this@Outer2를 통해 접근하지 않고 직접 호출도 가능
            outerFunction() // 가능

            // Outer와 Inner 클래스 각각에 x라는 이름의 속성을 가지고 있으므로
            // 소속이 불명확한 x라는 이름을 통해서는 사용할 수 없으므로
            // 명확하게 명시해줘야함
            // Inner class 내부이므로 this.x 와 this@Inner.x는 동일함
            println("x : ${this.x}, x : ${this@Inner.x}")
            println("x : ${this@Outer2.x}, y : $y")
            // Outer의 속성인 z는 명확한 이름을 가지므로 this없이 바로 접근
            println("z : ${z}")
        }

        // Inner 클래스 객체는 내부적으로 Outer 객체의 참조를 가지고 있다.
        // 따라서 자격있는 this를 통해 외부 객체를 통째로 반환할 수도 있다.
        fun getOuter(): Outer2 {
            return this@Outer2
        }
    }

    class Outer3(var x : Int){
        // 만약 내부 클래스를 오직 외부 클래스 안에서만 사용한다면
        // 내부 클래스의 접근 제어자를 private로 설정해서 내부에서만 사용하도록 강제할 수 있다.
        private var inner : Inner

        init{
            inner = Inner(100)
        }

        fun outerFunction(){
            println("x : $x")
            // 내부 클래스의 객체를 사용할 수 있다.
            inner.innerFunction()
        }

        // Inner 클래스의 객체가 외부로 노출되지 않도록 private 했으므로
        // 특정 메서드에서 해당 객체를 반환하는 것도 허용되지 않아서 아래의 함수는 불가능
        /*
        fun getInner() : Inner{
            return inner
        }
        */

        // Outer3 클래스 내부에 Inner 클래스를 정의하며
        // 접근 제어자를 private으로 설정해 내부 클래스의 존재를 숨김
        private class Inner(var y : Int){
            fun innerFunction(){
                println("y : $y")
            }
        }

    }

    // 만약 어떤 식으로든 내부 클래스의 객체에 직접 접근할 수 있도록 반환할 필요가 있다면
    // 내부 클래스의 접근 제어자를 public으로 설정해야 함
}

// 내부 클래스와 마찬가지로 내부 인터페이스도 정할 수 있다.
class Outer4{
    //1
    interface  InnerInterface{
        fun innerFunction()
    }
}
// 외브 클래스 내부 인터페이스에 접근하려면 외브 클래스 명을 경유해야함
class MyNestedInterfaceTestClass : Outer4.InnerInterface{
    // 인터페이스이므로 추상 메서드를 구현해야함
    override fun innerFunction() {
        println("my implemented inner function")
    }
}

fun main() {
    var outer1 = Outer(10)
    // Outer 클래스 내부에 있는 Inner 클래스 객체 생성
    var inner1 = Outer.Inner(20)

    println(outer1.x)
    outer1.outerFunctin()

    // println(outer1.y) // 접근 불가
    // outer1.innerFunction() // 접근 불가

    println(inner1.y)
    inner1.innerFunction()

    // println(inner1.x) // 접근 불가
    // inner1. outerFunction() // 접근 불가

    //inner 키워드를 이용한 NestedClass를 사용하는 부분
    // Outer2 클래스의 객체를 생성하고 해당 객체(outer2)를 통해 두 개의 Inner 객체 생성
    // inner2, inner3은 서로 다른 객체이지만 Outer2 객체를 통해 생성했으므로, 같은 외부
    var outer2 = Outer2(10, 100)
    var inner2 = outer2.Inner(20, 200)
    var inner3 = outer2.Inner(30, 300)
    
    // inner2와 inner3의 객체 비교
    // 다른 객체이므로 false
    println("inner2 === inner3 : ${inner2 === inner3}") // false
    
    // 내부에서 참조하는 Outer2 객체를 반환해서 참조 비교
    println("inner2.getOuter() === inner3.getOuter() : ${inner2.getOuter() === inner3.getOuter()}")
    
    println("inner2.innerFunction()")
    inner2.innerFunction()
    println("inner3.innerFunction()")
    inner3.innerFunction()
    
    // inner2의 외부 클래스 객체를 반환받는 함수를 통해
    // 외부 클래스 객체의 x값을 변화시킴
    // 외부 클래스 객체를 같은 것을 참조하므로 둘 다 바뀐것을 볼 수 있음.
    inner2.getOuter().x = 100
    println("inner2.innerFunction() (x에 100 대입 후)")
    inner2.innerFunction()
    println("inner3.innerFunction() (x에 100 대입 후)")
    inner2.innerFunction()
    inner3.innerFunction()
}