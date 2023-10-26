package tp4

object PrettyPrinter {
    val tab_space = 2;

    def stringOf(p:Statement):String={
        stringOfIndent(p, 0, false)
    }

    private def stringOfIndent(p:Statement, i:Int, fromSeq: Boolean):String={
        var indentation:String = ""
        // XXX: weird
        for (indent <- 0 until i) {
            indentation += " "
        }
        indentation + (
            p match {
                case Assignement(v, e) => v + ":= " + stringOf(e)
                case If(c, s1, s2) =>
                    "if (" + stringOf(c) + ") then {\n" +
                    stringOfIndent(s1, i+tab_space, false) + "\n} else {\n" +
                    stringOfIndent(s2, i+tab_space, false) + "\n}"
                case Print(e) => "print(" + stringOf(e) + ")"
                case Read(s) => "read(" + s + ")"
                case Skip => "skip"
                case While(c, s) =>
                    "while(" + stringOf(c) + ") do\n" +
                        stringOfIndent(
                            s,
                            if(isASeq(s)) { i } else { i+tab_space },
                            false
                        )
                    
                case Seq(s1, s2) =>
                    (if (fromSeq) { "" } else { "{\n" }) +
                    stringOfIndent(s1, i+tab_space, true) + "\n" +
                    stringOfIndent(
                        s2,
                        if(isASeq(s2)) { i } else { i+tab_space },
                        true
                    ) +
                    (if (fromSeq) { "" } else { "\n" + indentation + "}" })
            }
        )
    }

    def stringOf(e:Expression): String={
        e match {
            case IntegerValue(i) => i.toString()
            case VariableRef(s) => s
            case BinExpression(op, e1, e2) => "(" + stringOf(e1) + " " + stringOf(op) + " " + stringOf(e2) + ")"
        }
    }

    def stringOf(e:Operator): String={
        e match {
            case Minus => "-"
            case Plus => "+"
            case Equal => "="
            case Inf => "<"
            case Infeq => "<="
            case Times => "*"
        }
    }


    def isASeq(p:Statement):Boolean={
        p match {
            case Seq(_, _) => true
            case _ => false 
        }
    }
}
