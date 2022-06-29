/* 상속(Inheritance)
: 특정 클래스의 모든 속성과 메서드를 그대로 물려받은 상태로 새로운 속성이나 메서드를 추가해서 클래스를 정의하고 싶을 때 사용
이때, 상속해주는 클래스를 부모 클래스(parent class), 상속을 받는 클래스를 자식 클래스(child class)
*/

// open 키워드 : 해당 클래스가 상속 가능하다는 사실을 명시
// open 키워드를 지정하지 않으면 해당 클래스는 상속할 숭 ㅓㅄ음
open class Person(var name: String, var age: Int, var gender: String) {
    fun eat(food: String) {
        println("${name}가 ${food}을/를 먹습니다.")
    }

    fun sleep(hour: Int) {
        println("${name}가 ${hour}시간 동안 잠을 잡니다.")
    }
}

// 클래스 이름 옆에 콜론 기호(:)와 상속받을 클래스의 이름을 지정함
// 자식 클래스의 주생성자로 전달받은 값 name, age, gender을 부모 클래스 명
// 오른쪽 괄호 안에 지정해 부모 클래스의 주 생성자를 호출하는 과정에서 값이 전달됨
// 부모 클래스로 넘겨주는 값은 var, val 키워드가 지정되어 있지 않다.
// 보무의 주 생성자에 전달할 용도이므로 속성으로 정의할 필요가 없다.
// 단 자식 클래스의 고유한 속성을정의하기 위해선 var 키워드를 지정한다.
class Employee(
    name: String, age: Int, gender: String,
    var company: String, var salary: Int, var position: String
) : Person(name, age, gender) {
    // 추가 메서드 정의
    fun work(hour: Int) {
        println("${company} 회사에 소속된 ${name}가 ${hour}시간 동안 일합니다.")
    }
}
/* 상속과 관련된 자바와 코틀린의 차이점과 공통점
자바에선 클래스의 상속을 기본적으로 허용함.
만약 상속하지 못하도록 막고 싶은 클래스가 있다면, final 키워드로 해당 클래스의 상속이 불가함을 명시함

하지만 코틀린은 반대로 '화이트리스트식 접근법'을 지향해서 기본적으로 상속이 허용되지 않고
open 키워드를 이용해 상속을 허용한 클래스만 상속할 수 있다.

자바와 마찬가지로 코틀린에서도 다중상속은 허용되지 않습니다.
따라서 상속받을 수 있는 클래스의 개수는 1개로 고정된다.
인터페이스의 경우 여러개를 구현하는 것은 가능하다.
*/

// super 키워드를 이용한 부모 객체 접근
open class Parent(var parentProp: Int){
    fun parentFunc(){
        println("from parentFunc")
    }
}
class Child(prop: Int, var childProp: Int) : Parent(prop){
    fun childFunc(){
        // super 키워드를 통해 부모 클래스의 parentProp 속성, 메서드을 출력함
        // this 키워드를 생략해도 되는 것처럼
        // super 키워드를 생략해도 자식 클래스에 존재하지 않은 속성, 메서드은
        // 자동으로 부모 클래스에서 찾아서 접근함
        println("${super.parentProp}")
        println("${parentProp}")

        super.parentFunc()
        parentFunc()
    }
}

fun main(){
    var emp1 = Employee("김철수",25,"남성","삼성",10000,"사원")
    var emp2 = Employee("이영희",42,"여성","LG",20000,"과장")

    // 부모 클래스에 있는 Person클래스의 메서드르르 이용할 수 있다.
    emp1.eat("밥")
    emp1.sleep(6)

    emp2.work(8)
}
