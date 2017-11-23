package Analisador_Semantico;

import Analisador_Lexico.Tag;
import Analisador_Lexico.Lexer;
import Analisador_Sintatico.Analisador_Sintatico;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
if-stmt ::= if condition then stmt-list end

private void ifStmt() throws IOException
    {
        switch(token.tag)
        {
            case Tag.IF: eat(Tag.IF); condition(); eat(Tag.THEN); stmtList(); elseStmt(); break;
            default: error();
        }
    }
 */

public class IfStmt extends Analisador_Sintatico{
    Condition condition;
    StmtList stmtList;
    ElseStmt elseStmt;

    public IfStmt(Analisador_Sintatico init){
        super(init);
    }

    @Override
    public void analiseSemantica()
    {
        switch (token.tag)
        {
            case Tag.IF:
            {
                try{
                    eat(Tag.IF);
                }catch(IOException e)
                {
                    Logger.getLogger(IfStmt.class.getName()).log(Level.SEVERE, null, e);
                }

                condition = new Condition(this);
                condition.analiseSemantica();

                try{
                    eat(Tag.THEN);
                }catch (IOException e)
                {
                    Logger.getLogger(IfStmt.class.getName()).log(Level.SEVERE, null, e);
                }

                stmtList = new StmtList(this);
                stmtList.analiseSemantica();

                elseStmt = new ElseStmt();
                elseStmt.analiseSemantica();

                break;
            }

            default:
                System.out.println("Erro sintático linha " +Lexer.line+ ":\n" + "Comando esperado, porém não encontrado.");
                error();
        }
    }
}
