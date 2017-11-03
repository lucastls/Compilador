
package Analisador_Lexico2;

import java.io.IOException;


public class Analisador_Sintatico {
    
    Lexer lexer;
    private Token token;
    int tok = token.getTag();
    
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
        System.out.println("Erro sintático linha: " + lexer.line);
        System.exit(0);
    }
    
    private void eat(int tag) throws IOException
    {
        if(token.tag == tag)
        {
            System.err.println("eat(" +token+")");
            advance();
        }
        else
        {
            error();
        }
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
            case Tag.STR: decl(); break;
            default: error();
        }
    }

    private void decl() throws IOException
    {
        switch(token.tag)
        {
            case Tag.INT:
            case Tag.STR: type(); identList(); eat(Tag.PV);
            default: error();
        }
    }

    private void identList() throws IOException
    {
        switch(token.tag)
        {
            case Tag.ID: identifier(); break;
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
            case Tag.PRT: stmt(); break;
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
            case Tag.ID: identifier(); eat(Tag.EQ); simpleExpr(); break;
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
            case Tag.STR:
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
            case Tag.WHL: eat(Tag.WHL); condition(); eat(Tag.END);
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
            case Tag.PRT: eat(Tag.PRT); eat(Tag.PR); writable(); eat(Tag.FP);
            default: error();
        }
    }

    private void writable() throws IOException
    {
        switch(token.tag)
        {
            case Tag.STR:
            case Tag.ID:
            case Tag.NUM:
            case Tag.PR:
            case Tag.EX:
            case Tag.MN: simpleExpr(); break;

            case Tag.AS: literal(); break;
            default: error();
        }
    }

    //ERRADO
    private void expression() throws IOException
    {
        switch(token.tag)
        {
            case Tag.ID: simpleExpr(); break;
            default: error();
        }
    }
    
    private void simpleExpr() throws IOException
    {
        
    }
    
    private void term() throws IOException
    {
        
    }
    
    private void fatorA() throws IOException
    {
        
    }
    
    private void factor() throws IOException
    {
        
    }
    
    private void relop() throws IOException
    {
        
    }
    
    private void addop() throws IOException
    {
        
    }
    
    private void mulop() throws IOException
    {
        
    }
    
    private void constant() throws IOException
    {
        switch(token.tag)
        {
            case Tag.ID: integerConst(); break;
            case TAG.LI: literal(); break;
            default: error();
        }
    }

    //ERRADO
    private void integerConst() throws IOException
    {
        switch(token.tag)
        {
            case Tag.NUM: eat(TAG.NUM); integerConst(); break;
            default: error();
        }
    }
    
    private void literal() throws IOException
    {
        switch(token.tag)
        {
            case TAG.LI: eat(TAG.LI); break;
            default: error();
        }
    }
    
    private void identifier() throws IOException
    {
        
    }

    //????
    private void letter() throws IOException
    {
        switch(token.tag)
        {
            case TAG.LI: eat(TAG.LI); break;
            default: error();
        }
    }
    
    private void digit() throws IOException
    {
        switch(token.tag)
        {
            case TAG.NUM: eat(TAG.NUM); break;
            default: error();
        }
    }
    
    private void caractere() throws IOException
    {
        
    }
}
