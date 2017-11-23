package Analisador_Semantico;

import Analisador_Lexico.Tag;
import Analisador_Lexico.Lexer;
import Analisador_Semantico.Sintatico;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
stmt-sufix ::= while condition end

private void stmtSufix() throws IOException
    {
        switch(token.tag)
        {
            case Tag.WHL: eat(Tag.WHL); condition(); eat(Tag.END); break;
            default: error();
        }
    }
 */

public class StmtSufix extends Sintatico{
    Condition condition;

    public StmtSufix(Sintatico init)
    {
        super(init);
    }

    @Override
    public void analiseSemantica()
    {
        switch (token.tag)
        {
            case Tag.WHL:
            {
                try{
                    eat(Tag.WHL);
                }catch (IOException e)
                {
                    Logger.getLogger(StmtSufix.class.getName()).log(Level.SEVERE, null, e);
                }
            }

            condition = new Condition(this);
            condition.analiseSemantica();

            {
                try{
                    eat(Tag.END);
                }catch (IOException e)
                {
                    Logger.getLogger(StmtSufix.class.getName()).log(Level.SEVERE, null, e);
                }
            }

            break;

            default:
                System.out.println("Erro sintático linha " +Lexer.line+ ":\n" + "Comando esperado, porém não encontrado.");
                error();
        }
    }
}
