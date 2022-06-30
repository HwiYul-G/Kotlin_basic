/*
    === 업캐스팅과 다형성 ===
    업캐스팅은 객체지향 프로그래밍 특성 중 다형성(pollymorphism)을 지원하기 위해 반드시 필요하다.
    여기서 다형성은 부모 타입의 객체가 다양한 자식 객체로 변화해서 작동할 수 있다는 특징임임
*/

// 무언가를 그릴 수 있는 기능을 제공하는 인터페이스 정의
interface Drawable{
    fun draw(c:Canvas) // 전달받은 캔버스에 그림을 그리는 추상메서드
}
class Canvas(width:Int,height:Int){
    // 인터페이스 타입의 객체를 전달받아서 그림 그리기 메서드를 호출
    fun drawToCanvas(d:Drawable){
        d.draw(this)
    }
}

// 인터페이스를 구현할 다양한 추상메서드
// 모두 무언가를 그리기 위해 사용된다는 공통의 목적이 있는 클래스지만 구현은 조금씩 다름.
class DrawableRectangle(val width:Int, val height:Int) : Drawable{
    private fun drawRectangle(c:Canvas){
        println("캔버스에 ${width}x${height} 크기의 사각형을 그립니다.")
    }

    override fun draw(c: Canvas) = drawRectangle(c)
}
class DrawableCircle(val centerX:Int,val centerY: Int, val radius: Int):Drawable{
    private fun drawCircle(c:Canvas){
        println("캔버스의 (${centerX}, ${centerY}) 위치에 반지름이 ${radius}인 원을 그립니다.")
    }
    override fun draw(c:Canvas) = drawCircle(c)
}

class DrawableLine(val x1:Int, val y1:Int, val x2:Int, val y2:Int) : Drawable{
    private fun drawLine(c:Canvas){
        println("캔버스의 (${x1}, ${y1}) 위치에서 시작하여 (${x2}, ${y2}) 위치에서 끝나는 선을 그립니다.")
    }
    override fun draw(c: Canvas) = drawLine(c)
}

class DrawableBackground(val red: Int, val green: Int, val blue : Int) : Drawable{
    override fun draw(c:Canvas) = println("캔버스의 배경색 (${red} ,${green} ,${blue})을 칠합니다.")
}

fun main(){
    val c = Canvas(500,500)

    // Drawable 타입의 객체를 저장할 리스트를 생성하고 여러 객체를 추가함
    // 객체가 추가되는 시점에 Drawable 타입으로 업캐스팅이 발생함
    val drawables = mutableListOf<Drawable>()
    drawables.add(DrawableRectangle(200,200))
    drawables.add(DrawableCircle(250,250,50))
    drawables.add(DrawableLine(0,0,500,500))
    drawables.add(DrawableBackground(255,0,0))

    for(d in drawables){
        // 리스트 객채를 순회하며 drawToCanvas() 메서드를 호출하며, Drawable 인터페이스를 구현한 객체를 전달함
        c.drawToCanvas(d)
    }
}
/*
캔버스 그리기 메서드에서 관심있는 것은 메서드에 전달된 객체가 그릴 수 있는 기능을 제공하는지 여부밖엥 ㅓㅄ다.
무엇을 어떻게 그릴지는 인터페이스를 구현한 클래스에서 담당한다.
여기서 부모 타입으로 업캐스팅 되었어도 자식타입에서 재정의한 메소드가 호출된다는 개념이 화용된다.

즉 객체(d)는 부모타입(Drawable)의 객체이지만 리스트를 순회하는 매 시점마다 해당 인터페이스를 구현하고 있는 구체적인 클래스에서 재정의한 메서드를 실행함.
구체적인 동작 방식(그리는 대상과 그려지는 방식)이 달라지게 되므로 다형성이라는 개념이 성립한다.

인터페이스를 구현하지 않는 방식으로 같은 기능을 수행하도록 코드를 작성해야한다면,
캔버스 객체에 구체적인 클래스를 전달받는 메서드를 모두 오버로딩 방식으로 재정의해야한다.
// 이때, Drawable 인터페이스는 삭제 해야함함class Canvas(with:Int, height:Int){
    fun drawToCanvas(d: DrawableRectangle){d.draw(this)}
    fun drawToCanvas(d: DrawableCircle){d.draw(this)}
    fun drawToCanvas(d: DrawableLine){d.draw(this)}
    fun drawToCanvas(d: DrawableBackground){d.draw(this)}
}

이러한 방식으로 구현하게 되면, 모든 클래스가 Drawable 이란 인터페이스를 구현한다는 공통점이 사라진 상황이므로
리스트를 통해 그룹처럼 관리할 수 없고 개별 객체를 통해 그리기 메서드를 홏루해야한다.

val c = Canvas(500,500)
// 그룹으로 관리할 수 없으므로 개별 객체를 생성
val rect = DrawableRectangle(200, 200)
var cir = DrawableCircle(250,250,50)
val line = DrawableLine(0, 0, 500,500)
val back = DrawableBackground(255, 0, 0)
// 오버로딩된 메서드에 개별 객체를 전달
c.drawToCanvas(rect)
c.drawToCanvas(cir)
c.drawToCanvas(background)
c.drawToCanvas(back)

위와 같이 다형성을 활용할 수 없는 경우 코드가 길어지고 중복되는 부분이 많아져 코드 관리가 여럽다.

인터페이스를 상속받는 클래스를 통해 다형성을 구현할 수 있을 뿐만아니라,
일반 클래스나 추상 클래스를 상속한 클래스를 부모 타입으로 업캐스팅하더라도
부모 객체 타입을 통해 재정의한 메서드를 호출한다는 개념은 동일하게 적용된다.
*/