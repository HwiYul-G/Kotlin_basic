package `4_function`

// 함수 : 특정 목적을 달성하는데 필요한 일련의 코드를 모아놓은 코드 집합
// 결괏값을 반환하지 않아도 되지만, 입력에 따른 결괏값을 반환하기 위해 사용
// 프로시져(Procedure) : 결괏값을 반환하지 않는 함수
// 함수는 한 번 작성하고 계속 호출해서 사용할 수 있으므로 재사용성이 뛰어나고
// 복잡한 문제를 여러 작은 문제로 나눠서 해결할 수 있게 도움.

// main 함수 : 프로그램이 시작될 때 호출되는 함수, '엔트리 포인트 함수'

// built-in 함수 : 어떤 유용한 기능을 수행하도록 미리 정의해 놓은 함수
// ex) print(), println(), readLine() 등

// 함수 정의
// fun 함수이름(이름1:타입1, 이름2:타입2, ... , 이름n : 타입n) : 반환값 타입{.. return 반환값}

// argument(인자) vs 매개변수(parameter)
// 인자는 함수로 전달하는 실질적인 값을 의미한다.
// sum(1, 2) 의 1,2는 인자이다.
// fun sum (a : Int,b : Int) : Int{} 에서 함수로 전달되는 값을 받을 상수 a, b는 매개변수(파라미터)이다.

fun printHello(){
    println("Hello")
    return // 이 함수의 경우 생력해도 됨.
}

fun printHelloTo(to : String){
    println("Hello ${to}")
}

fun getHello(): String{
    return "Hello"
}

fun main(args:Array<String>) {
    printHello() // 함수 호출
    printHelloTo("Pororo")

    var getHelloVar = getHello()
    println(getHelloVar)
}