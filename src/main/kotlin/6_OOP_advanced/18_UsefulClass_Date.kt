// 날짜와 관련된 작업을 진행하기 위해
// 날짜 정보가 필요할 때 사용하는 LocalDate 클리스와
// 날짜와 시간 정보가 모두 포함된 LocalDateTime 클래스를 사용
import java.time.LocalDate
import java.time.LocalDateTime
// 날짜 조작과 관련된 추가 클래스
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.TimeZone

// 각 클래스에서 제공하는 now 메서드를 호출하면
// 현재 시간에 대한 날짜, 시간 정보를 담은 객체를 반환함.
fun main(args: Array<String>) {
    // LocalDate는 날짜 정보만을 필요할 때 사용
    var now : LocalDate = LocalDate.now()
    println(now)
    println(now.year) // 년
    println(now.month) // 월
    println(now.dayOfMonth) // 일

    // LocalDateTime은 날짜와 시간 정보가 동시에 필요할 때 사용
    var nowWithTime = LocalDateTime.now()
    println(nowWithTime.hour)
    println(nowWithTime.minute)
    println(nowWithTime.second)

    // now 함수를 호출할 때 기본 시간대(TimeZone)를 기준으로 작동하는 객체 반환
    // 만약 다른 시간대를 이용해 객체를 반환받고 싶다면
    // now 함수를 호출할 때 시간대 정보를 포함한 ZoneId 객체를 전달함

    // 기본 시간대 확인
    println(TimeZone.getDefault().toZoneId())
    // 협정 세계표준시(UTC)를 시간대로 사용해 객체를 반환
    var nowInUTC = LocalDateTime.now(ZoneId.of("UTC"))
    // 한국 시간대에서 9시간 전의 시간이 출력됨
    println("nowUTC : ${nowInUTC}")

    // 여기선 기본 시간대가 서울을 기준으로 한 아시아 시간대(Asia/Seoul)로 설정된 것을 볼 수 있다.
    // 해당 시간대는 세계 협정시를 기준으로 9시간이 더해진 시간대이므로
    // 세계 협정시를 기준으로 한 날짜 객체를 출력하면
    // 출력 시점에서 9시간 전의 시간이 출력되는 것을 확인할 수 있다.


    // 날짜 클래스에서 제공하는 parse 함수를 사용해
    // 날짜 형식의 문자열을 날짜 객체로 변환할 수 있다.
    var date = LocalDate.parse("2022-07-09")
    println(date)

    // 시간 정보까지 포함한 문자열을 날짜/시간 객체로 변환
    var dateTime = LocalDateTime.parse("2022-07-09T22:50:12")
    println(dateTime)

    // 날짜를 원하는 양식으로 출력하기 위해
    // DateTimeFormatter 객체를 사용할 수 있다.
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
    // 반환값은 문자열
    val formatted : String = nowWithTime.format(formatter)
    println(formatted)

    // DateTimeFormatter 객체를 parse 메서드에 전달하면
    // 해당 날짜 양식에 맞게 표기된 문자열을 날짜 객체로 return
    val dateTime2 = LocalDateTime.parse("2022-07-07 10:53:23",formatter)
    println(dateTime2)
}