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
            case Tag.LI:
            case Tag.ID:
            case Tag.NUM:
            case Tag.PR:
            case Tag.EX:
            case Tag.MN:  simpleExpr(); expression2(); break;
            default: error();
        }
 */
public class Expression2 extends Sintatico{

    Relop relop;
    Expression2 expression2;

    public Expression2(Sintatico init){
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

                relop = new Relop(this);
                relop.analiseSemantica();

                expression2 = new Expression2(this);
                expression2.analiseSemantica();
                break;

            default:
                System.out.println("Erro sintático na linha " + Lexer.line + ":\n" + "Expressão esperada não encontrada.");
                error();
        }
    }
}
