// 접근제어자 199p - 204p
/* 접근 제어자(visibility modifier) 
: 클래스에 포함된 속성이나 메서드에 접근할 수 있는 자격 조건을 지정
public  어떤 영역이든 자유롭게 접근 가능(기본값)
protected 해당 클래스를 상속받는 자식 클래스에서 접근 가능
internal 같은 모듈에 정의돼 있는 경우 접근 가능
private 해당 클래스 내부에서만 접근 가능

internal 접근제어자는 jar 파일과 같은 라이브러리 파일을 생성할 때,
해당 라이브러리의 모듈 내부에서만 접근할 수 있도록 강제할 함수 및 클래스를 정의할 때 사용
*/

// 접근 제어자를 생략했으므로 클래서의 접근 제어자는 public 이다.
// PublicClass는 어디서나 자유롭게 접근할 수 있다.
// 여기서 "open이라는 키워드는 상속이 가능함을 정의"하기 위해 사용하는 키워드이다.
// public open class PublicClass{} 로 해도 동일하다
open class PublicClass
// 주 생성자에서 정의한 속성에 접근 제어자를 설정할 수 있다.
// privateProp1은 private 이므로 클래스 내부에서만 접근 가능하다.
    (var publicProp1 : String, private var privateProp1 : String)
{
    // 클래스 중괄호 내부에 정의된 속성도 접근 제어자를 설정할 수 있다.
    var publicProp2 = "public prop2"
    private var privateProp2 = "public prop2"
    // 클래스를 상속받는 자식 클래스에서 접근할 수 있다.
    protected var protectedProp = "protected"

    // 접근지정자가 없으면 public 이므로 어디서나 접근 가능하다.
    fun publicFunc(){
        println(publicProp1)
        // private 속성갑이어도 정의된 클래스 내부이므로 접근 가능
        println(privateProp1)
        privateFunc()

        // 본인과 본인을 상속받는 클래스에서 접근가능한 protected
        println(protectedProp)
        protectedFunc()
    }

    private fun privateFunc() = println("from private function")
    protected fun protectedFunc() = println("from protected function")
}
// private 접근 제어자로 외부 파일에서 접근할 수 없는 클래스를 정의함
// 이 클래스는 해당 클래스가 정의된 파일 내부에서만 접근 가능하다.
private class PrivateClass

/* 접근 제어자를 이용한 '변수, 상수, 함수' 정의
public : 어떤 영역이든 자유롭게 접근 가능
internal : 같은 모듈에 정의돼 있는 경우 접근 가능
private : *같은 파일 내*에서만 접근 가능
*/
fun publicFunc() = println("from public function")
// private 변수, 상수, 함수 중 함수이므로 같은 파일에서만 사용 가능
private fun privateFunc() = println("from private function")

var publicVariable = "public variable"
private val privateConstant = "private constant"

// 상속 받은 클래스에서 부모 클래스의 protected 속성과 메서드에 접근하는 예제
class Extender(prop1: String, prop2 : String): PublicClass(prop1, prop2){
    fun parentAcessDemo(){
        // PublicClasss를 상속받고 있는 클래스이므로
        // PublicClass의 protected로 지정된 속성과 메서드에 접근 가능
        println(protectedProp)
        protectedFunc()

        // 부모 클래스인 PublicClass의 private은 접근 불가능
        //println(privateProp1)
        // privateFunc()
    }
}

fun main(){
    // publicClass는 어디서든 자유롭게 생성가능
    // private class는 해당 클래스가 정의된 같은 파일 내부에서만 객체 생성
    var publicClass = PublicClass("Hello","World")
    val privateClass = PrivateClass()

    // class 내부에서 public으로 선언된 속성, 메서드에 자유롭게 접근
    println(publicClass.publicProp1)
    println(publicClass.publicFunc())
    // class 내부에서 private으로 선언된 속성, 메서드는 클래스 내부에서만 접근가능
    // Cannot access 'privateProp1': it is private in 'PublicClass'
    // println(publicClass.privateProp1)
    // println(publicClass.protectedFunc())
    // println(publicClass.privateFunc())

    // private 접근 제어자가 지정되어 있어도 같은 파일에서 접근하므로 문제 없음음
    publicFunc()
    privateFunc()

    println(publicVariable)
    println(privateConstant)
}