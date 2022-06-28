// 생성자 182p - 187p
/* 생성자
    : 클래스에 필요한 객체를 생성하는 과정에서 필요한 작업을 수행하고
    필요한 속성의 초기값을 설정하기 위해 사용하는 특수한 메서드
* */

class Person1{
    // 적절한 초기값을 대입
    var name : String = "홍길동"
    var age : Int = 0
    // 현재 이 클래스의 경우 생성자가 정의되어 있지 않고, 속성값 자체에 바로 적절한 초깃값을 대입함
}

fun main(){
    var p1 = Person1()
    println("${p1.name} : ${p1.age}")

    p1.name = "김철수"
    p1.age = 20
    println("${p1.name} : ${p1.age}")

}
