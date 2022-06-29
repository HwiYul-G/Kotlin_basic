import java.util.Random

// 속성을 초기화할 때 많은 계산 작업이 필요하거나 많은 메모리 자원이 소모되고,
// 조건에 따라 해당 속성값이 사용되지 않을 수 있다면,
// "실제로 해당 값을 사용하는 시점에 값을 초기화"하는 편이 더 합리적이다.

// 특히 분기문을 통해 속성에 접근하는 상황이라면 조건이 만족되지 않는 경우에는
// 속성에 접근할 필요가 없기 때문에 속성값을 바로 평가하지 않고 실제로 사용되는 시점에서 가져오게 하는 것이 좋다.

// lazy 키워드 : 속성에 접근하는 시점에 값의 초기화 작업이 이뤄지게 함.(속성값 지연 초기화)
class LazyClass(var x: Int) {
    // lazy 키워드를 통해 lazyValue1 값이 늦게 초기화 되도록 함
    val lazyValue1 by lazy {
        // 이 값을 최초로 읽어오는 시점에서
        // 코드 블록 내부의 일을 처리하고
        // 마지막 줄에서 평가된식(toLowerCase().trim()을 호출해서
        // 앞뒤 공백이 사라진 소문자 문자열이 결괏값으로 대입되어서 값을 초기화함
        println("lazy 람다식 내부에서 속성값 초기화 진행")
        var s = " H E L L O "
        s.toLowerCase().trim()

        // 그 이후로는 최초로 대입받은 값을 계속 반환하는 '상수'가 됨
        // 따라서 lazy 키워드를 짖한 속성은 val 키워드를 지정해 상수로 만들어야함.
    }

    // 클래스 내부의 속성값을 이용해서 값을 초기화 하는 것도 가능하다.
    val lazyValue2 by lazy {
        // lazy 키워드를 사용했으므로 호출되는 시점에서 초기화를 시작하고
        // 마지막 값인 x*2가 대입된다.
        // lazy 키워드를 사용했으므로 val로 지정
        x * 2
    }

    // lazyValue1, lazyValue2 속성의 경우 변수를 초기화하는 작업이 오래 소모되지 않으므로
    // 늦게 초기화 한다고 장점이 있지는 않다.
    // 그러나 아래와 같은 속성의 경우 초기화 시간이 오래 걸리고 메모리를 많이 필요로하므로 lazy 키워드를 지정해 늦게 초기화 하는 편이 더 합리적이다.
    val costHeavyProperty by lazy {
        println("시간이 오래 걸리고 메모리 사용량이 많은 속성값 초기화 진행")
        Thread.sleep(1000)
        Array<Byte>(1024 * 1024 + 100) { 0 }
    }
}
// == lateinit ==
// lateinit은 null 허용 타입으로 선언하고 싶지 않은 속성이 있으나
// 당장 값을 대입하기 어려운 상황에서 나중에 해당 속성에 접근하기 전까지는
// 속성값이 초기화 됐음을 보장하고 싶은 속성을 선언할 때 사용하는 키워드
// 특히 lateinit 키워드는 안드로이드 프로그램 작성하면서 액티비티 클래스 내부의 속성을 선언할 때 자주 사용
class PropertyObject{
    fun func(){
        println("from PropertyObject Function")
    }
}

class LateInitClass{
    // lateinit은 primitive 타입을 지원하지 않음
    // 따라서 Byte, Short, Int, Long, Float, Double, Char, Boolean 타입값은 lateinit을 이용한 늦은 초기화 불가능
    // lateinit은 lazy 키워드를 지정한 속성과 달리 '변수'에만 적용할 수 있으므로
    // var 키워드를 붙여 변수로 선언해야한다.
    // lateinit var a : Int // 'lateinit' modifier is not allowed on primitive type properties
    lateinit var obj : PropertyObject

    fun initMyObject(value: PropertyObject){
        //obj 객체를 초기화하는 메서드
        // 이 메서드가 호출되지 않은 상황(즉, 값이 초기화되지 않은 시점)에
        // useMyObject() 를 호출하려 한다면
        // 객체의 메서드를 호출하려는 시점에 UninitializedPropertyAccessException 예외가 발생한다.
        obj = value
    }

    fun useMyObject(){
        //3
        obj.func()
    }
}
fun main(args: Array<String>) {
    var lazyClass = LazyClass(10)

    // lazyValue1 속성에 처음으로 접근하는 시점에서 블록 내부의 코드가 실행되어 출력되는 문자열
    println(lazyClass.lazyValue1)

    // 속성값을 출력하며 속성에 접근하지만
    // 이 시저메서는 속성값이 이미 초기화된 상태이므로 블록 내부의 값을 실행하지 않음
    println(lazyClass.lazyValue1)
    println(lazyClass.lazyValue1)

    println(lazyClass.lazyValue2)

    // 조건부로 실행되는 코드
    // 따라서 해당 조건을 만족해서 실제로 접근할 때 값을 초기화하는 것이 더 합리적
    if(Random().nextBoolean()){
        println(lazyClass.costHeavyProperty)
    }

    //lateInit
    var lateInitClass = LateInitClass()
    // 속성값이 초기화 되지 않은 상황에서 객체에 접근해 메서드를 호출했으므로
    // UninitializedPropertyAccessException 발생
    // lateInitClass.useMyObject()

    // 값의 초기화가 이뤄졌으므로 이 코드 이후부터는
    // 안전하게 해당 객체를 사용하는 메서드 호출이 가능함
    lateInitClass.initMyObject((PropertyObject()))
    // 안전하게 메서드 호출 가능
    lateInitClass.useMyObject()

    // 즉! lateinit 키워드는 값을 사용하기 이전에 속성값의 초기화가 반드시 이뤄질 것임을
    // 프로그래머가 보장할 수 있을 때 사용하는 키워드임.

    /* 자바 코드로 변환된 obj 속성에 접근하는 게터 메서드(getObj)의 내용
    lateinit으로 선언한 속성의 초깃값 대입이 이뤄지지 않은 상황에서
    값에 접근할 때 떻게 예오를 발생시키는 지 확인할 수 있다.
    @NotNull
    public final PropertyObject getObj(){
        PropertyObject var10000 = this.obj;

        // 내부적으로 null 값 여부를 확인하고 예외를 발생시키는 방식으로 동작함
        if(this.obj == null){
            Intrinsics.throwUninitializedPropertyAccessException("obj");
        }
        return var10000;
    }
    * */
}
