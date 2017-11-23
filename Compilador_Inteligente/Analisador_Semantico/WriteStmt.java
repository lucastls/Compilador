package Analisador_Semantico;

import Analisador_Lexico.Tag;
import Analisador_Lexico.Lexer;
import Analisador_Sintatico.Analisador_Sintatico;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
write-stmt ::= print(writable)

private void writeStmt() throws IOException
    {
        switch(token.tag)
        {
            case Tag.PRT: eat(Tag.PRT); eat(Tag.PR); writable(); eat(Tag.FP); break;
            default: error();
        }
    }
 */

public class WriteStmt extends Analisador_Sintatico{
    Writable writable;

    public WriteStmt(Analisador_Sintatico init)
    {
        super(init);
    }

    @Override
    public void analiseSemantica()
    {
        switch (token.tag)
        {
            case Tag.PRT:
            {
                try{
                    eat(Tag.PRT);
                }catch (IOException e)
                {
                    Logger.getLogger(WriteStmt.class.getName()).log(Level.SEVERE, null, e);
                }
            }

            {
                try{
                    eat(Tag.PR);
                }catch (IOException e)
                {
                    Logger.getLogger(WriteStmt.class.getName()).log(Level.SEVERE, null, e);
                }
            }

            writable = new Writable(this);
            writable.analiseSemantica();

            {
                try{
                    eat(Tag.FP);
                }catch (IOException e)
                {
                    Logger.getLogger(WriteStmt.class.getName()).log(Level.SEVERE, null, e);
                }
            }

            break;

            default:
                System.out.println("Erro sintático linha " +Lexer.line+ ":\n" + "Comando esperado, porém não encontrado.");
                error();
        }
    }
}
