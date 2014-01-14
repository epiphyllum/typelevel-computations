import scala.reflect.macros.WhiteboxContext
import scala.language.experimental.macros
import scala.reflect.runtime.universe.Liftable


object liftableMacro {
  def prolog(code: Any) = macro impl(code)
  def impl(code: c.Expr[Any])(c: WhiteboxContext): c.Tree = ???
}
