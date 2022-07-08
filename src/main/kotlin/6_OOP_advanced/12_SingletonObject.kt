
/*
    싱글턴 패턴(Singleton pattern)은 가장 빈번하게 사용되는 디자인 패턴 중 하나로
    클래스의 객체가 오직 하나만 생성되는 것을 보장하기 위해 사용하는 디자인 패턴

    코틀린에선 object 키워드를 이용해 쉽게 싱글턴 클래스를 정의할 수 잇다.
    싱글컨 패턴은 프로그램에서 객체가 전역적으로 사용되고,
    그 과정에서 하나 이상의 객체를 생성할 필요가 없다고 판단될 때 활용 됨
*/

// singleton 패턴을 적용한 클래스
// object 키워드와 클래스 이름이면 싱글턴 클래스 정의가 끝남
object SingletonClass{
    var x : Int = 0
    fun increaseX() = x++
    fun printX() = println(x)
}
// 단, 싱글턴 클래스에선 주 생성자를 포함해서 어떤 생성자도 정의할 수 없다.
// 생상저가 존재하는 이유는 개별적으로 존재할 여러 객체를 생성하기 위함인데,
// 싱글턴 클래스의 객체는 오직 하나만 존재해야하므로 생성자가 필요치 않고 정의도 불가함
fun main(){
    // 싱글턴 클래스의 객체를 활용하는 예제
    // 싱글컨 클래스의 경우 프로그램의 시작과 함께 객체가 생성되므로 따로 객체를 생성하는 과정 없이
    // 클래스의 이릉믈 통해 속성과 메서드에 접근하는 것을 볼 수 있음.
    SingletonClass.x = 100
    SingletonClass.increaseX()
    SingletonClass.increaseX()
    SingletonClass.printX()
}
/*
    앞의 싱글턴 클래스를 자바 코드로 디컴파일한 결과
    publci final class SingletonClass{
        privaet static int x;

        // 싱글턴 클래스를 통해 생성한 유일한 객체를 보관할 상수인 ISNTANCE 정의
        public static final SingletonClass INSTANCE;
        public final int getX(){ return x; }
        public final void setX(int var1) { x = var1; }

        public final int increaseX(){
            int var1;
            x = (var1 = x) + 1;
            return var1;
        }
        public final void printX(){
            int var1 = x;
            System.out.println(var1);
        }

        // 클래스의 생성자.
        // 생성자의 접근 제어자가 private이므로 외부에서 생성자를 호출할 수 없고, 객체도 만들 수 없다.
        private SingletonClass() {}

        // static 블록 내부에서 생성자를 호출해서 객체를 생성
        // static 블록은 프로그램이 시작되어 필요한 클래스가 로딩되는 시점에서 단 한 번만 실행됨
        // 따라서 이 블록에서 생성한 INSTANCE 객체가 유일한 객체가 됨
        static{
            SingletonClass var0 = new SingletonClass();
            INSTANCE = var0;
        }
    }

    위의 클래스에 접근할 떄는
    SingletonClass.INSTANCE.setX(100);
    SingletonClass.INSTANCE.increaseX();
    이러한 방식으로 접근함함

    디자인 패턴
    : 프로그램을 작성하는 과정에서 생기는 비슷한 문제들을 효율적으로 풀어내는 방법을 모아놓은 사례
*/