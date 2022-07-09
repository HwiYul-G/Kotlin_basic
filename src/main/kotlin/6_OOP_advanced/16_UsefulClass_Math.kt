
import java.math.BigDecimal
import java.math.BigInteger
import kotlin.math.*

fun main(args: Array<String>) {
    // kotlin.math 패키지 사용
    // 상수 (자연대수 e와 파이)
    println(E)
    println(PI)

    // 절댓값 함수 abs
    val minusTen = -10
    println(abs(minusTen))
    println(minusTen.absoluteValue)

    // round() : 반올림 , ceil() : 올림, floor() : 내림
    println(round(0.5))
    println(ceil(0.1))
    println(floor(0.9))

    // pow() : 제곱, sqrt() 제곱근
    val base = 2.0
    println(base.pow(3))
    println(sqrt(4.0))
    println(sqrt(2.0))

    // sin(), cos(), tan()는 라디안 단위의 값을 전달 받음
    // degree(도)를 전달하고 싶다면
    // Math 클래스에서 제공하는 toRadians()를 통해 각도를 라디안으로 바꿔서 전화해야함
    println(sin(Math.toRadians(90.0)))
    println(cos(Math.toRadians(90.0)))
    // 1이 아니라 0.999.... (약간의 오차 발생)
    println(tan(Math.toRadians(45.0)))

    // 타입 변환 메서드(toInt, toString)에 진수와 관련된 인자를 전달해서
    // 10진수를 16진수 문자열로 변환하거나 역으로 변환할 수 있음
    // 정수를 16진수 문자열로 변환
    var intToHex = 16711680.toString(16) // ff0000
    // 16진수 문자열을 정수로 변환
    var hexToInt = intToHex.toInt(16) // 16722680
    println(intToHex)
    println(hexToInt)
    // * 특정 함수나 메서드는 16진수 형태로 표현된 인자값을 전달받는 경우가 많아서 알아야함!

    // 큰 정수를 다루기 위해선 BigInteger 클래스와  - import java.math.BigDecimal
    // 정밀한 실수를 다루기 위해 BigDecimal 클래스를 사용해보자 - import java.math.BigInteger
    val bi1 = BigInteger("1000000000000000000")
    val bi2 = BigInteger("1000000000000000000")
    val bi3 = bi1 + bi2
    val bi4 = bi1 * bi2
    println(bi3)
    println(bi4)
    // 코틸린에서 기본적으로 제공하는 Int, Long같은 정수 타입의 최댓값 범위를 넘는데
    // 결괏값이 문제 없이 출력되고 있다.

    // 계산된 결과를 변환 메서드를 호출해 정수 타입으로 변환할 수 있다.
    // 하지만 변환 가정에서 뜻하지 않은 값의 유실이 일아날 수 있다.
    println(bi4.toLong())

    // Long 타입의 최대 범위는 922경 정도인데 그 값이 Long의 범위를 초과하므로
    // 정상적인 변환이 이뤄지지 않음
    // '타입이름ValueExact' 메서드를 호출해서 해당 타입의 값으로 변환하는 과정에서
    // 데이터 유실이 발생할 경우 예외가 발생하도록 유도할 수 있다.

    // 값의 유실이 발생하는 경우 예외가 발생하도록 유도
    println(bi4.longValueExact())

    // 반환할 타입의 포험되는 값으로 변환하는 것은 아무 문제 없이 수행됨
    val bi5 = bi1 - bi2
    println(bi5.longValueExact()) // 0으로 범위 안에 포함되므로 문제 없이 변환 됨

    // BigDecimal 클래스
    // : 무한한 소수점 정밀도가 요구되거나 실수형 타입의 계산 과정에서 발생할 수 있는
    // 미세한 오차를 허용하지 않는 정밀한 결괏값이 필요할 때 사용하는 클래스

    // 미세한 값의 오차가 발생 가능
    println(0.03 - 0.02)
    // BigDecimal의 경우 오차 없이 정확하게 계산을 수행
    println(BigDecimal("0.03") - BigDecimal("0.02"))

    // 계산 과정에서 미세한 값의 오차 발생 가능
    var sum = 0.0
    for (i in 0..9999) {
        sum += 0.0001
    }
    println(sum)
    
    // BigDecimal 객체를 통해 더하기 연산을 수행했을 때
    // Double 타입의 실수형 값을 계산하는 과정에서 생긴 미세한 오차값이 사라진다.
    var sumBd = BigDecimal("0.0")
    for(i in 0..9999){
        sumBd += BigDecimal("0.0001")
    }
    println(sumBd)

    // 단 BigInteger, BigDecimal 객체를 이용해 연산할 경우
    // 일반적 정수나 실수 연산보다 더 많은 계산량을 필요로 한다.
    // 따라서 큰 수 혹은 정확한 정밀도가 바드시 필요한 상황에서만 사용한다.
}