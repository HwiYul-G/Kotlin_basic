package `1_variable`

fun main(args:Array<String>){
    // 특정 타입의 값을 다른 타입의 값으로 변환할 때 사용하는 메서드들
    var byteValue : Byte = 65
    println(byteValue)

    // Byte to Int, Short, Long, Float, Double
    var intValue : Int = byteValue.toInt()
    println(intValue)
    var shortValue : Short = byteValue.toShort()
    println(shortValue)
    var longValue : Long = byteValue.toLong()
    println(longValue)
    var floatValue : Float = byteValue.toFloat()
    println(floatValue)
    var doubleValue : Double = byteValue.toDouble()
    println(doubleValue)

    // Char 타입으로 변환.
    // 65는 'A'에 대응되는 숫자이므로 charValue에는 'A' 문자가 저장됨
    var charValue : Char = byteValue.toChar()
    println(charValue)

    // 위의 경우는 작은 범위의 타입에서 큰 범위의 타입으로 변환이므로 ok
    // 그 반대의 경우라면, 데이터를 담을 통이 작아져서
    // 잘못된 값(원본과 달라진 값)이 대입될 수 있음

    // Short 타입의 최대값음 32767
    var maxShortNum : Short = 32767
    // Byte 타입으로 변환 (Byte의 max는 127)
    var shortToByte : Byte = maxShortNum.toByte()
    println(shortToByte) // -1이 나옴옴

    // 하지만, 큰 타입에서 작은 타입으로 변환 시에도
    // 작은 타입의 값의 범위를 넘지 않으면 문제되지 않음
    var shortNum : Short = 127
    var shortToByteSafe : Byte = shortNum.toByte()
    println(shortToByte) // 127이 잘 나옴옴

    // 코틀린에서는 자동 타입 변환을 지원하지 않음 (자바는 지원)
    // 1. 자바
    // int intNum = 100;
    // long longNum = intNum; // 자동으로 형변환 수행
    // 2. 코틀린 : 위에서 연습한 to타입() 메서드를 사용해서 명시적으로 변환해야함

    // String to NuberType
    // 단 숫자가 아닌 내용을 포함한 문자열을 변환시 NumberFormatException error 발생
    var intString = "1234"
    var doubleString = "1234.5"
    var stringToInt = intString.toInt()
    var stringToDouble = doubleString.toDouble()
    println(stringToInt)
    println(stringToDouble)

    // 문자열을 불리언 타입으로도 변환 가능
    // 단 내용은 true 이거나 false 이여야함
    var booleanStr = "true"
    var stringToBoolean = booleanStr.toBoolean()
    println(stringToBoolean)

    // 반대로 특정 타입의 값을 문자열로 변환하려면 toString() 사용
    var testNumber = 123
    var intToString : String = testNumber.toString()
    println(intToString)

}