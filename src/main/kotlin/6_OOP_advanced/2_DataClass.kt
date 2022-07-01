
/*
    데이터 클래스
    : 단순이 값을 저장하기 위한 용도로 활용하는 클래스
    data 키워드를 이용함
    정의 방법은 아래와 같다.
    data class 클래스명(var/val 데이터이름 : 데이터타입, ... )

    데이터 클래스의 주 용도는 값을 저장하는 것이지만,
    데이터 클래스에 필요한 메서드를 정의하는데 특별한 제약이 있는 것은 아니고

    자동으로 구현되는 메서드도 있다.
    toString() :클래스이름(속성1=속성값1, 속성2=속성값2, ... , 속성n = 속성값n) 으로 객체의 내용을 나타낼 문자열을 반환하는 메서드
    equals() : 두 객체가 논리적으로 같은지 여부를 판단
        일반적으로 equals() 내부에서는 먼저 두 객체의 클래스 타입이 같은지 비교한 후 내부의 속성값이 모두 같은지를 비교하는 방식으로 두객체가 '논리적'으로 같은지 여부 판별
        
        자바의 == 연산자 : 두 객체의 참족 같은지 비교하기 위해 사용
        자바의 equals() : 값이 같은 지 비교
        코틀린의 == 연산자 : 두 객체의 논리적 값이 같은 지 비교
        코틀린의 === 연산자 : 자바의 ==(참조 비교 연산자)의 역할로 객체 참조가 같은지 비교
        
    copy() : 객체를 복사하기 위해 사용하는 메서드
        copy()는 얕은 복사(shallow copy)를 수행하여 속성값을 복사
        얕은 복사는 참조를 복사함.
        
    componentN() : 객체의 속성값을 반환하는 메서드
        데이터 클래스를 선언하면, 해당 데이터 클래스에 포함할 속성의 개수만큼 component 메서드가 생성됨
        - 코틀린에서 제공하는 구조 분해 할당(Destructuring Declaration)을 이용할 때 유용하게 사용되는 메서드임.
            : 구조분해할당은 객체의 속성값을 간편하게 여러 개의 변수에 대입하기 위해 사용하는 방법으로
            var (p1Name, p1Age) p1
            위와 같이 ()안에 변수의 이름을 쓰고 p1 객체를 대입하면 component1, component2 메서드를 호출해서
            전달받은 반환값을 각 변수에 대입한다.
            var p1Name = p1.component1()
            var p1Age = p1.component2()
            위와 같은 동작을 수행한다.

            코틀린에서 기본적으로 제공하는 데이터 저장 클래스인 Pair나 Triple 클래스를 활용할 때도 구조 분해 할당이 유용하게 사용된다.

    hashCode() : 객체가 논리적으로 같은 값을 가지고 있는지 여부를 효율적으로 검사하기위한 용도. 임의의 정숫값을 반환하는 역할을 수행

    클래스의 주 생성자가 아니라 중괄호 내부에 속성을 정의할 경우 copy, equals, hashCode, toString과 같은 메서드의 자동 구현 과정에서 해당 속성을 이용하지 않는다.
    즉, 데이터 클래스에서는 주 생성자에 정의된 속성만을 이용해 메서드의 내용을 구현한다.
    => 특별한 이유가 없다면, 데이터 클래스에서 주 생성자가 아닌 곳에 속성값을 정의하는 것은 지양!

 */

// 단지 값을 저장할 용도로 정의한 데이터 클래스로, 메서드를 하나도 정의하지 않아 중괄호도 없다.
data class Person_OOP(var name: String, var age: Int)

// 직업 속성은 주 생성자에 정의한 것이 아니고 {} 내부에 속성을 정의해서 메서드 자동 구현 과정에서 해당 속성을 이용하지 않음.
data class Person2_OOP(var name: String, var age: Int) {
    var job: String = "Unknown"
}

class PersonNotData(var name: String, var age: Int) {
    operator fun component1() = age
    operator fun component2() = name
}

