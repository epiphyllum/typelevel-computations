import scala.language.experimental.macros
import scala.reflect.runtime.{universe ⇒ u}
import u._


object Test {
  prolog {
    declarations[
      X <: Nat,
      Y <: Nat,
      Z <: Nat,
      Out <: Nat
    ]
    GCD(X, X, X)
    GCD(X, Y, Out) :- {
      X < Y
      Z = Y - X
      GCD(X, Z, Out)
    }
    GCD(X, Y, Out) :- {
      Y < X
      GCD(Y, X, Out)
    }
  }
  /*
   * Should generate the following code:
   * trait GCD[X <: Nat, Y <: Nat, Z <: Nat] {}
   *
   * object GCD {
   *   implicit def gcd0[X <: Nat]: GCD[X, X, X] =
   *     new GCD[X, X, X] {}
   *   implicit def gcd1[X <: Nat, Y <: Nat, Z <: Nat, Out <: Nat]
   *     (implicit ev0 : LT[X, Y], ev1 : Diff.Aux[Y, X, Z],
   *               ev2 : GCD[X, Z, Out]): GCD[X, Y, Out] =
   *       new GCD[X, Y, Out] {}
   *   implicit def gcd2[X <: Nat, Y <: Nat, Out <: Nat]
   *     (implicit ev0 : LT[Y, X],
   *               ev1 : GCD[Y, X, Out]): GCD[X, Y, Out] =
   *     new GCD[X, Y, Out] {}
   * }
   */
}
