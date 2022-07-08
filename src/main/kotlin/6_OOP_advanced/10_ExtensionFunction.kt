/*
    확장 함수 (Extension function)
    : 상속이 금지된 클래스에 새로운 함수를 추가해서 기능을 확장하고 싶을 때 사용.
*/

// 상속이 금지된 문자열 클래스(String)에 새로운 함수를 추가
// 함수명 앞에 해당 함수를 추가할 클래스의 이름을 함께 지정해서 추가함
// 클래스의 메서드와 마찬가지로 함수 내부에서 this 객체를 통해 문자열 객체에 접근
fun String.removeAllSpace() : String{
    return this.replace(" ","")
}

// 전달받을 매개변수도 자유롭게 추가할 수 있다.
fun String.sayHelloTo(to:String, times: Int){
    for(i in 1.. times){
        println("${this}가 ${to}에게 인사!")
    }
}

// 직접 정의한 상속이 불가능한 클래스에 확작 함수를 추가
class ExtenededFunctionClass(var x : Int, private var y : String){
    fun func(){
        println("function inside class")
    }

    private fun privateFUnc(){
        println("private function inside class")
    }


    // 확장 함수와 동일한 이름을 가진 메서드 정의
    // 만약 아래의 주석을 제외하게 되면, 클래스 내부에 정의된 메서드의 호출 우선순위가 더 높아서
    // 여기 있는 함수가 호출됨.
    /*
    fun myFunction(){
        println("from original method")
    }
    */
}

fun ExtenededFunctionClass.myFunction(){
    println("from extended function")
    //접근 허용
    func()

    // 접근 불가
    // privateFunc()
    // println(y)
}


fun main(){
    var spaceRemoved = " H E L L O ! ! !".removeAllSpace()
    println(spaceRemoved)
    "만복".sayHelloTo("만식",2)

    var extended = ExtenededFunctionClass(10, "Hello")
    extended.myFunction()
}

/*
    확장 함수 내부에서 private 속성과 메서드에 접근이 불가능한 이유를 알려면,
    코틀린 코드를 디컴파일한 자바 코드를 보면 됨

    // 클래스에 확장 함수를 정의했다고 해서 실제로 클래스에 매서드가 추가되는 것은 아님
    // 내부적으로 확장 함수와 같은 이름을 가진 정적 메서드가 정의되고 이 메서드에 객체를 인자값으로 전달해
    public static final void myFunction(@NotNull ExtendedFunctionClass $this$myFunction){
        Intrinsics.checkParameterIsNotNull($this$myFunction, "$this$myFunction");
        String var1 = "from extended function";
        boolean var2 = false;
        System.out.println(var1);

        // 객체 속성이나 메서드에 접근함
        $this$myFunction.func();
        int var3 = $this$myFunction.getX();
        var2 = false;
        System.out.println(var3);
    }

    즉 실제로 클래스 내부에 접근하지 않고,
    정적 메서드로 전달된 객체를 통해 속성과 메서드에 접근하므로 private로 선언된, 외부로 노출되지 않은 속성과
    메서드에는 접근이 불가능하기 때문이다.
*/
