// Quiz 클래스를 봉인 클래스로 선언했으므로
// Quize 클래스를 상속받는 클래스는 이 시점에서는 3 개로 한정됨
sealed class Quiz(val question: String)

// OX 퀴즈의 선택지는 O, X로 제한되므로 열거형 클래스를 정의
enum class AnswerType { O, X }

class OXQuiz(
    question: String,
    var answer: AnswerType
) : Quiz(question)

// N지 선다 퀴즈에선 답과 고를 수 있는 선택지 리스트(choices)를 전달받음
class MultiChoiceQuiz(
    question: String,
    val answer: String, val choices: List<String>
) : Quiz(question)

// 단답식 큊에서는 답과 유사 정답이 포함된 리스트(alternatives),
// 제출한 정답을 일부 변형해서 답과 대조할 수 있또록 도와줄
// 람다 함수 sanitize를 전달 받음
class ShortAnswerQuiz(
    question: String,
    val answer: String,
    val alternatives: List<String>?,
    val sanitize: (String) -> String = { it.trim() }
) : Quiz(question)

fun main() {
    // 퀴즈 타입의 리스트 생성
    val quizzes = mutableListOf<Quiz>()

    // 여러 종류의 퀴즈 객체를 생성해서 추가
    // Quiz 타입으러 업캐스팅 되서 저장됨
    quizzes.add(OXQuiz("대한민국의 수도는 서울이다.", AnswerType.O))
    quizzes.add(OXQuiz("미국의 수도는 뉴욕이다.", AnswerType.X))
    quizzes.add(MultiChoiceQuiz("대한민국의 수도는?", "서울", listOf("서울", "대전", "대구", "부산")))
    quizzes.add(ShortAnswerQuiz("사과를 뜻하는 영단어는?", "apple", null) { it.trim().toLowerCase() })
    quizzes.add(ShortAnswerQuiz("JetBrains사에서 만든 JVM 기반 프로그래밍 언어는?", "Kotlin", listOf("코틀린", "kotlin")))

    // 퀴즈 객체가 저장된 리스트를 순회회
    for (q in quizzes) {
        var question: String
        var answer: String? = null
        question = q.question

        var type = when (q) {
            // (4)
            is OXQuiz -> {
                // (5)
                answer = when (q.answer) {
                    AnswerType.O -> "O"
                    AnswerType.X -> "X"
                    // (enum 상수는 선택지 개수에 제약이 있고 모든 조건에 대한 분기문을 제공하므로) else 브랜치 불필요
                }
                "OX 퀴즈"
            }
            is MultiChoiceQuiz -> {
                answer = q.answer
                "N지선다 퀴즈"
            }
            is ShortAnswerQuiz -> {
                answer = q.answer
                "단답식 퀴즈"
            }
            // (Sealed 클래스는 선택할 수 있는 타입의 개수에 제약이 있고 모든 타입에 대한 분기문을 제공하므로) else 브랜치 불필요
            else -> {
                // 왜 인지 else가 없으면 에러 발생으로 적음
            }
        }
        println("유형 : ${type}\n질문 : ${q.question}\n답 : ${answer}")
    }
}