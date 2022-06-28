package `2_operator`

fun main() {
    // in 연산자
    // 1. 배열, 리스트, 집합 혹은 범위(Range) 객체처럼 여러 값이 저장된 객체에 특정 값이 포함돼 있는 지 검사
    var arr1 = arrayOf(1,2,3,4,5)
    println("3 in arr1 : " + (3 in arr1)) // true 반환
    println("6 in arr1 : " + (6 in arr1)) // false 반환

    var list1 = listOf('a', 'b', 'c', 'd', 'e')
    println(" 'a' in list : ${'a' in list1}") // true
    println(" 'f' in linst : ${'f' in list1}") // false

    // 2. for 구문 내부에서 배열, 리스트, 집합 혹은 범위 객체와 같이 여러 값이 저장된 객체에 포함된 값을 하나씩 순회
    // .. 연산자를 이앵효 1이상 10이하까지의 범위 정보를 담고 있는 범위 객체 생성
    println(1 in 1..10)
    // 소문자 알파벳에 'a'가 포함되어 있는지 여부를 검사
    println('a' in 'a'..'z')
    println(1 !in 1.. 10)

    // 반복문과 in 연산자
    for(num in 1.. 5)
        print("$num")
    println()

    var items = arrayOf('a', 'b', 'c')
    for(item in items)
        print(item)
    println()

    // withIndex()를 이용해 요소가 포함된 위치(idx) 정보를 함께 가져와 출력함함
   for((idx, item) in items.withIndex())
        println("$idx : $item")
}