fun main(args : Array<String>) {

    var p1 = Person_OOP("김철수", 20)
    var p2 = Person_OOP("김영희", 30)
    var p3 = Person_OOP("김철수", 20)

    // toString() : 객체의 내용을 나타낼 문자열을 반환하는 메서드
    // 클래스이름(속성1=속성값1, 속성2=속성값2, ... , 속성n = 속성값n)
    // 위와 같은 방식의 내용이 포함된 문자열이 반환됨.
    var personToString = p1.toString()
    // toString()을 호출해서 직접 문자열을 반환받을 수 있고
    println(personToString)
    println(p1) // println 함수에 객체를 전달하면 내부적으로 toString()이 호출해서 문자열로 변환한 후 출력한다.

    // 비교 연산자(==) : 두 객체의 내용(속성값)이 같은지 비교
    // 비교 연산자를 호출하면 내부적으론 equals()가 호출되고 메서드의 인자값으로 비교할 대상의 객체가 전달됨
    println(p1 == p2) // false
    println(p1 == p3) // true
    // 참조 비교 연산자(===) : 두 객체의 참조가 같은지 비교
    // 생성자를 통해 새로운 객체를 생성할 때마다 힙(Heap)이라는 이름이 붙은 특정 메모리 영역에 객체가 생성됨.
    // 따라서 객체를 생성할 때마다 서로 다른 참조(메모리 주소)를 가진 객체가 생성된다.
    // 비록 p1과 p3 객체의 속성값(논리적인 정보)는 같더라도
    // 두 객체는 생성자를 통해 생성된, 독립된 메모리 공간을 점유하는 참조가 다른 객체이므로 false를 반환한다.
    println(p1 === p3) // false

    // 메서드를 호출하면 원본 객체에 있는 모든 객체에 있는 모든 속성값들이 복사된 새로운 객체가 생성
    // 복사되었기에 내용(값)은 같지만, 참조는 같지 않다!
    var copied1 = p1.copy()
    println(copied1)
    println(p1 === copied1) // false

    //  메서드에 명명인자를 통해 인자값을 전달하는 방식으로 일부 속성값을 변경해서 복사 객체를 생성할 수 있다.
    var copied2 = p1.copy(name="박철수")
    var copied3 = p1.copy(age=50)
    println(copied2)
    println(copied3)

    // 여기서 Person 클래스는 두 개의 속성(name, age)을 가지고 있으므로
    // component1, component2 메서드가 생기고 각각 name, age 속성값을 반환하는 역할을 수행함
    /*
    println(p1.component1()) // 김철수
    println(p1.component2()) // 20
    */

    // 구조 분해 할당
    var (p1Name, p1Age) = p1
    /*
    var p1Name = p1.component1()
    var p1Age = p1.component2()
    */
    println(p1Name)
    println(p1Age)

    // 타입에 상관없이 두 개의 값을 저장할 수 있는 Pair 객체에 포함된 값을
    // 각 변수 (pairName, pairAge)에 대입
    var personPair = Pair("이영희", 30)
    var (pairName, pairAge) = personPair

    // Triple 객체에는 임의의 타입을 가진 3 개의 값을 저장할 수 있으므로 값을 대입 받을 변수도 3개가 필요함
    // 만약 객체의 특정 속성값만 대입해야한다면, 맨 뒤 변수 이름을 생략하거나 앞에 오는 변수의 이름을 언더 스코어(_)로 지정하면 됨
    // 하지만 _는 위치를 채우기 위한 용도로만 사용했을 뿐, 의미는 없다.
    var personTriple = Triple("박철수", 30, "경찰")
    var (tripleName, tripleAge, tripleJob) = personTriple
    // var (tripleName, tripleAge) = personTriple
    // var (_, tripleAge, tripleJob) = personTriple




    // hashCode
    println(p1.hashCode())
    println(p2.hashCode())
    println(p3.hashCode())


    var pe1 = Person2_OOP("김철수", 30)
    var pe2 = Person2_OOP("김철수", 30)
    println(pe1) // toString()를 통해 정보를 출력할 때, 주생성자에 없는 직업에 대한 내용은 제외되어 출력됨

    // 이름과 나이는 같고 직업 정보는 다른 객체를 생성했지만,
    // 직업 내용을 비교하지 않아서 내용이 같은 객체라교 판별함
    pe1.job = "공무원"
    pe2.job = "회사원"
    // 직업 정보가 다르지만 true
    println(pe1 == pe2)

    // copy()를 통해 객체를 복제해도 직업정보는 복제되지 않음
    var pe4 = pe1.copy()
    println(pe4.job) // "Unknown" 출력 (copy할 때 name, age만 복사)

    // component3()은 생성도 되지않고 호출도 불가능.
    // var pe1Job = pe1.component3()

    var p = PersonNotData("박영호", 18)
    val (pAge, pName) = p
    println(pAge)
    println(pName)

}