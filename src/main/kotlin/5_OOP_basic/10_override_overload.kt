// 메서드 오버라이드 221-223 , 메서드오버리동 224
/* method override(메서드 오버라이드)
: 부모 클래스에 정의된 메서드의 작동방식을 변경하는 행위
: 부모 클래스의 메서드를 재정의(override) 한다고 함

단, 상속과 마찬가지로 부모 클래스의 메서드를 정의할 때 fun 키워드 앞에
open 키워드를 지정해서 해당 메서드를 자식 클래스에서 재정의(override) 할 수 있게 허용해야 한다.
또한, 자식 클래스에서는 재정의(override)할 때 메서드 fun 키워드 앞에 override 키워드를 지정해
메서드를 재정의하고 있음을 명시해야 한다.
*/

open class Parent2() {
    // open 키워드를 함수 앞에 지정하지 않아서 자식 클래스에서 override(재정의) 불가
    fun parentFunc() {
        println("from parentFunc")
    }

    // open 키워드가 있으므로 자식 키워드에서 override(재정의) 가능
    open fun overridableParentFunc() {
        println("from overridableParentFunc")
    }

    open fun overridableParentFuncWithArg(arg1: Int, arg2: String) {
        println("from overridableParentFuncWithArg(${arg1}, ${arg2})")
    }
}

class Child2 : Parent2() {
    // 재정의 불가
    // 'parentFunc' in 'Parent2' is final and cannot be overridden
    /*
    override fun parentFunc(){
        println("from overrided parentFunc")
    }
    */

    // 부모 클래스의 메서드를 override함
    // 따라서 override 키로를 적음
    override fun overridableParentFunc() {
        println("from overrided overridableParentFunc")
    }

    //간혹 부모 코드에 정의된 메서드를 실행한 후에 추가로 코드를 작성해야하는 경우
    // super 키워드를 이용해 부모 클래스의 메서드를 호출하고
    // 자식 클래스에서 수행ㅎ 코드를 추가로 작성함
    override fun overridableParentFuncWithArg(arg1: Int, arg2: String) {
        super.overridableParentFuncWithArg(arg1, arg2)
        println("from overrided overridableParentFuncWithArg(${arg1}, ${arg2})")
    }
}

/* 메서드 오버로딩
: 클래스에 정의된 메서드도 인자값의 개수나 전달 순서 혹은 타입을 달리해서 오버로딩할 수있다.
*/
class MethodOverloadingClass{
    // 오버로딩된 메소드
    fun overloadingTest() = println("overloading test 1")
    fun overloadingTest(x:Int) = println("overloading test 2")
    fun overloadingTest(x:Int,y:Int) = println("overloading test 3")
    fun overloadingTest(x:Int,y:Double) = println("overloading test 4")
    fun overloadingTest(x:Double,y:Int) = println("overloading test 5")
}

/* 안드로이드에서 제공하는 Intent 클래스에 포함된 putExtra 메서드
이 메서드는 여러 타입의 인자를 전달할 수 있게 오버로딩돼 있다.
public Intent putExtra(String name, Parcelable value){}
public Intent putExtra(String name, int value){}
public Intent putExtra(String name, String value){}
위의 코드는 자바로 작성되었지만 오버로딩 개념은 똑같이 적용된다.
메서드 오버로딩을 적용해서 2번째로 전달할 매개변수의 타입을 모두 드라게 정의한 것을 볼 수 있다.
*/
fun main(args: Array<String>) {
    var p = Parent2()
    p.parentFunc()
    p.overridableParentFunc()
    p.overridableParentFuncWithArg(100, "Hello")

    var c = Child2()
    c.parentFunc()
    c.overridableParentFunc()
    c.overridableParentFuncWithArg(200, "World")
}