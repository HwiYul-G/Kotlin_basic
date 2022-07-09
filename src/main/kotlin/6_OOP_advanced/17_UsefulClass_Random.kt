// 338 - 339
import kotlin.random.Random

fun main() {
    // 정수 범위 내에서 난수를 생성(-2147483648 ~ 2147483647)
    val randomInt = Random.nextInt()
    // 0.0 부터 1.0을 제외한 실수 범위 내에서 난수를 생성(0.0 ~ 0.9999...)
    val randomDouble = Random.nextDouble()
    // 참 혹은 거짓을 반환
    val randomBoolean = Random.nextBoolean()

    println(randomInt)
    println(randomDouble)
    println(randomBoolean)

    // nextInt()를 호출할 때 추가 인자를 전달해서
    // 특정 범위에 포함된 정수만 반환받을 수 있다

    // 0부터 100까지의 범위 내에서 정수를 반환
    val randomBetweenZeroToHundred = Random.nextInt(101)
    // 1 부터 6까지 범위 내에서 정수를 반환
    val randomDiceNum = Random.nextInt(1,7)

    // 인자를 하나만 전달할 경우 0부터 해당 값을 포함하지 않은 범위 내에서 값을 반환
    // 인자를 두 개(a, b) 전달 시, a에서 b-1의 범위 값 내에서 값을 반환함.

    // 랜덤 객체에서는 난수를 생성하기 위해
    // 내부적으로 시드(seed)라고 하는 Long 타입 정수를 사용함.
    // 따라서 같은 시드 값을 사용해 생성된 랜덤 객체는
    // 난수를 반환하는 메서드를 같은 순서로 호출하면
    // 모두 같은 값을 반환함.
    val seed = 0
    val randomFromSeed1 = Random(seed)
    val randomFromSeed2 = Random(seed)

    // 같은 시드 값을 통해 생성한 후 같은 순서로 난수값을 반환한 경우
    // 모두 같은 난수를 반환
    for(i in 1..1000){
        if(randomFromSeed1.nextInt() != randomFromSeed2.nextInt()){
            println("서로 다른 난수가 생성됨")
        }
    }
    // 같은 seed 값을 사용하고 난수를 반환하는 메서드(nextInt)를 동일한 순서로 호출
    // 따라서 매번 같은 난수값이 반환되어 아무것도 출력되지 않는 것을 확인할 수 있다.

    // 만약 서로 다른 환경에 있는 컴퓨터에서 난수값을 동기화해야 할 필요가 있는 경우
    // 앞에서 본 것처럼 같은 시드값을 이용해 생선한 Random 객체를 활용할 수 있다.
}