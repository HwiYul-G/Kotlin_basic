/*
    object 키워드와 익명 클래스
    - 익명 클래스
    : 말 그대로 이름 없는 클래스로, 익명 클래스를 이용하면 간단하게 클래스를 상속받는 자식 클래스를 '정의하는 동시에 객체를 생성'할 수 있다.

    인터페이스를 구현하는 과정에서 수행하는 작업은
    추상 메서드를 재정의(override)하는 작업밖에 없어서 귀찮은 클래스 정의 과정을 건너뛰고
    간단히 익명 클래스를 통해 객체를 생성할 수 있다면 더 간결한 코드를 작성할 수 있다.

    특히 GUI 기반 프로그램을 작성하는 과정에서
    이벤트 처리와 관련된 추상 메서드를 재정의해야할 때가 많은데,
    이 상황에서 익명 클래스를 자주 이용하게 됨.
*/
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import java.awt.event.MouseEvent
import java.awt.event.MouseListener
import javax.swing.JButton

open class OpenClass{
    fun func1(){
        println("from func1")
    }
    open fun func2(){
        println("from func2")
    }
}

abstract class MyAbstractClass{
    var x: Int = 10
    var s : String = "Hello"

    fun concreteFunction(){
        println("from concrete")
    }
    abstract fun abstractFunction()
}

interface IMyInterface{
    fun abstractFunction()
}

/*
    익명 클래스를 만들기 위해선 object 키워드를 사용
    익명 클래스를 정의하는 동시에 해당 클래스의 객체를 생성함
    object : 상속 클래스 혹은 구현 인터페이스 이름{
        // 필요한 메서드 구현
    }
*/

fun main(){
    // 일반 클래스를 상속받으며 재정의를 허용한 메서드를 재정의
    // 클래스를 상속받는 익명 클래스 선언 및 객체 생성
    var o = object : OpenClass(){
        override fun func2() {
            println("from func2 (override)")
        }
    }
    o.func1()
    o.func2()

    // 추상 클래스를 상속받으며 추상 메서들르 재정의
    var ab = object : MyAbstractClass(){
        override fun abstractFunction() {
            println("from abstract")
        }
    }
    println(ab.x)
    println(ab.s)
    ab.concreteFunction()
    ab.abstractFunction()

    // 인터페이스를 구현하며 추상 메서드를 재정의
    var i = object : IMyInterface{
        override fun abstractFunction() {
            println("from abstract")
        }
    }
    i.abstractFunction()

    // adHoc 클래스 만들기
    val adHoc = object{
        var x:Int = 0
        var y:Int = 0

        fun print(){
            println("$x $y")
        }
    }
    adHoc.print()

    //JAVA의 GUI 라이브러리인 Swing에서 제공하는 JButton(버튼 컴포넌트)를 설정하는 과정에서
    // 실제로 익명 클래스가 활용되는 방식 보기
    var btn = JButton("Button")
    // ActionListener : 인터페이스로 버튼이 클릭된 이후 작업을 정의하기 위해 사용됨
    btn.addActionListener(object : ActionListener{
        // ActionListener 인터페이스는 재정의 해야할 하나의 추상메서드 actionPerfomed를 포함함
        // 함수형 인터페이스 : 하나의 추상 메서드만 포함하고 있는 인터페이스
        override fun actionPerformed(e: ActionEvent?) {
            println(e)
            println("Button clicked!")
        }
    })
    // 구현해야할 추상 메서드가 1개인 함수형 인터페이스의 경우
    // 람다함수의 형태로 축약해서 인터페이스를 구현할 수 있다.
    btn.addActionListener{
        // 전달받는 ActionEvent 객체의 이름을 e로 정의
        e ->
        println(e)
        println("Button clicked!")
    }
    // 여기서 추상 메서드를 통해 전달받는 인자값이 하나라면 더 축약된 코드를 작성할 수 있다.
    // 이 경우 전달받는 객체에 접근할 때 it이라는 대명사를 사용해 접근함.
    btn.addActionListener{
        // 매개변수의 이름을 지정하는 e -> 구문을 생략하고 it이라는 대명사로 객체에 접근
        println(it)
        println("button Clicked!!")
    }

    // 만약 함수형 인터페이스를 구현하는 익명 객체를 축약된 코드로 생성하고 싶다면
    // 다음과 같이 , 인터페이스의 이르을 쓴다음
    // 람다 함수 블록을 정의해서 추상 메서드를 구현한다.
    // 이벤트 리스너 인터페이스를 익명 클래스를 활용해 람다 함수를 사용하는 축약된 코드로 구현
    val listener = ActionListener{
        println(it)
        println("Button clicked!")
    }
    // 이후 리스너 객체를 활용
    btn.addActionListener(listener)
    /*
        SAM 변환(SAM Conversion)
        람다 함수를 인터페이스 구현 객체로 바꾸는 변환 작업
        단, 람다 함수를 전달했다고 해서 실제로 해당 함수를 그대로 사용하는 것은 아니고,
        람다 함수의 내용을 바탕으로 구현된 추상 메서드를 포함한 익명 클래스가 생성됨.
    */

    // Mouse 관련 event 처리에 필요한 MouseListener 인터페이스를
    // 익명 클래스를 통해 구현하는 예제.
    // MouseListener 인터페이스에는 구현해야할 추상 메서드가 2개 이상 있으므로 함수형 인터페이스가 아님
    // 따라서 람다 함수를 활용해 축약 코드를 작성할 수 없으므로 object 키워드를 통해 익명 클래스 객체를 생성해야 함
    btn.addMouseListener(object :MouseListener{
        override fun mouseReleased(e: MouseEvent?) {
            println("mouseReleased")
        }

        override fun mouseEntered(e: MouseEvent?) {
            println("mouseEntered")
        }

        override fun mouseClicked(e: MouseEvent?) {
            println("mosueClicked")
        }

        override fun mouseExited(e: MouseEvent?) {
            println("mouseExited")
        }

        override fun mousePressed(e: MouseEvent?) {
            println("mousePressed")
        }
    })
}