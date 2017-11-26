package Analisador_Sintatico;

import Analisador_Lexico.*;

import java.io.IOError;
import java.io.IOException;


public class Analisador_Sintatico {

    Lexer lexer;
    private Token token;

    public Analisador_Sintatico(Lexer lexer) throws IOException
    {
        this.lexer = lexer;
        token = lexer.scan();
    }

    private void advance() throws IOException
    {
        token = lexer.scan();
    }

    private void error()
    {
        System.err.println("\nErro sintático na linha " + lexer.line +" próximo ao Token: (\""+ token+ "\")");
        System.exit(0);
    }

    private void eat(int tag) throws IOException
    {
        if(token.tag == tag)
        {
            System.out.println("eat" +token+"");
            advance();
        }
        else
        {
            error();
        }
    }

    private void initAnalise() throws IOException
    {
        program();
    }

    private void program() throws IOException
    {
        switch(token.tag)
        {
            case Tag.PRG: eat(Tag.PRG); declList(); stmtList(); eat(Tag.END); break;
            default: error();
        }

    }

    private void declList() throws IOException
    {
        switch(token.tag)
        {
            case Tag.INT:
            case Tag.STR: decl(); declList2(); break;
            default: error();
        }
    }

    private void declList2() throws IOException
    {
        switch(token.tag)
        {
            case Tag.INT:
            case Tag.STR: decl(); declList2(); break;
            case Tag.ID: break;
            case Tag.IF: break;
            case Tag.DO: break;
            case Tag.SCN: break;
            case Tag.PRT: break;
            default: error();
        }
    }

    private void decl() throws IOException
    {
        switch(token.tag)
        {
            case Tag.INT:
            case Tag.STR: type(); identList(); eat(Tag.PV); break;
            default: error();
        }
    }

    private void identList() throws IOException
    {
        switch(token.tag)
        {
            case Tag.ID: identifier(); identList2(); break;
            default: error();
        }
    }

    private void identList2() throws IOException
    {
        switch(token.tag)
        {
            case Tag.VR: eat(Tag.VR); identifier(); identList2(); break;
            case Tag.PV: break;
            default: error();
        }
    }

    private void type() throws IOException
    {
        switch(token.tag)
        {
            case Tag.INT: eat(Tag.INT); break;
            case Tag.STR: eat(Tag.STR); break;
            default: error();
        }
    }

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

    private void stmtList2() throws IOException
    {
        switch(token.tag)
        {
            case Tag.ID:
            case Tag.IF:
            case Tag.DO:
            case Tag.SCN:
            case Tag.PRT: stmt(); stmtList2(); break;
            case Tag.END: break;
            case Tag.ELSE: break;
            case Tag.WHL: break;
            default: error();
        }
    }

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

    private void assignStmt() throws IOException
    {
        switch(token.tag)
        {
            case Tag.ID: identifier(); eat(Tag.IG); simpleExpr(); break;
            default: error();
        }
    }

    private void ifStmt() throws IOException
    {
        switch(token.tag)
        {
            case Tag.IF: eat(Tag.IF); condition(); eat(Tag.THEN); stmtList(); elseStmt(); break;
            default: error();
        }
    }

    private void elseStmt() throws IOException
    {
        switch(token.tag)
        {
            case Tag.END: eat(Tag.END); break;
            case Tag.ELSE: eat(Tag.ELSE); stmtList(); eat(Tag.END); break;
            default: error();
        }
    }

    private void condition() throws IOException
    {
        switch(token.tag)
        {
            case Tag.LI:
            case Tag.ID:
            case Tag.NUM:
            case Tag.PR:
            case Tag.EX: expression(); break;
            default: error();
        }
    }

    private void whileStmt() throws IOException
    {
        switch(token.tag)
        {
            case Tag.DO: eat(Tag.DO); stmtList(); stmtSufix(); break;
            default: error();
        }
    }

    private void stmtSufix() throws IOException
    {
        switch(token.tag)
        {
            case Tag.WHL: eat(Tag.WHL); condition(); eat(Tag.END); break;
            default: error();
        }
    }

    private void readStmt() throws IOException
    {
        switch(token.tag)
        {
            case Tag.SCN: eat(Tag.SCN); eat(Tag.PR); identifier(); eat(Tag.FP); break;
            default: error();
        }
    }

    private void writeStmt() throws IOException
    {
        switch(token.tag)
        {
            case Tag.PRT: eat(Tag.PRT); eat(Tag.PR); writable(); eat(Tag.FP); break;
            default: error();
        }
    }

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

    private void expression() throws IOException
    {
        switch(token.tag)
        {
            case Tag.LI:
            case Tag.ID:
            case Tag.NUM:
            case Tag.PR:
            case Tag.EX:
            case Tag.MN:  simpleExpr(); expression2(); break;
            default: error();
        }
    }

