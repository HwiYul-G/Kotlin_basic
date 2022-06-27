package `2_operator`

fun main() {
    // index 접근 연산자는 배열 혹은 리스트 같이 여러 개의 값을 저장할 수 있는 객체에
    // 값을 저장하거나 변경하고 읽어올 때 사용하는 연산자
    // 배열, 리스트가 저장된 변수명의 오른쪽에 []를 쓰고 그 안에 위치를 나타내는 숫자를 써서 그 값에 접근
    var nums = arrayOf(100, 200, 300)
    println("nums[0] : ${nums[0]}")
    println("nums[1] : " + nums[1])
    println("nums[2] : " + nums[2])

    nums[1] = 400
    println(nums[1])
    // 현재 배열의 최대 인덱스 범위인 2를 넘는 값을 이용해 접근하면
    // ArrayIndexOutOfBoundsException 오류가 발생한다.

    // 인덱스 접근을 위해 반드시 숫자만 사용하는 것은 아니다.
    // map 처럼 키를 통해 값에 접근하는 객체에서는 [] 안에 키 값을 적어 그 키에 대응하는 값을 참조할 수 있다.
    var areaCodeMap = mutableMapOf("02" to "서울", "031" to "경기")
    println(areaCodeMap["02"])
    println(areaCodeMap["031"])
    areaCodeMap["051"] = "부산"
    println(areaCodeMap["051"])


}