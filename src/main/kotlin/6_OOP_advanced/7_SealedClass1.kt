/*
    봉인(sealed) 클래스는 추상 클래스와 비슷하게 상속 과정을 통해 완성될 수 있는 클래스를 정의함과 동시에
    해당 클래스를 상속받는 클래스의 개수를 제한하고 싶을 때 사용하는 클래스

    sealed class 클래스명{}
*/

// sealed 키워드를 사용하는 봉인 클래스는
// 추상 클래스처럼 추상 메서드를 포함할 수 있다.
sealed class SealedParent{
    abstract fun abstractMethod()
}

// SealedParent 클래스와 같은 파일에 자식 클래스를 정의
// sealedParent를 상속받는 자식 클래스는 봉인 클래스를 정의한 파일에만 정의할 수 있다.
// 그렇지 않으면, 상속이 불가능하다.
class SealedChild1 : SealedParent(){
    override fun abstractMethod() = println("from child1")
}
class SealedChild2 : SealedParent(){
    override fun abstractMethod() = println("from child2")
}
class SealedChild3 : SealedParent(){
    override fun abstractMethod() = println("from child3")
}

fun main(args: Array<String>){
    // 추상클래스와 마찬가지로 봉인 클래스 객체를 생성할 수 없다.
    // 클래스 상속에 제약을 두므로 익명 클래스 형태로 객체를 생성할 수도 없다.
    // 오직 해당 봉인 클래스를 상속받은 자식 클래스를 통해서만 객체 생성이 가능하다.
    // val parent = SealedParent() // 봉인 클래스 객체 생성 불가

    // 앞에서 배운 열거형 클래스의 객체는 모두 상숫값처럼 취급되므로 개별적으로 객체를 생성할 수 없고 그럴 필요도 없다.
    // 하지만 봉인 클래스를 상속받은 클래스를 통해서는 객체를 원하는 만큼 생성할 수 있다.
    val child1 = SealedChild1()
    val child2 = SealedChild2()
    val child3 = SealedChild3()

    // 봉인 클래스는 특히 when - case 표현시고가 함께 사용할 때 진가를 발휘함
    // 업캐스팅을 통해 자식 타입의 객체를 부모 타입의 객체로 변환하며 대입
    val sealedParent : SealedParent = child1
    val childType = when(sealedParent){
        // 클래스 타입에 따른 분기 진행
        is SealedChild1 -> 1
        is SealedChild2 -> 2
        is SealedChild3 -> 3
        //3
        // 모든 클래스 타입에 대한 분기문을 제공했으므로 else 분기문은 불필요
        // 하지만 인텔리 제이 상인지 규칙이 바뀌었는지 필요한 듯 하다.
    }

    // 모든 클래스 타입에 대한 분기문이 없으므로 else 필요
    val childType2 = when(sealedParent){
        is SealedChild1 ->1
        else -> 2
    }
}