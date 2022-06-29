// getter, setter 메서드
// : 속성값을 설정할 때 대입할 값이 적합한지 여부를 검토하기위한 일종의 검증 코드를 작성하거나
// 속성값을 반환하기 전에 가공하는 코드를 작성하게 되는 경우
// 속성값을 조회하는 getter와 속성값을 설정하는 setter 를 정의함

class GetterAndSetterDemo() {
    var num: Int = 0
        // value 값은 대입 과정에서 잔달된 대입될 값
        // 이 값을 이용해 필요한 검증 절차를 걸쳐 실제 속성값의 변경 여부를 결정함
        set(value) {
            println("값을 ${value}으로 설정")
            field = value
        }
        // field는 backing field라고도 불리며, 세터나 게터 매서드 내부에 실제 속성값에 접근하기 위해 사용하는 일종읙 ㅏ상 속성
        // 만약 field 속성이 아닌 실제 속성 이름(num)을 통해 값을 대입하려고 시도하거나
        // 게터 메서드에서 값을 반환하려하면 StackOverflow 에러가 발생하므로
        // 꼭 필드 속성을 통해서 갑을 대입하거나 반환해야한다.
        get() {
            println("${field}값을 반환}")
            return field
        }
}
// 게터, 세터를 직접 정의하지 않아도 값을 그대로 대입하는 세터 메스드와 값을 그대로 반환하는 게터 메서드는 자동 생성됨

class Person6_1(val name: String, pAge: Int) {
    var age: Int = 0
        set(value) {
            when {
                value < 0 -> throw Exception("음수 나이는 허용되지 않습니다.")
                value > 200 -> throw Exception("나이가 비정상적으로 많습니다.")
            }
            field = value
        }

    // 미성연자인지 여부를 알려주는 속성(isMinor)
    var isMinor = pAge < 20
        get() = this.age < 20

    // 주 생성자로 전달받은 pAge값을 대입해서 나이 속성 초기화
    // init 블록 내부에서 값을 대입할 경우 앞에 정의한 setter 메서드가 호출됨
    // 따라서 허용되지 않는 ㄱ밧이 전달되는 경우 예외가 발생한다.
    init {
        age = pAge
    }
}

fun main() {
    var demo = GetterAndSetterDemo()
    // 대입이 이뤄지는 과정에서 setter 메서드를 호출하고 대입할 값(100)을 인자로 전달함
    // 세터 내부에서 100이란 값을 value 상수를 통해 참조함
    // value는 함수로 전달받는 매개변수와 같은 역할을 수행하되 대입값이 전달된다고 이해하면 됨
    // 이를 변경하고 싶다면 value가 아닌 다른 이름으로 참조해도 무방함
    demo.num = 100

    // 속성값에 접근해서 값을 읽어옴
    // 읽어오는 과정에서 게터 메서드를 호출함.
    println(demo.num)

    var p1 = Person6_1("김철수",15)
    println("${p1.name}, ${p1.age}, ${p1.isMinor}")

    var p2 = Person6_1("이영희",-1) // 허용되지 않는 값
    println("${p1.name}, ${p1.age}, ${p1.isMinor}")
}
/* 실제 속성의 이름을 통해 setter, getter 메서드를 호출했을 때 StackOverflow 에러가 발생하는 이유
field 속성에 접근해 값을 변경하거나 읽어오도록 구현한 코드를 디컴파일 하면,
자바 코드상으론 아래와 같이 변환 된다.
public final class GetterAndSetterDemo{
    private int num;
    public final int getNum(){ return this.num;}
    public final void setNum(int value){this.num = value;}
}

만약 직접 num 속성에 접근해서 값을 변경하거나 읽어오도록 구현한 코드를 디컴파일 하면 아래와 같다.
public final class GetterAndSetterDemo{
    public final int getNum(){ return this.getNum(); }
    public final void setNum(int value){ this.setNum(value);}
}
결과적으로 getter, setter 없이 num 속성에 직접 값을 대입하면
메서드 재귀 호출이 무한 반복되고 호출 스택의 메모리가 모두 소진되는 시점에 StackOverflow 에러가 발생한다.
*/