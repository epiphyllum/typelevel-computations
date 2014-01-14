import scala.language.experimental.macros
import scala.reflect.runtime.{universe ⇒ u}
import u._


object Test {
  prolog {
    X, Y, Z, Out : Nat
    gcd(X, X, X)
    gcd(X, Y, Out) :- {
      X < Y,
      Z = Y - X,
      gcd(X, Z, Out)
    }
    gcd(X, Y, Out) :- {
      Y < X,
      gcd(Y, X, Out)
    }
  }
}
