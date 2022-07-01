/*
    data class를 통해 제공되는 copy는 얕은복사(shallow copy)를 수행해
    속성값을 복사한다.
*/

data class PersonForShallowCopy(var name: String, var age: Int, var favorites: MutableList<String>)
// 데이터 클래스객체를 복사하는 과정에서 생기는 문제점 살펴보기

fun main() {
    val p = PersonForShallowCopy("김철수", 20, mutableListOf("게임", "독서", "요리"))
    val copied = p.copy()

    // 기본적으로 참조 복사가 진행되어 모두 같은 참조를 가리키므로 모두 true
    println(p.name === copied.name) // true
    println(p.age === copied.age) // true
    println(p.favorites === copied.favorites) // true
    println("p : ${p}")
    println("copied : ${copied}")

    // 이름과 나이 변경
    copied.name = "이영희"
    copied.age = 30

    // 리스트의 정보 수정 및 추가
    copied.favorites[0] = "수영"
    copied.favorites.add("등산")

    // 이름과 나이는 복사된 객체에서만 수정한 내용으로 변경됨
    // 하지만 취미 정보는 복사된 객체를 통해 수정한 내용과 추가한 내용이
    // 복사된 객체(copied)와 원본 객체(p)에도 영향을 미침
    println(p.name === copied.name) // false
    println(p.age === copied.age) // false
    println(p.favorites === copied.favorites) // true
    println("p : ${p}")
    println("copied : ${copied}")
/*
    기본 타입 및 문자열 타입 값 대입은 원본에 영향을 끼치지 않지만,
    다른 타입(리스트, 맵 같은 컬렉션 타입)의 값은 원본에도 영향을 미칠 수 있으므로 주의 필요
*/


    // 만약 리스트, 맵과 같은 객체를 사용해 객체의 내용을 변경하되
    // 원본에 영향을 끼치지 않도록 하려면
    // 새로운 객체를 만들어 대입한 후 내용을 추가해야한다.
    copied.favorites = mutableListOf<String>()
    copied.favorites.add("수영")
    copied.favorites.add("등산")

    // 별개의 리스트 데이터로 취급되어 false가 뜨는 것을 확인할 수 있다.
    println(p.name === copied.name) //false
    println(p.age === copied.age) // false
    println(p.favorites === copied.favorites) //false
    println("p : ${p}")
    println("copied : ${copied}")
}