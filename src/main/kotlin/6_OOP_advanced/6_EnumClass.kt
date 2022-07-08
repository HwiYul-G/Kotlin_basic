import kotlin.random.Random

/*
    열거형(enumerate) 클래스
    : 밀접하게 관련된 여러 개의 상숫값을 정의하기 위해 사용하는 클래스
    enum class 클래스명{
        A, B, C
    }
    클래스명을 통해 접근할 수 있는 A,B,C 상수를 정의함
*/

enum class Color{
    RED, GREEN, BLUE
}

// 난이도와 관련된 상수를 정의한 열거형 클래스
enum class Difficulty{
    EASY, STANDARD, HARD
}
// 방향과 관려된 상수를 정의한 열거형 클래스
enum class Direction{
    EAST, WEST, SOUTH, NORTH
}
// 열거형 클래스는 값의 범위에 제한이 있을 때만 정의할 수 있다.
// 정수, 실수 같은 숫자는 값의 범위에 한계가 없어서 열거형 클래스로 정의하긴 부적합


// 코틀린에선 열거형 클래스에 필요한 속성과 메서드를 정의할 수 있게 허용함
// 열거형 클래스의 주 생성자로 키보드 입력 및 단축키의 설명 정보를 저달받을 수 있게 하고 단축키 정보를 담은 상숫값을 정의
enum class Shortcut(val key : String, val description : String){
    // 열거형 클래스의 중괄호 블록 내부에서만 열거형 타입의 객체 생성이 허용됨!
    // val shortcut = ShortCut("Ctrl + Z", "설명")
    // 위와 같은 방식으로 주 생성자를 직접 호출해서 객체를 생성할 수 없다.
    UNDO("Ctrl + Z", "가장 최근에 실행한 명령어를 취소합니다."),
    REDO("Ctrl + Y", "가장 최근에 취소된 명령어를 다시 실행합니다.")

}

// 위는 속성을 정의한 것
// 이번에는 열거형 클래스에 메서드를 추가해 볼 것
// 게임 랭크 정보와 관련된 열거형 클래스를 정의하는 예제
// 랭크에 따라 게임에 승리했을 때 얻을 점숫값을 저장할 속성(point)과
// 랭크 이름과 점수를 Pair 타입으로 반환하는 메서드인 getRanckAndPoint() 정의
enum class GameRank(val point : Int){
    BRONZE(10),SILVER(50),GOLD(100);

    // 랭크와 관련된 이름과 포인트 값을 반환하는 메서드
    fun getRankAndPoint() = this.name to point
}
// 열거형 클래스는 클래스를 상속받지 못하지만, 인터페이스를 구현할 수는 있다.
// GameRank 클래스와 같은 역할을 수행하는 열거형 클래스를 인터페이스를 구현하는 방식으로 정의해보기
interface RankAndPointInterface{
    fun getRankANdPoint() : Pair<String, Int>
}
enum class GameRank2 : RankAndPointInterface{
    BRONZE{// 추상메서드를 재정의해서 메서드를 구현함
        override fun getRankANdPoint() = this.name to 10
    },
    SILVER{
        override fun getRankANdPoint() = this.name to 50
    },
    GOLD{
        override fun getRankANdPoint() = this.name to Random.nextInt(100,200)
    }
}



fun main(){
    // Color 타입의 변수 color1에 열거형 클래스 값을 대입
    // 열커형 클래스도 클래스이므로 값의 타입은 값이 정의된 열거형 클래스의 타입과 같다.
    var color1 : Color = Color.RED
    var difficulty1 : Difficulty = Difficulty.EASY
    var direction = Direction.SOUTH

    // 문자열 값을 이용해 열거형 상수에 접근해야 하는 상황이라면 열거형 클래스에서 제공하는 valueOf()를 이용
    var color2 = Color.valueOf("GREEN")
    // var difficulty2 = Dificculty.HARD 와 아래의 결과는 동일함
    var difficulty2 = Difficulty.valueOf("HARD")

    // 열거형 클래스에서 제공하는 name, ordinal 속성에 접근하는 예제
    // name 속성 : 상수의 이름이 문자열 형태로 저장됨
    // ordinal 속성 : 상수가 정의된 순서가 저장됨 (순서는 0부터 시작)
    println(color1.name) // 상수 이름인 RED가 출력됨
    println(direction.name) // 상수 이름인 SOUTH 가 출력됨

    println(color1.ordinal)
    println(color2.ordinal)

    // 열겨형 클래스에서 제공하는 values 메서드를 호출하면 열거형 클래스에 포함된 모든 상숫값이 담긴 배열을 반호나함
    // 이 배열을 순회에서 모든 상숫값을 조회할 수 있음
    for(color in Color.values()){
        println("ordinal : ${color.ordinal}, name : ${color.name}")
    }

    // 코틀린에선 열거형 클래스에 필요한 속성ㅅ과 메서드를 정의할 수 있게 허용함
    // Shortcut 열거형 클래스 타입 객체의 속성값을 출력하는 예제
    var undo = Shortcut.UNDO
    println(undo.key)
    println(undo.description)

    //

}
/*
    열거형 클래스를 사용해 상숫값을 정의하지 않고, 숫자나 문자열 타입의 상수를 사용하는 것도 가능하며,
    구현성 특별히 문제가 발생하지 않음
    다만, 코드의 가독성을 높이고 열거형 클래스의 타입 확인을 통해 대입 실수를 방지할 수 있으므로
        상숫값을 정의해야 할 상황이 오면 열거형 클래스를 쓰는 것도 고려해야함

    다음은 열거형 클래스 대신 임의의 숫자 상수를 정의해 값을 대입하는 상황에서 발생하는 문제를 보여준느 예제
    val RED = 1; var GREEN =2 ; var BLUE = 3
    var color = RED
    color = 4; // color에 정의되지 않은 값을 대입하는 실수를 발생할 가능성이 있음.
*/