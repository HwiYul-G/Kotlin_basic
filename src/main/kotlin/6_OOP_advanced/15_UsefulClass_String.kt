// 문자열 클래스

fun main(args: Array<String>) {
    var str1 = "Hello"
    println(str1.length) // 문자열의 길이 반환
    println(str1[0]) // index를 통해 특정 위치의 글자에 접근

    // indexOf()를 통해 인자로 전달한 글자나 문자열이 시작되는 위치를 반환 받음
    // 만약 해당 글자나 문자열이 없으면 -1이 나옴
    val idx1 = str1.indexOf("H")
    val idx2 = str1.indexOf("l")
    val idx3 = str1.indexOf("a")
    val idx4 = str1.indexOf("el")
    println(idx1) // 0
    println(idx2) // 2
    println(idx3) // 'a'가 없으므로 -1이 나옴
    println(idx4) // 1

    // contains() : 특정 문자나 문자열이 포함되어 있는 지 여부 반환
    println(str1.contains('a')) // false
    println(str1.contains("el")) // true

    // startsWith 메서드를 통해 특정 접두어로 시작하는지
    // endsWith 메서드를 통해 접미어로 끝나는지 여부를 확읺 수 있다.
    println(str1.startsWith("He")) // He로 시작하면 true
    println(str1.endsWith("lo")) // true

    // replace()를 통해 문자열 내용의 일부를 치환함
    var replaced = str1.replace("He", "Je")
    println(replaced) // Jello로 바뀜

    // substring()을 통해 문자열의 일부 내용을 추출해 새 문자열을 반환받음
    var sub = str1.substring(0..3)
    println(sub) // Hell

    // split()를 통해 특정 구분자(delimiter)를 기준으로 문자열을 자른 뒤
    // 문자열 리스트를 반환 받을 수 있다.
    var splitted: List<String> = "Hello,World,Kotlin".split(",")
    for (s in splitted) {
        // 순서대로 "Hello", "World", "Kotlin" 출력
        println(s)
    }

    // joinToString()을 통해 특정 구분자를 이용해 리스트에 포함된 문자열을 이어붙인 문자열을 반환받을 수 있다.
    var joined = listOf("Hello", "World", "Kotlin").joinToString(",")
    println(joined)

    // 문자열 양쪽에 포함된 공백을 제거하기 위해서는 이름이 trim()메서드 이용
    val target1 = " \t\nHello\n\t "
    val result1 = target1.trim() // 문자열 양쪽의 공백을 제거한 "Hello" 반환
    val result2 = target1.trimStart() // "Hello\n\t "
    val result3 = target1.trimEnd() // " \t\nHello"

    println("${result1.length}")
    println("${result2.length}")
    println("${result3.length}")

    // 문자열 양쪽이 아닌 중간에 포함된 공백을 제거하고 싶다면 replace() 를 활용해
    val spaceRemoved = "H e l l o".replace(" ", "") // space bar를 ""으로
    println(spaceRemoved)

    // isEmpty() : 문자열의 길이가 0인 내용이 없는 문자열인지 여부를 조사
    // isBlanck() : 공백 문자열(스페이스, 탭, 엔터)만 포함된 문자열인지 여부 체크
    val empty = ""
    println(empty.isEmpty())
    println(empty.isBlank())

    val blank = " \t\n"
    println(blank.isEmpty()) // 문자열의 길이가 0이 아니므로 false
    println(blank.isBlank()) // ture

    // 문자열 내용이 없거나 빈 문자열로 구성되어 있을 때 특정 코드를 실행해야 한다면
    // ifEmpty, ifBlank 같은 '함수'를 전달받는 메서드
    blank.ifEmpty { println("비어있는 문자열입니다.") }
    blank.ifBlank { println("내용이 없는 문자열입니다.") }

    // trimMargin()
    // : trim()과 같이 앞뒥 공백을 제거하고 특정 기호 글자(|)를 기준으로
    // 해당 기호 앞에 있는 공백 문자를 제거하는 메서드
    // 인자를 전달해 기준이 될 기호 글자를 변경할 수도 있다.
    var text = """Hello
        |Kotlin
        |Language
    """.trimMargin()
    println(text)

    // 해시 기호(#)를 기준으로 공백 문자 제거
    var textWithSharp = """Hello
        #Kotlin
        #Language
    """.trimMargin("#")
    println(textWithSharp)

    // lines() : 개행 문자(enter) 기준으로 잘려진 문자열이 포함된 리스트를 반환함
    var textWithLines = "first\nsecond\nthird\n"
    val lines = textWithLines.lines()
    for(line in lines){
        println(line)
    }

    // 문자열에 내용을 추가하거나 수정하는 작업을 빈번하게 진행하는 경우
    // StringBuffer 객체를 활용함
    // 내부적으로 중간 결과를 저장하기 위한 메모리 공간(버퍼)을 이용해서 문자열 수정 작업을 진행하므로 효율적임
    val sb = StringBuffer()
    sb.append("Hello")
    sb.append(" World!")
    println(sb)

    // 특정 위치에 내용을 삽입
    sb.insert(0, "Kotlin ")
    println(sb)

    // 특정 범위에 포함된 내용을 삭제
    sb.delete(0,7)
    println(sb)

    println(sb.length)

    // String(문자열)로 반환
    val bufferToStr = sb.toString()
}