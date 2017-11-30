package Analisador_Semantico;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import Analisador_Lexico.Lexer;
import Analisador_Lexico.Tag;
import Analisador_Semantico.Sintatico;

/*
expression ::= simple-expr expression2

switch(token.tag)
        {
            case Tag.LI:
            case Tag.ID:
            case Tag.NUM:
            case Tag.PR:
            case Tag.EX:
            case Tag.MN: simpleExpr(); expression2(); break;
            case Tag.END: break;
            case Tag.THEN: break;
            case Tag.FP: break;
            default: error();


        }
 */

public class Expression extends Sintatico{

    SimpleExpr simpleExpr;
    Expression2 expression2;

    public Expression(Sintatico init){
        super(init);
    }

    @Override
    public void analiseSemantica() {

        switch (token.tag) {
            case Tag.LI:
            case Tag.ID:
            case Tag.NUM:
            case Tag.PR:
            case Tag.EX:
            case Tag.MN:

                simpleExpr = new SimpleExpr(this);
                simpleExpr.analiseSemantica();

                expression2 = new Expression2(this);
                expression2.analiseSemantica();

                if (!expression2.type.equals("void")) {

                    if (!simpleExpr.type.equals(expression2.type)) {
                        System.out.println("Erro semântico na linha " + Lexer.line + ":\n" + "Operador com tipo incompatível com o operando. ");
                        error();
                    }

                    this.type = "bool";

                } else {
                    this.type = simpleExpr.type;
                }
                break;

            case Tag.END: break;
            case Tag.THEN: break;
            case Tag.FP: break;

            default:
                System.out.println("Erro sintático na linha " + Lexer.line + ":\n" + "Expressão esperada não encontrada.");
                error();
        }
    }
}
