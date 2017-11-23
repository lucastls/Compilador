package Analisador_Semantico;

import Analisador_Lexico.Tag;
import Analisador_Lexico.Lexer;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
program ::= program [decl-list] stmt-list end

private void program() throws IOException
{
    switch(token.tag)
    {
        case Tag.PRG: eat(Tag.PRG); declList(); stmtList(); eat(Tag.END); break;
        default: error();
    }

}
 */

public class Program extends Analisador_Sintatico{
    DeclList decllist;
    StmtList stmtlist;

    public Program(Analisador_Sintatico init)
    {
        super(init);
    }

    @Override
    public void analiseSemantica()
    {
        switch(token.tag)
        {
            case Tag.PRG:
            {
                try{
                    eat(Tag.PRG);
                }catch (IOException e)
                {
                    Logger.getLogger(Program.class.getName()).log(Level.SEVERE, null, e);
                }
            }
            this.tipo = "prg";
            decllist = new DeclList(this);
            decllist.analiseSemantica();

            stmtlist = new StmtList(this);
            stmtlist.analiseSemantica();

            {
                try{
                    eat(Tag.END);
                }catch(IOException e){
                    Logger.getLogger(Program.class.getName()).log(Level.SEVERE, null, e);
                }
            }

            break;

            default:
                System.out.println("Erro sintático linha " +Lexer.line+ ":\n" + "Declaração do início do programa esperada, mas não encontrada");
                error();
        }
    }
}
