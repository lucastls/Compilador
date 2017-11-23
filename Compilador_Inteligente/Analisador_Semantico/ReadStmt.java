package Analisador_Semantico;

import Analisador_Lexico.Tag;
import Analisador_Lexico.Lexer;
import Analisador_Sintatico.Analisador_Sintatico;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
read-stmt ::= scan(id)

private void readStmt() throws IOException
    {
        switch(token.tag)
        {
            case Tag.SCN: eat(Tag.SCN); eat(Tag.PR); identifier(); eat(Tag.FP); break;
            default: error();
        }
    }
 */

public class ReadStmt extends Analisador_Sintatico{
    Identifier identifier;

    public ReadStmt(Analisador_Sintatico init)
    {
        super(init);
    }

    @Override
    public void analiseSemantica()
    {
        switch (token.tag)
        {
            case Tag.SCN:
            {
                try{
                    eat(Tag.SCN);
                }catch (IOException e)
                {
                    Logger.getLogger(ReadStmt.class.getName()).log(Level.SEVERE, null, e);
                }
            }

            {
                try{
                    eat(Tag.PR);
                }catch (IOException e)
                {
                    Logger.getLogger(ReadStmt.class.getName()).log(Level.SEVERE, null, e);
                }
            }

            identifier = new Identifier(this);
            identifier.analiseSemantica();

            {
                try{
                    eat(Tag.FP);
                }catch (IOException e)
                {
                    Logger.getLogger(ReadStmt.class.getName()).log(Level.SEVERE, null, e);
                }
            }

            break;

            default:
                System.out.println("Erro sintático linha " +Lexer.line+ ":\n" + "Comando esperado, porém não encontrado.");
                error();
        }
    }
}
