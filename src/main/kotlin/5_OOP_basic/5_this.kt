// class 내부에서 this 키워드를 이용해 자기 자신을 가리키는 객체에 접근할 수 있다.
class ThisRefDemoClass {
    var prop: Int

    constructor(prop: Int) {
        // 생성자에서 전달받은 매개변수 이름과 클래스의 속성명이 같으면 error
        // 예를 들면 prop = prop 같은 경우
        // 이 문제를 해결하기 위해 이름을 다르게 prop = _prop 로 이름을 다르게 하거나
        // this 키워드를 이용함
        this.prop = prop
    }

    fun myFunc() {
        // this 객체를 출력함
        println("$this")
        // this 키워드를 이용해 속성값에 접근한 것과 없는 것은 동일함
        println("${this.prop}")
        println("${prop}")
    }

    fun anotherFunc() {
        // 여기서도 this 없이 접근해도 동일함
        this.myFunc()
    }
}

fun main(){
    var t1 = ThisRefDemoClass(100)
    t1.anotherFunc()

    var t2 = ThisRefDemoClass(200)
    t2.anotherFunc()

    // 위의 코드의 결과 값을 보면
    // this 객체를 출력했을 때,
// // class가 속한 패키지와 클래스 이름이 나오고 @ 뒤로 임의의 16진수 값이 출력된다
    // 이 16진소 기호는 객체를 식별할 수 있는 일종의 고윳값이다.
}