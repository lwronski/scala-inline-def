import scala.deriving._
import scala.compiletime._


trait Decoder[T: Mirror.Of]:

  given yamlWriter: Construct[T] = Construct.derived[T]

  final def from(yaml: String): Either[Exception, T] = ???

  def apply(node: String): T = ???

object Decoder:
  inline def derived[T](using m: Mirror.Of[T]): Decoder[T] = {
    new Decoder[T] {
      override def apply(yaml: String): T = ???
    }
  }
