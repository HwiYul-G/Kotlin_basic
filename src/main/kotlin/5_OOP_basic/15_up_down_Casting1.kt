/* 업캐스팅과 다운캐스팅
upcasting : 자식 클래스를 부모 클래스의 타입으로 변환하는 캐스팅
downcasting : 부모 클래스를 자식 클래스 타입으로 변환하는 캐스팅

14_에 있는 A, B, C 클래스를 이용
B 클래스는 A 클래스의 자식 클래스, C 클래스는 A 클래스의 자식 클래스
*/

fun main() {
    var b = B()
    var c = C()

    // B, C 클래스 타입의 객체를 부모 클래스 타입인 A 타입으로 변환하는 upcasting
    // as 키워드를 이용해 명시적으로 수행
    var upCasted1WithAsKeyword = b as A
    var upCasted2WithAsKeyword = c as A

    // upCasting의 경우 as 키워드가 생략 가능(단, 변수 타입에 캐스팅할 상위 타입을 명시해야 함)
    // 변수 타입이 생략된 경우(:A) 추론에 의해 본래 타입(B, C)로 설정되므로 실질적 변환이 이뤄지지 않음.
    var upCasted1: A = b
    var upCasted2: A = c

    println(upCasted1.a)
    upCasted1.aMethod()
    // 원래 객체 타입이 B 클래스이지만 현재는 A 클래스 타입의 변수에 객체가 저장되어 있다.
    // 따라서 B 클래스에 정의된 속성(b)에 접근하거나 메서드(bMethod)를 호출할 수 없다
    // println(upCasted1.b)
    // upCasted1.bMethod()

    upCasted2.aMethod()
    // println(upCasted2.c)
    // upCasted2.cMethod()

    //========다운 캐스팅 =======
    // upCasting으로 상위 타입으로 변환한 객체를 다운 캐스팅으로 원래 타입으로 변환
    // 다운캐스팅은 반드시 as 키워드를 사용해 "명시적"으로 형ㅂㄴ환을 수행해야함
    var downCasted1 = upCasted1 as B
    var downCasted2 = upCasted2 as C

    // 다운캐스팅으로 원래 클래스 타입으로 변환이 이루어졌으므로
    // 클래스에 정의된 고유 속성과 메서드에 접근 간으함
    println(downCasted1.b)
    downCasted1.bMethod()

    println(downCasted2.c)
    downCasted2.cMethod()

    // 추상 클래스를 상속받은 클래스의 객체를 업캐스팅
    // 추상클래스인 phone을 상속받는 클래스의 객체를 생성
    var galaxyPhone = GalaxyS()
    var v30Phone = V30()

    // 추상 클래스 타입으로 업캐스팅
    var phone1: Phone = galaxyPhone
    var phone2: Phone = v30Phone

    // 실제로 실행되는 메서드는 각자 재정의(override) 된 메서드
    phone1.turnOn()
    phone2.turnOn()

    // interface를 구현한 클래스의 객체를 업캐스팅
    var rectangle = Rectangle(10.0,20.0)
    var circle = Circle(10.0)

    // 인터페이스 타입으로 업캐스팅
    var shape1 : Shape = rectangle
    var shape2 : Shape = circle

    // 실제로 실행되는 메서드는 각자 재정의된 메서드
    println(shape1.calculateArea())
    println(shape1.calculatePerimeter())

    println(shape2.calculateArea())
    println(shape2.calculatePerimeter())
    /* 업캐스팅을 수행한 후 부모 타입의 객체를 통해 메서드를 호출해도
    구현 클래스에서 메서드를 재정의했다면 재정의된 메서드가 실행됨.
   */
}
