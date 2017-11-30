package Analisador_Semantico;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import Analisador_Lexico.Lexer;
import Analisador_Lexico.Tag;
import Analisador_Sintatico.Analisador_Sintatico;
/*
expression2 ::= relop expression | lambda

switch(token.tag)
        {
            case Tag.EQ:
            case Tag.GR:
            case Tag.GE:
            case Tag.LS:
            case Tag.LE:
            case Tag.NE:  relop(); expression(); break;
            case Tag.THEN: break;
            case Tag.END: break;
            case Tag.FP: break;
            default: error();
        }
 */
public class Expression2 extends Sintatico{

    Relop relop;
    Expression expression;

    public Expression2(Sintatico init){
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

                relop = new Relop(this);
                relop.analiseSemantica();

                expression = new Expression(this);
                expression.analiseSemantica();
                break;

            case Tag.THEN: break;
            case Tag.END: break;
            case Tag.FP: break;

            default:
                System.out.println("Erro sintático na linha " + Lexer.line + ":\n" + "Expressão esperada não encontrada.");
                error();
        }
    }
}
