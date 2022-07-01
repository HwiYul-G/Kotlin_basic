/*
    data class에서 제공하는 copy 메서드는 shallow copy(얕은 복사)에서 본 것처럼
    복사본의 내용을 변경했을 시에 원본에도 변경 내용이 적용되는 부작용이 유발될 수 있다.

    따라서, 참조 복사가 아닌 새로운 객체를 생성해서 서로 독립적으로 데이터를 유지하려면
    새 참조 객체를 생성해서 복사를 진행하는 깊은 복사(deep copy)를 직접 수행해야 한다.

    1) 깊은 복사를 수행하는 메서드를 직접 정의하는 방법
    2) 복사 생성자를 제공하는 방법
*/

data class PersonForDeepCopy(var name: String, var age: Int, var favorites: MutableList<String>) {
    // 1) 깊은 복사를 수행하는 메서드
    fun deepCopy(
        name: String? = null,
        age: Int? = null,
        favorites: MutableList<String>? = null
    ): PersonForDeepCopy {
        val f = if (favorites == null) {
            // 새 리스트 객체 생성
            val deepCopiedList = mutableListOf<String>()
            // 직접 모든 내용을 복사한 후 생성한 객체를 반환
            for (fav in this.favorites) {
                deepCopiedList.add(fav)
            }
            // 새 객체가 생성됐으므로 참조가 달라짐
            // 참조가 달라져 복사된 객체의 값을 변경해도 원본 리스트에 영향을 끼치지 않음
            deepCopiedList
        } else {
            favorites
        }

        return PersonForDeepCopy(name ?: this.name, age ?: this.age, f)
    }

    // C++에서 제공하는 복사 생성자와 비슷한 역할을 수행하도록
    // 복사할 객체를 전달받는 보조 생성자로 정의해서 생성자 내부에서 값 복사를 수행함
    constructor(other: PersonForDeepCopy) : this(other.name, other.age, other.favorites) {
        // toMutableList() 호출하면 내부적으로 깊은 복사를 수행한 리스트를 반환함
        this.favorites = favorites.toMutableList()
    }
}

fun main(args: Array<String>) {
    val pd = PersonForDeepCopy("김철수", 20, mutableListOf("게임", "독서", "요리"))
    // 깊은 복사 메서드를 호출해서 복사한 객체를 반환받음
    val deepCopied = pd.deepCopy()
    // 아래와 같이 복사 생성자를 이용해도 결과는 동일함
    // val deepCopied = PersonForDeepCopy(dp)

    // 깊은 복사를 수행했으므로 두 객체는 서로 독립된 상태를 유지함
    println(pd.favorites === deepCopied.favorites) //false
    deepCopied.favorites[0] = "수영"
    deepCopied.favorites.add("등산")

    println(pd)
    println(deepCopied)
}

