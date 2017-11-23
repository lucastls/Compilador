package Analisador_Semantico;

import Analisador_Lexico.Tag;
import Analisador_Lexico.Lexer;
import Analisador_Semantico.Sintatico;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
stmt ::= assign-stmt ";" | if-stmt | while-stmt | read-stmt ";" | write-stmt ";"

private void stmt() throws IOException
    {
        switch(token.tag)
        {
            case Tag.ID: assignStmt(); eat(Tag.PV); break;
            case Tag.IF: ifStmt(); break;
            case Tag.DO: whileStmt(); break;
            case Tag.SCN: readStmt(); eat(Tag.PV); break;
            case Tag.PRT: writeStmt(); eat(Tag.PV); break;
            default: error();
        }
    }
 */

public class Stmt extends Sintatico{
    AssignStmt assignStmt;
    IfStmt ifStmt;
    WhileStmt whileStmt;
    ReadStmt readStmt;
    WriteStmt writeStmt;

    public Stmt(Sintatico init)
    {
        super(init);
    }

    @Override
    public void analiseSemantica()
    {
        switch (token.tag)
        {
            case Tag.ID:
                assignStmt = new AssignStmt(this);
                assignStmt.analiseSemantica();

                try{
                    eat(Tag.PV);
                }catch (IOException e)
                {
                    Logger.getLogger(Stmt.class.getName()).log(Level.SEVERE, null, e);
                }

                break;

            case Tag.IF:
                ifStmt = new IfStmt(this);
                ifStmt.analiseSemantica();

                break;

            case Tag.DO:
                whileStmt = new WhileStmt(this);
                whileStmt.analiseSemantica();

                break;

            case Tag.SCN:
                readStmt = new ReadStmt(this);
                readStmt.analiseSemantica();

                try{
                    eat(Tag.PV);
                }catch (IOException e)
                {
                    Logger.getLogger(Stmt.class.getName()).log(Level.SEVERE, null, e);
                }

                break;

            case Tag.PRT:
                writeStmt = new WriteStmt(this);
                writeStmt.analiseSemantica();

                try{
                    eat(Tag.PV);
                }catch (IOException e)
                {
                    Logger.getLogger(Stmt.class.getName()).log(Level.SEVERE, null, e);
                }

                break;

            default:
                System.out.println("Erro sintático linha " +Lexer.line+ ":\n" + "Comando esperado, porém não encontrado.");
                error();
        }
    }
}
