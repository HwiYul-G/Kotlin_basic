/* smart cast
: 타입 확인이 끝난 시점에 자동으로 해당 타입으로 반환하는 기능
*/

fun main() {
    // 부모 클래스 타입으로 형변환(캐스팅)하면 접근 가능한 속성과 메서드가 부모 클래스 타입에 속한 속성과 메서드로 한정됨
    // 따라서 String 타입을 Any 타입으로 upcasting 한 경우 Any 타입엔 trim()가 존재하지 않아 호출이 불가능
    var strToAny: Any = "Hello"
    // strToAny.trim()

    // is 키워드를 사용해 타입을 확인하고 문자열 타입이면 블록 내부로 진입함
    if (strToAny is String) {
        // 명시적인 타입 변환(as 키워드 이용)으로 문자열 타입으로 전환 후
        // trim()를 호출함
        var s = strToAny as String
        println(s.trim())

        // 이미 is 키워드와 if 절을 이용해서 strToAny에 저장된 객체가 String 타입이라는 것을
        // 확신할 수 있는 ㅅ황
        // 따라서 곧바로 문자열 메서드인 trim()을 호출할 수 있다.
        println(strToAny.trim())
    }

    var data: Any = "String"
    // data = 1234

    var result: Any? = when (data) {
        // 조건 비교 구문에서 is 키워드를 이용해 특정 타입인지 비교함 (자바의 instanceOf)
        // 코드 블록에서는 스마트 캐스트를 통해 자동 형변환이 이뤄지므로 고유 메서드를 곧바로 호출할 수 있다.
        is String -> data.toLowerCase()
        is Int -> data.inc()
        else -> null
    }
    println(result)
}

/* 스마트 캐스팅의 내부 동작 방식
형변환 작업을 수행하는 코드를 자동으로 삽입한다.

자바 코드로 디컴파일 하면 아래와 같다.
Object str = "Hello";
String data;
if(str instanceOf String){
    // 타입검사 이후 명시적인 형변환 코드가 자동으로 삽입됨
    data = (String)str; // upcasting
    data = StringKt.trim((CharSequence)data).toString();
    System.out.println(data);
}
*/
