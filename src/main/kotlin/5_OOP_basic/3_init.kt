/* 생성자
    : 클래스에 필요한 객체를 생성하는 과정에서 필요한 작업을 수행하고
    필요한 속성의 초기값을 설정하기 위해 사용하는 특수한 메서드
* */

class Person1 {
    // 적절한 초기값을 대입
    var name: String = "홍길동"
    var age: Int = 0
    // 현재 이 클래스의 경우 생성자가 정의되어 있지 않고, 속성값 자체에 바로 적절한 초깃값을 대입함
}

// 객체(인스턴스)를 생성하기 위해 클래스 이름을 호출하는 시점에서 바로 속성을 초기화하기 위해선
// 주 생성자(primary constructor)를 정의해야 함
// class 클래스이름(val/var 속성이름:속성타입, .. ){ }
class Person2(var name: String, var age: Int, var gender: String) {}

// 만약 var/var 키워드를 생략하면 속성이 아니라 함수에 전달되는 매개변수로 취급됨
// property가 정의된 것이 아니므로 접근 자체가 불가능함.
class Person3(name: String, age: Int, gender: String) {}

// 위와 같은 주 생성자에는 코드 작성이 불가능
// 속성값 초기화시 복잡한 코드를 작성해야하는 경우는 init 블록 사용함
// init 블록은 주 생성자를 통한 속성값 초기화 작업(대입작업)이 끝난 직후 실행됨
class Spy1(var realName: String, var realAge: Int, var realGender: String) {
    var fakeName: String
    var fakeAge: Int
    var fakeGender: String

    // 주 생성자는 코드를 작성할 수 없으므로
    // 주 생성자 호출 후 바로 호출되는 init 블록 내부에
    //전달받은 진짜이름, 나이, 성별 정보를 이용한 가짜 정보 생성 코드 잓
    init {
        fakeName = realName.reversed()
        fakeAge = realAge * 2
        fakeGender = if (realGender == "남성") "여성" else "남성"
    }
}

// 만약 진짜 정보가 저장될 속성값이 필요 없고, 가짜 정보만 저장해야 한다면
// 주 생성자의 var 키워드를 생략해 값으로만 전달받고 진짜 정보의 속성을 안 만들어 됨
class Spy2(realName: String, realAge: Int, realGender: String) {
    var fakeName: String
    var fakeAge: Int
    var fakeGender: String

    init {
        fakeName = realName.reversed()
        fakeAge = realAge * 2
        fakeGender = if (realGender == "남성") "여성" else "남성"
    }
}

// 사실 위의 예제로 된 것의 init 블록 내부에서 수행하는 개별 속성값 초기화 작업은
// 한 줄의 대입문으로 끝날 수 있느 간단한 작업이므로 init 없이 직접 대입 해도 됨
class Spy3(realName: String, realAge: Int, realGender: String) {
    var fakeName: String = realName.reversed()
    var fakeAge: Int = realAge * 2
    var fakeGender: String = if (realGender == "남성") "여성" else "남성"
}

// 생성자로 값을 전달하지 않았을 때 대입할 적절한 기본값이 있을 경우, 기본값 설정
// 단 성별의 경우 기본값을 설정하지 않았으므로 반드시 인자를 통해 값을 전달해야함
class Person4(val gender: String, val name: String = "무명씨", var age: Int = 20) {}


fun main() {
    var p1 = Person1()
    println("${p1.name} : ${p1.age}")

    p1.name = "김철수"
    p1.age = 20
    println("${p1.name} : ${p1.age}")

    // 생성자에 값을 전달할 때는 정의한 속성 순서대로 값을 전달해야함
    var p2 = Person2("김철수", 20, "남성")
    println("${p2.name}, ${p2.age}, ${p2.gender}")

    var p3 = Person3("김철수", 20, "남성")
    // println("${p2.name}, ${p2.age}, ${p2.gender}") // Error

    var spy1 = Spy1("김철수", 20, "남성")
    var spy2 = Spy2("이영희", 30, "남성")
    println("${spy1.fakeName}, ${spy1.fakeAge}, ${spy1.fakeGender}")
    println("${spy2.fakeName}, ${spy2.fakeAge}, ${spy2.fakeGender}")

    var p4 = Person4("남성")
    println("${p4.name}, ${p4.age}")
    p4 = Person4("여성","이영희",15)
    println("${p4.name}, ${p4.age}")
    // p4 = Person4("남성",30) // error : 생성자의 순서가 맞지 않아서
    p4 = Person4("남성",age = 30)
    println("${p4.name}, ${p4.age}")
}
