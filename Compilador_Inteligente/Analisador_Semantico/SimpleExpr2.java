package Analisador_Semantico;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import Analisador_Lexico.Lexer;
import Analisador_Lexico.Tag;
import Analisador_Semantico.Sintatico;
import Util.Util;

/*
switch(token.tag)
        {
        case Tag.PL:
        case Tag.MN:
        case Tag.OR: addop(); term(); simpleExpr2(); break;
        case Tag.EQ: break;
        case Tag.GR: break;
        case Tag.GE: break;
        case Tag.LS: break;
        case Tag.LE: break;
        case Tag.NE: break;
        case Tag.END: break;
        case Tag.PV: break;
        case Tag.THEN: break;
        case Tag.FP: break;
default: error();
        }
*/

public class SimpleExpr2 extends Sintatico{

    Addop addop;
    Term term;
    SimpleExpr2 simpleExpr2;

    public SimpleExpr2(Sintatico init){
        super(init);
        this.type = init.type;
    }

    @Override
    public void analiseSemantica() {

        switch (token.tag) {
            case Tag.PL:
            case Tag.MN:
            case Tag.OR:

                addop = new Addop(this);
                addop.analiseSemantica();
                this.type = addop.type;

                term = new Term(this);
                term.analiseSemantica();

                if (!Util.isNumeric(addop.type) || !Util.isNumeric(term.type)) {

                    if (!addop.type.equals(term.type)) {

                        System.out.println("Erro semântico na linha " + Lexer.line + ":\n"  + "Operador incompatível com o type do operando.");
                        error();

                    }
                }

                this.type = Util.getNumericType(addop.type, term.type);
                simpleExpr2 = new SimpleExpr2(this);
                simpleExpr2.analiseSemantica();

                if (!simpleExpr2.type.equals("void")) {

                    if (!Util.isNumeric(addop.type) || !Util.isNumeric(simpleExpr2.type)) {
                        System.out.println("Erro semântico na linha " + Lexer.line + ":\n" + "Operador incompatível com o type do operando.");
                        error();

                    }


                }

                if (Util.isNumeric(addop.type)) {
                    this.type = Util.getNumericType(term.type, simpleExpr2.type);
                } else {
                    this.type = addop.type;
                }

                break;

            case Tag.EQ: break;
            case Tag.GR: break;
            case Tag.GE: break;
            case Tag.LS: break;
            case Tag.LE: break;
            case Tag.NE: break;
            case Tag.END: break;
            case Tag.PV: break;
            case Tag.THEN: break;
            case Tag.FP: break;

            default:
                System.out.println("Erro semântico na linha " + Lexer.line + ":\n" + "Operador incompatível com o type do operando.");
                error();
        }
    }
}
