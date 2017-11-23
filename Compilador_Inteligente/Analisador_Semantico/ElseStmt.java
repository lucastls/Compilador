package Analisador_Semantico;

import Analisador_Lexico.Tag;
import Analisador_Lexico.Lexer;
import Analisador_Sintatico.Analisador_Sintatico;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
else-stmt ::= if condition then stmt-list else stmt-list end

private void elseStmt() throws IOException
    {
        switch(token.tag)
        {
            case Tag.END: eat(Tag.END); break;
            case Tag.ELSE: eat(Tag.ELSE); stmtList(); eat(Tag.END); break;
            default: error();
        }
    }
 */

public class ElseStmt extends Analisador_Sintatico{
    Condition condition;
    StmtList stmtList;

    public ElseStmt(Analisador_Sintatico init)
    {
        super(init);
    }

    @Override
    public void analiseSemantica()
    {
        switch (token.tag)
        {
            case Tag.END:
            {
                try{
                    eat(Tag.END);
                }catch (IOException e)
                {
                    Logger.getLogger(ElseStmt.class.getName()).log(Level.SEVERE, null, e);
                }
            }

            break;

            case Tag.ELSE:
            {
                try{
                    eat(Tag.ELSE);
                }catch (IOException e)
                {
                    Logger.getLogger(ElseStmt.class.getName()).log(Level.SEVERE, null, e);
                }
            }

            stmtList = new StmtList(this);
            stmtList.analiseSemantica();

            case Tag.END:
            {
                try{
                    eat(Tag.END);
                }catch (IOException e)
                {
                    Logger.getLogger(ElseStmt.class.getName()).log(Level.SEVERE, null, e);
                }
            }

            break;

            default:
                System.out.println("Erro sintático linha " +Lexer.line+ ":\n" + "Comando esperado, porém não encontrado.");
                error();
        }
    }
}
