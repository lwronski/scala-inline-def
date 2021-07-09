import scala.deriving._
import scala.compiletime._

trait FromMap[T] {
  def fromMap(key: String): Either[String, T]
}

object FromMap:
  given FromMap[Int] = new FromMap[Int] {
    def fromMap(key: String): Either[String, Int] = ???
  }

trait Construct[T]:
  def construct(elem: String): Either[Exception, T]

object Construct:

  inline def derived[T](using m: Mirror.Of[T]): Construct[T] = {
    val fromMapElems = summonAll[m.MirroredElemTypes]
    val elemLabels = getElemLabels[m.MirroredElemLabels]
    inline m match {
      case p: Mirror.ProductOf[T] => ???
      case s: Mirror.SumOf[T]     => ???
    }
  }

  inline def summonAll[T <: Tuple]: List[FromMap[_]] = inline erasedValue[T] match {
    case _: EmptyTuple => Nil
    case _: (t *: ts)  => summonInline[FromMap[t]] :: summonAll[ts]
  }

  inline def getElemLabels[T <: Tuple]: List[String] = inline erasedValue[T] match {
    case _: EmptyTuple     => Nil
    case _: (head *: tail) => constValue[head].toString :: getElemLabels[tail]
  }