    private void expression2() throws IOException
    {
        switch(token.tag)
        {
            case Tag.EQ:
            case Tag.GR:
            case Tag.GE:
            case Tag.LS:
            case Tag.LE:
            case Tag.NE: relop(); simpleExpr(); expression2(); break;
            case Tag.END: break;
            case Tag.THEN: break;
            case Tag.FP: break;
            default: error();
        }
    }

    private void simpleExpr() throws IOException
    {
        switch(token.tag)
        {
            case Tag.LI:
            case Tag.ID:
            case Tag.NUM:
            case Tag.PR:
            case Tag.EX:
            case Tag.MN:  term(); simpleExpr2(); break;
            default:error();

        }
    }

    private void simpleExpr2() throws IOException
    {
        switch(token.tag)
        {
            case Tag.PL:
            case Tag.MN:
            case Tag.OR: addop(); term(); simpleExpr2(); break;
            case Tag.EQ: break;
            case Tag.GR: break;
            case Tag.GE: break;
            case Tag.LS: break;
            case Tag.LE: break;
            case Tag.NE: break;
            case Tag.END: break;
            case Tag.PV: break;
            case Tag.THEN: break;
            case Tag.FP: break;
            default: error();
        }
    }

    private void term() throws IOException
    {
        switch(token.tag)
        {
            case Tag.LI:
            case Tag.ID:
            case Tag.NUM:
            case Tag.PR:
            case Tag.EX:
            case Tag.MN: fatorA(); term2(); break;
            default: error();
        }
    }

    private void term2() throws IOException
    {
        switch(token.tag)
        {
            case Tag.MP:
            case Tag.DV:
            case Tag.AN: mulop(); fatorA(); term2(); break;
            case Tag.PV: break;
            case Tag.PL: break;
            case Tag.MN: break;
            case Tag.OR: break;
            case Tag.THEN: break;
            case Tag.END: break;
            case Tag.FP: break;
            case Tag.EQ: break;
            case Tag.GR: break;
            case Tag.GE: break;
            case Tag.LS: break;
            case Tag.LE: break;
            case Tag.NE: break;
            default: error();
        }
    }

    private void fatorA() throws IOException
    {
        switch(token.tag)
        {
            case Tag.LI:
            case Tag.ID:
            case Tag.NUM:
            case Tag.PR: factor(); break;
            case Tag.EX: eat(Tag.EX); factor(); break;
            case Tag.MN: eat(Tag.MN); factor(); break;
            default: error();
        }
    }

    private void factor() throws IOException
    {
        switch(token.tag)
        {
            case Tag.NUM:
            case Tag.LI: constant(); break;
            case Tag.ID: identifier(); break;
            case Tag.PR: eat(Tag.PR); expression(); eat(Tag.FP); break;
            default: error();
        }
    }

    private void relop() throws IOException
    {
        switch(token.tag)
        {
            case Tag.EQ: eat(Tag.EQ); break;
            case Tag.GR: eat(Tag.GR); break;
            case Tag.GE: eat(Tag.GE); break;
            case Tag.LS: eat(Tag.LS); break;
            case Tag.LE: eat(Tag.LE); break;
            case Tag.NE: eat(Tag.NE); break;
            default: error();
        }
    }

    private void addop() throws IOException
    {
        switch(token.tag)
        {
            case Tag.PL: eat(Tag.PL); break;
            case Tag.MN: eat(Tag.MN); break;
            case Tag.OR: eat(Tag.OR); break;
            default: error();
        }
    }

    private void mulop() throws IOException
    {
        switch(token.tag)
        {
            case Tag.MP: eat(Tag.MP); break;
            case Tag.DV: eat(Tag.DV); break;
            case Tag.AN: eat(Tag.AN); break;
            default: error();
        }
    }

    private void constant() throws IOException
    {
        switch(token.tag)
        {
            case Tag.LI: literal(); break;
            case Tag.NUM: integerConst(); break;
            default: error();
        }
    }

    private void integerConst() throws IOException
    {
        switch(token.tag)
        {
            case Tag.NUM: eat(Tag.NUM); break;
            default: error();
        }
    }

    private void literal() throws IOException
    {
        switch(token.tag)
        {
            case Tag.LI: eat(Tag.LI); break;
            default: error();
        }
    }

    private void identifier() throws IOException
    {
        switch(token.tag)
        {
            case Tag.ID: eat(Tag.ID); break;
            default: error();
        }
    }


    public static void main(String[] args) throws IOException
    {

        try{
            for (String filepath: args) {
                Lexer lexer = new Lexer(filepath);
                Analisador_Sintatico Sintatico = new Analisador_Sintatico(lexer);
                Sintatico.initAnalise();
                System.err.println("\n");
            }

        }catch(IOException e)
        {
            System.err.println(e.getMessage());
        }
    }

}
