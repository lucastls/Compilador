package Analisador_Semantico;

import Analisador_Lexico.Tag;
import Analisador_Lexico.Lexer;
import Analisador_Sintatico.Analisador_Sintatico;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
writable ::= simple-expr | literal

private void writable() throws IOException
    {
        switch(token.tag)
        {
            case Tag.ID:
            case Tag.NUM:
            case Tag.PR:
            case Tag.EX:
            case Tag.MN: simpleExpr(); break;

            case Tag.LI: literal(); break;
            default: error();
        }
    }
 */

public class Writable extends Analisador_Sintatico{
    SimpleExpr simpleExpr;
    Literal literal;

    public Writable(Analisador_Sintatico init)
    {
        super(init);
    }

    @Override
    public void analiseSemantica()
    {
        switch (token.tag)
        {
            case Tag.ID:
            case Tag.NUM:
            case Tag.PR:
            case Tag.EX:
            case Tag.MN:
                simpleExpr = new SimpleExpre(this);
                simpleExpr.analiseSemantica();

                break;

            case Tag.LI:
                literal = new Literal(this);
                literal.analiseSemantica();

                break;

            default:
                System.out.println("Erro sintático linha " +Lexer.line+ ":\n" + "Comando esperado, porém não encontrado.");
                error();
        }
    }
}
