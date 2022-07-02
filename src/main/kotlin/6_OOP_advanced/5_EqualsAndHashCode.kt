/*
    equals()와 hashCode() 메서드 재정의(override)
    equals() : 객체가 논리적으로 동등한지를 비교할 수 있도록 재정의하는 메서드

    data class를 정의하면 기본적으로 eqauls()와 hashCode()가 재정의됨

    equals()와 hashCode()가 재정의되지 않은 클래스를 사용하기 위해 일반 클래스를 사용
*/

import java.time.LocalDate

// equals, hashCode 메서드를 재정의 하지 않은 클래스 정의
class PersonOverrideNothing(var name: String, var age: Int, val birthDate: LocalDate)

// equals, hashCOde 메서드를 재정의할 클래스 정의
class PersonOverrideEqualsAndHashCode(var name: String, var age: Int, val birthDate: LocalDate) {
    // 재정의한 동등 비교 메서드로 비교할 대상값 other을 전달받음
    // 비교할 대상값 other은 Any?타입으로 null값을 포함한 어떤 타입도 허용
    override fun equals(other: Any?): Boolean {
        // 참조 비교 연산자(===)로 참조가 같은지 비교
        // 참조가 같다면 같은 메모리 주소를 점유하는 완전히 동일한 객체이므로 true
        if (this === other) return true
        // 비교할 대상과 같은 클래스인지 여부를 비교
        // 같은 클래스가 아니라면 같은 내용을 가진 객체일리 없으므로 바로 false
        if (javaClass != other?.javaClass) return false

        // 위의 비교과정으로 클래스가 같다는 것을 확신할 수 있으므로 형변환으로 동등한 클래스로 캐스팅
        other as PersonOverrideEqualsAndHashCode

        // 메서드를 생성하는 과정에서 추가한 모든 속성을 비교해 값이 동일한지 여부 확인
        if (name != other.name) return false
        if (age != other.age) return false
        if (birthDate != other.birthDate) return false

        // 모든 속성값이 동일하다면 true를 반환
        return true
    }

    // 메서드를 생성하는 과정에서 추가한 모든 속성을 이용해 하나의 ㅈ어숫값을 반환하도록 구현됨
    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + age
        result = 31 * result + birthDate.hashCode()
        return result
    }
}

//만약 equals()만 override하고 hashCode()를 재정의하지 않으면
class PersonOverrideEquals(var name: String, var age: Int, val birthDate: LocalDate) {
    // hashCode 재정의를 생략하고 equals()만 재정의
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as PersonOverrideEquals

        if (name != other.name) return false
        if (age != other.age) return false
        if (birthDate != other.birthDate) return false

        return true
    }
}

