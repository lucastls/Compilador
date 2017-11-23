package Analisador_Semantico;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import Analisador_Lexico.Lexer;
import Analisador_Lexico.Tag;
import Analisador_Sintatico.Analisador_Sintatico;

/*
switch(token.tag)
        {
            case Tag.EQ:
            case Tag.GR:
            case Tag.GE:
            case Tag.LS:
            case Tag.LE:
            case Tag.NE: relop(); simpleExpr(); expression2(); break;
            case Tag.END: break;
            case Tag.THEN: break;
            case Tag.FP: break;
            default: error();
        }
 */

public class Expression extends Analisador_Semantico{

    SimpleExpr simpleExpr;
    Expression2 expression2;

    public Expression(Analisador_Sintatico init){
        super(init);
    }

    @Override
    public void analiseSemantica() {

        switch (token.tag) {
            case Tag.EQ:
            case Tag.GR:
            case Tag.GE:
            case Tag.LS:
            case Tag.LE:
            case Tag.NE:

                simpleExpr = new SimpleExpr(this);
                simpleExpr.analiseSemantica();

                expression2 = new Expression2(this);
                expression2.analiseSemanticaSemantica();

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

            default:
                System.out.println("Erro sintático na linha " + Lexer.line + ":\n" + "Expressão esperada não encontrada.");
                error();
        }
    }
}
