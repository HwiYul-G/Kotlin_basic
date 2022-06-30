// 안전한 형 변환을 위한 as? 키워드
fun main(){
    // as 키워드와 as? 키워드(safe cast)의 차이
    var value : Any = "String"

    // as 키워드로 타입 변환
    // 원래 타입과 변환할 타입이 일치하지 않아 타입 변환에 실패하면
    // CalssCastException 예외가 발생함
    var intValue1 = value as Int

    // as? 키워드로 타입 변환
    // 타입 변환에 실패하면 예외가 밸상하는 대신 결괏값으로 null 값을 반환
    var intValue2 = value as? Int
    println(intValue2)
}