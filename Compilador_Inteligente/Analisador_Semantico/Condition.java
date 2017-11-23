package Analisador_Semantico;

import Analisador_Lexico.Tag;
import Analisador_Lexico.Lexer;
import Analisador_Semantico.Sintatico;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
condition ::= expression

private void condition() throws IOException
    {
        switch(token.tag)
        {
            case Tag.LI:
            case Tag.ID:
            case Tag.NUM:
            case Tag.PR:
            case Tag.EX: expression(); break;
            default: error();
        }
    }
 */

public class Condition extends Sintatico{
    Expression expression;

    public Condition(Sintatico init)
    {
        super(init);
    }

    @Override
    public void analiseSemantica()
    {
        switch (token.tag)
        {
            case Tag.LI:
            case Tag.ID:
            case Tag.NUM:
            case Tag.PR:
            case Tag.EX:
                expression = new Expression(this);
                expression.analiseSemantica();

                break;

            default:
                System.out.println("Erro sintático linha " +Lexer.line+ ":\n" + "Comando esperado, porém não encontrado.");
                error();
        }
    }
}
