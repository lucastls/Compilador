package Analisador_Semantico;

import Analisador_Lexico.Tag;
import Analisador_Lexico.Lexer;
import Analisador_Sintatico.Analisador_Sintatico;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
while-stmt ::= do stmt-list stmt-sufix

private void whileStmt() throws IOException
    {
        switch(token.tag)
        {
            case Tag.DO: eat(Tag.DO); stmtList(); stmtSufix(); break;
            default: error();
        }
    }
 */

public class WhileStmt extends Analisador_Sintatico{
    StmtList stmtList;
    StmtSufix stmtSufix;

    public WhileStmt(Analisador_Sintatico init)
    {
        super(init);
    }

    @Override
    public void analiseSemantica()
    {
        switch (token.tag)
        {
            case Tag.DO:
            {
                try{
                    eat(Tag.DO);
                }catch (IOException e)
                {
                    Logger.getLogger(WhileStmt.class.getName()).log(Level.SEVERE, null, e);
                }
            }

            stmtList = new StmtList(this);
            stmtList.analiseSemantica();

            stmtSufix = new StmtSufix(this);
            stmtSufix.analiseSemantica();

            break;

            default:
                System.out.println("Erro sintático linha " +Lexer.line+ ":\n" + "Comando esperado, porém não encontrado.");
                error();
        }
    }
}
