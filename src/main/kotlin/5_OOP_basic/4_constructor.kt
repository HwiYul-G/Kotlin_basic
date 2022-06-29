// 보조 생성자 : 188 - 195p
// 주 생성자를 통해 필수 정보를 초기화할 수 있게 하면서
// 동시에 직업, 연봉 같은 추가 정보를 초기화할 수 있는 생성자를 정의하기 위해 생김

// 추가적인 인자값을 전달받아 객체를 초기화할 방법을 제공하고 싶을 때는
// 보조생성자(secondary constructor)를 정의함
/* constructor(초기화에 필요한 인자 목록):
    this(주 생성자or 다른 보조 생성자에 필요한인자){ // 초기화 코드}

    보조 생성자에서는 객체의 속성값을 정의할 수 없다. 오직 전달 받는 용도로 ㄴ사용 가능
    따라서, 중괄호 내에서 전달받은 인자값을 대입하거나 가공해 필요한 속성값을 초기화하는 작업을 끝내야함

    만약 클래스에 주 생성자가 있으면, 보조 생성자는 반드시 직접 주 생성자를 호출하거나
    주 생성자를 호출하는 다른 보조 생성자를 호출해서 간접적으로 주 생성자를 호출해야함

    this 키워드는 주 생성자 혹은 다른 보조 생성자를 나타내는 용도로 사용됨
* */

// MyClass는 주 생성자가 없다. 따라서 보조 생성자 정의시 주 생성자를 호출할 필요 없음
class MyClass {
    // 보조 생성자 정의
    // 아무 값도 전달받지 않는 보조 생성자를 정의하므로 빈 괄호만 씀
    constructor() {
        println("from constructor")
    }

    // 또 다른 보조 생성자 정의
    // 인자를 전달받음, this() 키워드를 통해 앞에 정의한 보조 생성자를 호출함
    constructor(arg: Int) : this() {
        println("from constructor with arg($arg)")
    }
}

// 클래스를 정의하며 주 생성자도 함게 정의
// 주 생성자에서 전달받는 값이 하나도 없어도, 보조생성자에서 인자를 받으므로
// 이 경우 엄연히 주 생성자가 존재하므로 보조 생성자에서 반드시 주 생성자를 호출해야함
class MyClassWithPrimaryConstructor() {
    // this 키워드를 이용해 주 생성자를 호출
    constructor(arg: Int) : this() {
        println("from constructor with arg($arg)")
    }

    //인자를 두개 받는 또다른 보조 생성자 정의
    // this 키워드를 이용해 앞에서 정의한 보조생성자를 호출
    /// 그럼 this 키워드를 통해 주생성자의 호출도 간접적으로 이어짐
    constructor(arg1: Int, arg2: Int) : this(arg1) {}
    // constructor(arg1:Int, arg2:Int) : this() // 이렇게 호출해도 됨
}

// 주 생성자 정의
class Person5(var name: String, var age: Int, val gender: String) {
    var job: String = "Unkown"
    var salary: Int? = null

    // 보조 생성자를 통해 job 정보 추가 및 this로 주 생성자 호출
    constructor(name: String, age: Int, gender: String, job: String) : this(name, age, gender) {
        println("from constructor(name:String, age: Int, gender: String, job : String)")
        this.job = job
    }

    // 또 다른 보조 생성자러 salary 정보 추가 및 다른 보조 생성자 호출로 간접적으로 주 생성자 호출
    constructor(name: String, age: Int, gender: String, job: String, salary: Int) : this(name, age, gender, job) {
        println("from constructor(name : String, age:Int, gender: String, job:String, salary: Int")
        this.salary = salary
    }
}
/* 생성자에서 전달받는 매개변수의 개수와 타입이 모두 같을 경우
    생성자를 호출할 때 어떤 생성자를 호출해야할지 모호해지는 생황이 발생함
    constructor(name : String, age:Int, gender:String, job : String) : this(name, age, gender){
        println("from constructor(name : String, age: Int, gender: String, job : String)")
        this.job = job
    }

    // 위에서 생성한 생성자와 인자의 타입과 순서가 완전히 동앨해
    // Confliciting overloads 에러가 발생한다.
    constructor(name : String, age:Int, gender:String, job : String) : this(name, age, gender){
        println("from constructor(name : String, age: Int, gender: String, job : String)")
        this.age = age / 2
    }

    // 따라서 보조 생성자를 추가해서 정의할 때는
    // 인자값을 전달받는 방식을 다르게(인자값을 전달받는 순서, 매개변수의 개수, 타입)해서 생성자를 받아야 한다.
*/

// 주생성자, init, 보조 생성자 코드 실행 순서 체크해보기
class ConstructorCallOrderDemo(a: Int) {
    // 주 생성자의 대입 작업이 끝난 후 init 블록이 실행됨
    init {
        println("init")
    }

    // 보조 생성자는 init 블록 이후에 실행됨
    constructor(a: Int, b: Int) : this(a) {
        println("constructor(a:Int, b:Int)")
    }

    constructor(a: Int, b: Int, c: Int) : this(a, b) {
        println("constructor(a:Int, b:Int, c:Int)")
    }
}
// 주 생성자의 기본값 설정
class Person6(var name: String, var age: Int, val gender: String, var job : String = " Unknown", var salary:Int? = null){}
// 단, 보조 생성자에는 속성값 초기화와 관련된 코드를 작성할 수는 있찌만
// 주 생성자에는 코드 작성이 불가능하다.
// 이러한 경우 init 코드 블록을 이용할 수 있으므로 보조 생성자가 특별히 필요하지 않다면
// 주 생성자와 init 블록으로 초기화 하는 방법이 권장됨.
fun main() {
    // 주 생성자를 통해 객체 생성
    // job, salary는 각각 Unknown, null로 초기화 됨
    var p5 = Person5("김철수", 20, "남성")
    println("${p5.name}, ${p5.age}, ${p5.job}, ${p5.salary}")

    // 직업을 전달받는 보조 생성자를 통해 객체 생성
    p5 = Person5("이영희", 30, "여성", "의사")
    println("${p5.name}, ${p5.age}, ${p5.job}, ${p5.salary}")

    // 직업과 연봉을 전달받는 보조 생성자를 통해 객체 생성
    // 직업을 생성하는 보조 생성자를 호출하고
    // 그 과정에서 주 생성자도 호출함
    // 가장 먼저 주 생성자 -> 직업생성 보조 생성자 -> 마지막 본인(연봉생성)
    p5 = Person5("이영희", 30, "여성", "의사", 2000)
    println("${p5.name}, ${p5.age}, ${p5.job}, ${p5.salary}")

    // 코드 생성 순서 체크
    ConstructorCallOrderDemo(1,2,3)
}