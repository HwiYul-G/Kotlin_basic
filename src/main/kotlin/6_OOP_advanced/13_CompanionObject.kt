//320-323
/*
    companion object 키워드를 이용한 클래스 변수, 상수, 함수 정의

    object 키워드는 클래스를 통해 생성한 객체가 아닌,
        클래스 자체에 포함될 클래스 변수나 상수, 함수를 정의하기 위해사용할 수도 있다.

*/

// 클래스를 선언하며 객체를 통해 참조할 속성과 메서드를 정의함
// 해당 속성과 메서드는 객체를 생성한 이후 개별적 객체를 통해 접근 가능
class CompanionObjectClass(var x: Int) {
    fun instanceFunction() {
        println("from instance function ${x}")
    }

    // 컴패니언 오브젝트 블록 내에 있는 것들은 클래스 이름을 통해 접근 할 수 있다.
    // 즉 접근할 때 객체를 필요로 하지 않고, 함수 내부에서 this 키워드로 객체에 접근 불가
    // 단, 함수로 전달받은 객체를 조작할 수는 있다.
    companion object {
        // 클래스 내부에 companion ojbect 키워드를 쓰고 블록 내부에 클래스 변수(or 상수)와 함수 정의
        var classVariable: Int = 100
        const val CLASS_CONSTANT: Int = 200

        fun classFunc() {
            classVariable++
            println("class variable value : $classVariable")
            println("class constant value : $CLASS_CONSTANT")
        }
    }
}

class Product(val name: String, val price: Double, var discountRate: Double) {
    // 컴패니언 오브젝트 블록 내부엔 클래스와 밀접한 관련이 있는 유용한 함수를 정의하는게 일반적
    companion object {
        // 상품 한개 가격을 계산할 것
        fun calculatePrice(product: Product): Double {
            return product.price - (product.price * product.discountRate)
        }

        // 여러 상품 가격을 계산할 것
        fun calculateTotal(products: List<Product>): Double {
            var total = 0.0
            for (p in products) total += Product.calculatePrice(p)
            return total
        }
    }
}

fun main(args: Array<String>) {
    // CompanionObjectClass 는 일반 클래스이므로 객체를 생성할 수 있고,
    // 객체와 관련된 속성과 메서드에 접근 가능
    var c = CompanionObjectClass(100)
    c.instanceFunction()
    println(c.x)

    // 객체 생성과 무관하게 클래스 함수 및 변수, 상수에 접근 가능
    CompanionObjectClass.classFunc()
    println(CompanionObjectClass.classVariable)
    println(CompanionObjectClass.CLASS_CONSTANT)

    val p1 = Product("장난감", 1000.0, 0.1)
    val p2 = Product("책", 5000.0, 0.0)
    println(Product.calculatePrice(p1))
    println(Product.calculateTotal((listOf(p1, p2))))

}