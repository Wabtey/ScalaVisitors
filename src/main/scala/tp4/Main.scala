package tp4

object Main {
  def main(args: Array[String]): Unit = {
    val exp = BinExpression(
      Plus,
      VariableRef("y"),
      BinExpression(Minus, IntegerValue(1), IntegerValue(2))
    )
    val prog = Seq(
      Assignement("x", IntegerValue(0)),
      Seq(
        Assignement("y", IntegerValue(1)),
        Seq(
          Read("z"),
          Seq(
            While(
              BinExpression(Inf, VariableRef("x"), VariableRef("z")),
              Seq(
                Assignement(
                  "x",
                  BinExpression(Plus, VariableRef("x"), IntegerValue(1))
                ),
                Seq(
                  Assignement(
                    "y",
                    BinExpression(Times, VariableRef("y"), VariableRef("x"))
                  ),
                  Print(VariableRef("x"))
                )
              )
            ),
            Print(VariableRef("y"))
          )
        )
      )
    )

    val p2 = 
        Seq(
            Assignement(
                "x",
                BinExpression(Plus, VariableRef("x"), IntegerValue(1))
            ),
            Seq(
                Assignement(
                "y",
                BinExpression(Times, VariableRef("y"), VariableRef("x"))
                ),
                Print(VariableRef("x"))
            )
        )

    println(PrettyPrinter.stringOf(exp) + "\n-----\n" + PrettyPrinter.stringOf(prog))
    println("\n-----\n" + PrettyPrinter.stringOf(p2))
  }
}
