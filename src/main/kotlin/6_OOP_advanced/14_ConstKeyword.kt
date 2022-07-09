
/*
    companion object 블록 내부에 상수를 정의할 때
    const 쿼드와 @JvmField 애네테이션을 이용하면 내부적으로 상수에 접근하는 방식을 바꿀 수 있다.
*/

class Person_14_Test(name:String, age : Int){

}

class ConstDemo{
    companion object{
        // 아래 3가지 방법 모두 상수를 정의하기 위해 사용되지만
        // 내부적인 접근 방식이 조금씩 다름
        val CONSTANT_VAL1 = "constant value1"
        const val CONSTANT_VAL2 = "constant value2"
        @JvmField val CONSTANT_VAL3 = "constant value3"

        
        val CONSTANT_PERSON1 = Person_14_Test("김철수1",10)
        @JvmField val CONSTANT_PERSON2 = Person_14_Test("김철수2", 20)
        // const val 로 정의한 상수의 경우 기본 타입과 문자열만 대입 허용
        // const val CONSTANT_PERSON = Person("김철수", 20) // 기본타입, 문자열 아니므로 불가능
    }
}

fun main(args : Array<String>) {
    // 상수에 접근하는 코드
    println(ConstDemo.CONSTANT_VAL1)
    println(ConstDemo.CONSTANT_VAL2)
    println(ConstDemo.CONSTANT_VAL3)

    println(ConstDemo.CONSTANT_PERSON1)
    println(ConstDemo.CONSTANT_PERSON2)
}
/*
    코틀린에서 상수를 접근하는 코드를 보면 접근 방식에 차이가 없어 보임
    자바 코드로 디컴파일된 코드를 보면 각 상수에 대한 접근 방식이 다른 것을 볼 수 있음

    // val 키워드를 통해 정의한 상수의 경우 getter 이용해 접근
    String var1 = ConstDemo.Companion.getCONSTANT_VAL1();
    System.out.println(var1);

    // const val의 경우 실제로 상수에 접근하지 않고 값 자체를 그대로 치환하는 방식
    var1 = "constant value 2";
    System.out.println(val1);

    // @JvmField 애너테이션 : 클래스에 정의된 상수에 직접 접근
    var1 = ConstDemo.CONSTANT_VAL3;
    System.out.println(val1);

    // val은 getter 이용, const val은 값 자체를 그대로 치환환
    Peson var3 = ConstDemo.Companion.getCONSTANT_PERSON1();
    System.out.println(var3);
    var3 = ConstDemo.CONSTANT_PERSON2;
    System.out.println(var3);

    어느것이 더 나은 방식인지에 대한 답은 없지만
    게터 생성과 호출에 대한 오버헤드를 줄이기 위핸 @JvmField 애너테이션을 통해 접근할 수 있다.
    @JvmField 애너테이션은 게터, 세터를 통해 값에 접근하지 않고 직접 접근하도록 유도하기 위해 사용
*/

class JvmFieldAnnotationDemo{
    // JvmField 애너테이션이 붙은 속성은 게터, 세터 없이 값에 직접 접근
    @JvmField var value1 = "Hello1"
    // JvmField 애너테이션 없는 경우 게터, 세터 메서드 이용해 값에 접근
    var value2 = "Hello!"

}
/* 코틀린에서 위의 객체의 속성값에 접근하는 방법
fun main() {
    val demo = JvmFieldAnnotationDemo()
    println(demo.value1)
    println(demo.value2)
}
*/

/*
// https://blog.egorand.me/where-do-i-put-my-constants-in-kotlin/
// https://stackoverflow.com/questions/45628861/kotlin-why-the-compiler-needs-the-const-modifier
// https://medium.com/@BladeCoder/exploring-kotlins-hidden-costs-part-1-fbb9935d9b62
// https://www.baeldung.com/kotlin-jvm-field-annotation

class ConstDemo {
    // Const 'val' are only allowed on top level or in objects
    // const val constVal1 = "Hello"

    companion object {
        // https://stackoverflow.com/questions/49426456/kotlin-whats-the-reasoning-behind-const
        // 내부적으로는 게터 메소드를 통해 호출 (호출에 따르는 오버헤드 존재)
        val CONSTANT_VAL1 = "Constant 1"
        // 인라인되어 해당 상수로 치환 처리됨을 주의
        const val CONSTANT_VAL2 = "Constant 2"
        // @JvmField를 붙여서 ConstDemo 클래스에 정의된 static final 상수로 인식하도록 설정
        @JvmField val CONSTANT_VAL3 = "Constant 3"

        // const val 로 정의한 상수의 경우 기본 타입과 문자열만 대입 허용
        // Const 'val' has type 'Person'. Only primitives and String are allowed 에러 발생
        // const val COSNTANT_PERSON = Person("김철수", 20)
        @JvmField val COSNTANT_PERSON = Person("김철수", 20)

        // 컴파일 시점에 값이 정해져야 하므로 런타임에 실행되는 함수(혹은 메소드) 호출 반환값을 이용한 초기화는 불가
        // Const 'val' initializer should be a constant value 에러 발생
        // const val CONSTANT_VAL4 = getNum()
    }
}


fun main(args : Array<String>) {
    println(ConstDemo.CONSTANT_VAL1)
    println(ConstDemo.CONSTANT_VAL2)
    println(ConstDemo.CONSTANT_VAL3)
    println(ConstDemo.COSNTANT_PERSON)

    val demo = JvmFieldAnnotationDemo()
    println(demo.value1)
    println(demo.value2)
    println(demo.value3)
}
*/