import tp4.Assignement
import tp4.PrettyPrinter
import tp4.IntegerValue

import collection.mutable.Stack
import org.scalatest._
import flatspec._
import matchers._

class PrettyPrinterTests extends AnyFlatSpec with should.Matchers {
    test("Assignement Print") {
        val prog = Assignement("x", IntegerValue(0))
        assert(PrettyPrinter.stringOf(prog) == "x:= 0")
    }
}
