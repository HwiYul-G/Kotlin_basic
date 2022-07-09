// 342 - 345
/*
    시스템 클래스
    : 프로그래밍이 실행되는 환경에 대한 정보를 제공하는 메서드
    + 유용하게 사용될 여러 메서드를 제공함

    그중 currentTimeMillis() : 현재 시간을 에포크 시간(epoch time)으로 반환

    * 에포크 시간 : 협정 세계시(UTC)를 기준으로 1970년 1월 1일 9시부터 경과된 시간을 초단위로 나타낸 시간
    단, currentTimeMillis()를 통해 반환된 값은 밀리초 단위 값이며
    일반적으로 초 단위 값보다 밀리초 단위 값을 사용

    큰 정수를 저장해야하므로 currentTImeMillis() 리턴 값은 Long 타입이다.

    import java.time.Instant
    import java.time.LocalDateTime
    import java.time.ZoneId
    import kotlin.system.exitProcess

*/
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import kotlin.system.exitProcess

fun main(args: Array<String>) {
    // 에포크 시간 반환
    val millis = System.currentTimeMillis()
    println(millis)

    // LocalDateTime 클래스의 ofInstant 함수에
    // currentTimeMillis()를 호출해서 전달받은 에포크 시간을 ofEpochMilli()에 전달해
    // 해당 에포크 시간과 관련된 Instant 객체를 반환받고 +
    // 세계 표준시 기준 시간 정보를 담은 ZoneId 객체를 전달해서 시간을 반환 받을 수 있음.
    val dateTimeFromMillis = LocalDateTime.ofInstant(
        Instant.ofEpochMilli(millis),
        ZoneId.of("Asia/Seoul")
    )
    println(dateTimeFromMillis)

    // getProperties()를 호출해서 프로그램이 실행되고 있는 환경에 대한 정보(JUM버전, CPU, 운영체제 정보)를 파악할 수 있다.
    // 정보는 키와 값으로 구분되어 제공되며,
    // getProperties()에 키 문자열을 전달하면 해당 키에 대응하는 값이 반환됨.
    println("System properties")
    val props = System.getProperties()
    for (prop in props) {
        println("key : ${prop.key}\nvalue : ${prop.value}\n")
    }
    // 직접 키를 전달해서 값을 반환
    println(System.getProperty("os.name"))

    // getenv()를 호출해서 os에서 사용하는 환경변수를 파악할 수 있다.
    // 정보는 키와 값으로 구분되어 제공됨
    println("System environments")
    val envs = System.getenv()
    for (env in envs) {
        println("key : ${env.key}\nvalue : ${env.value}\n")
    }
    // window os 기준으로 환경변수를 확인하거나 추가하려면
    // '시스템 환경 변수 편집' 프로그램을 이용함.

    // exitProcess 함수
    // 프로그램은 보통 main 함수가 종료ㅚ는 시점에 종료된다.
    // 만약 프로그램 중간에 명시적으로 종료해야할 경우 exitProcess()를 호출
    // 이 함수는 내부적으로 System class의 exit()를 호출함
    // exitProcess()에 전달흔 인자는 에러 코드를 나타냄
    // 0을 전달하면 정상적으로 프로그램이 종료되는 것이다.
    exitProcess(0)
    // 정상 종료가 아닌 경우 1~255 범위의 임의의 숫자를 전달해서
    // 어떤 문제가 발생했는 지 알릴 수 있다.
}