fun main() {
    val p1 = PersonOverrideNothing("김철수", 20, LocalDate.parse("2000-01-01"))
    val p2 = PersonOverrideNothing("김철수", 20, LocalDate.parse("2000-01-01"))

    // 매서드를 재정의하지 않은 클래스 객체를 동등비교
    // Any 클래스를 통해 상속받은 equals 메서드를 사용
    /*
        자바의 Object 클래스(코틀린의 Any 클래스)에 정의된 equals 메서드 구현 코드
        equals 메서드의 기본 구현 코드에서는 내부적으로 참조 비교 연산자를 통해 동등 여부를 판단하도록 구현되어 있음
        public boolean equals(Object obj){
            return (this == obj); // 자바에서 ==은 동등 비교가 아닌 참조비교 연산자
        }
    */
    println(p1 == p2) // false

    // ===은 코틀린에서 참조비교 연산자이므로 서로 다른 메모리 주소를 참조하는 다른 객체이므로 메서드 재정의 여부와 무관히거짓값을 출력함
    println(p1 === p2) //false

    // 기본 구현된 hashCode 메서드는 객체의 참조 주소를 반환화도록 구현돼 있으므로 서로 다른 정수값을 ㄱ자ㅣㅁ
    // 둘이 다른 hashCode를 가짐
    println(p1.hashCode())
    println(p2.hashCode())

    val p3 = PersonOverrideEqualsAndHashCode("김철수", 20, LocalDate.parse("2000-01-01"))
    val p4 = PersonOverrideEqualsAndHashCode("김철수", 20, LocalDate.parse("2000-01-01"))

    // equals()를 재정의해서 객체 속성값을 모두 비교하도록 구현했고,
    // 두 객체의 모든 속성값이 같으므로 참이 출력됨
    println(p3 == p4) // true

    // 다른 객체이므로 다른 메모리 주소 참조
    println(p3 === p4) // false

    // hashCode 메서드를 재정의해서 속성값을 이용해 정숫값을 생성하게 함
    // 둘이 다른 객체이지만 속성값이 모두 동일하므로 같은 정숫값을 반환함
    // 둘이 같은 hashCode를 가짐
    println(p3.hashCode())
    println(p4.hashCode())

    // equals, hashCode 메서드를 재정의하지 않은 클래스의 객체를 키로 사용해 저장
    val map1 = mutableMapOf<PersonOverrideNothing, Int>()
    map1.put(p1, 1000)
    map1.put(p2, 2000)
    println(map1.size)
    println(map1.get(p1))
    println(map1.get(p2))
    /*
        위의 경우, 어떤 메서드도 오버라이딩하지 않은 PersonOverrideNothing 타입을 키로 사용하는 맵 객체이다
        맵의 특성상 중복된 키를 허용하지 않기 때문에, 키 값이 동등한지 여부를 빅하기 위해
        내부적으로 equals()와 hashCode()를 사용한다.

        그런데 앞에서 본 것처럼 hashCode()가 반환한 정수값도 다르고 동등 비교도 거짓값을 반호나하므로
        서로 같은 내용을 가진 객체를 키로 전달했음에도 서로 다른 키로 인식하는 문제가 일어남.
        결국 p1, p2를 객체 각각의 키로 인식하고 서로 다른 연관값을 저장하게 되어 최종적으로 맵 객체의 크기는 2가 됨
    */

    val map2 = mutableMapOf<PersonOverrideEqualsAndHashCode, Int>()
    map2.put(p3, 1000)
    map2.put(p4, 2000)
    println(map2.size)
    println(map2.get(p3))
    println(map2.get(p4))
    /*
        위의 경우는 클래스의 메서드를 재정의하여 같은 키로 인식하므로,
        최종 맵 객체의 크기는 1이고 접근한 값 역시 모두 2000이 출력된다.
    */

    // 컬렉션에서 제공하는 집합 객체를 사용할 때도 이 문제는 동일하게 일어난다.
    // 집합 객체는 기본적으로 중복된 값 삽입을 허용하지 않는다.
    // 여기서 중복의 의미는 동등비교를 했을 때 참이 반환된다.

    // 같은 요소의 중복을 허용하지 않는 집합 객체 생성
    val set1 = mutableSetOf(p1)
    set1.add(p2)
    // p1과 p2는 동등 비교의 결과로 거짓값을 반환하므로
    // 서로 중복인 값이 아니라고 판단하고 집합에 포함되어 size가 2로 출력됨
    println(set1.size)

    val set2 = mutableSetOf(p3)
    // 여기서 p3, p4가 동등 비교의 결과로 참을 반환하므로 서로 중복인 값이라고 판단해서 p4를 집합에 포함시키지 않음
    set2.add(p4)
    println(set2.size) // 1이 출력됨

    val p5 = PersonOverrideEquals("김철수", 20, LocalDate.parse("2000-01-01"))
    val p6 = PersonOverrideEquals("김철수", 20, LocalDate.parse("2000-01-01"))

    // equals()를 override 했으므로 true를 반환
    println(p5 == p5)

    // hashCode()를 재정의하지 않았으므로 다른 정숫값을 반환함
    println(p5.hashCode())
    println(p6.hashCode())

    // 객체를 키로 이용해 각각 다른 값을 저장했으므로 값이 덮어 쓰이기를 기대하지만
    // 어떤 PersonOverrideNothing 객체를 가지고 한 것과 동일한 결과가 나옴.
    val map3 = mutableMapOf<PersonOverrideEquals, Int>()
    map3.put(p5, 1000)
    map3.put(p6, 2000)
    println(map3.size)
    println(map3.get(p5))
    println(map3.get(p6))

    val set3 = mutableSetOf(p5)
    set3.add(p6)
    println(set3.size)
    // 위와 같은 결과가 나오는 이유는
    // 성능상의 이유로 두 객체의 내용이 같은지를 비교하기 위해서 내부적으로 hashCode 값을 먼저 비교
    // 그 값이 같은 경우에만 equals()를 호출하도록 구현돼어 있기 때문이다.
    // 만약 두 객체의 hashCode 값이 다르면 두 객체는 동등한 객체가 아니라고 확신할 수 있으므로
    // 추가로 계산과정이 복잡한 equals()를 호출할 필요가 없다.
    // 단, hashCode값이 동일하다면 두 객체가 동등할 확률이 높지만 반드시 같은 객체라 할 순 없다.
    // 따라서, hashCode값이 같으면 equals()를 호출해 속성값을 모두 비교하는 복잡한 과정을 거친다.

    /*
        equals와 hashCode 메서드 구현과 관련한 계약이 있다.
        1) (equals()의 호출 반호나값이 참값이므로) 만약 두 객체가 동등하다면 반드시 hashCode()를 호출해서 반환받은 정숫값을 같아야한다.
        2) 만약 hashCode() 를 호출해서 반환받은 정숫값이 같더라도, 두 객체가 반드시 동등하다고 할 수는 없다.
        즉 두 객체가 동등하지 않아도 같은 정숫값이 반환될 수 있다.

        따라서 동등조건 비교를 위해 equals()를 재정의했다면 반드시 hashCode()도 재정의 해야한다.
        ** equals()를 재정의할 때 사용한 속성은 모두 hashCode 메서드를 재저으이하는 과정에서 사용돼야한다.
    */
}