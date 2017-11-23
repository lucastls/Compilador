package Analisador_Semantico;

import Analisador_Lexico.Tag;
import Analisador_Lexico.Lexer;
import Analisador_Semantico.Sintatico;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
stmt-list ::= stmt stmt-list2

private void stmtList() throws IOException
    {
        switch(token.tag)
        {
            case Tag.ID:
            case Tag.IF:
            case Tag.DO:
            case Tag.SCN:
            case Tag.PRT: stmt(); stmtList2(); break;
            default: error();
        }
    }
 */

public class StmtList extends Sintatico{
    Stmt stmt;
    StmtList2 stmtlist2;

    public StmtList(Sintatico init)
    {
        super(init);
    }

    @Override
    public void analiseSemantica()
    {
        switch(token.tag)
        {
            case Tag.ID:
            case Tag.IF:
            case Tag.DO:
            case Tag.SCN:
            case Tag.PRT:
                stmt = new Stmt(this);
                stmt.analiseSemantica();

                stmtlist2 = new StmtList2(this);
                stmtlist2.analiseSemantica();

                break;
            default:
                System.out.println("Erro sintático linha " +Lexer.line+ ":\n" + "Declaração do início do programa esperada, mas não encontrada");
                error();
        }
    }
}
