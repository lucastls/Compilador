package Analisador_Semantico;

import Analisador_Lexico.Tag;
import Analisador_Lexico.Lexer;
import Analisador_Semantico.Sintatico;
import Util.Util;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
assign-stmt ::= identifier "=" simple-expr
private void assignStmt() throws IOException
    {
        switch(token.tag)
        {
            case Tag.ID: identifier(); eat(Tag.IG); simpleExpr(); break;
            default: error();
        }
    }
*/

public class AssignStmt extends Sintatico {
    
    Identifier identifier;
    SimpleExpr simpleExpr;
    
    public AssignStmt(Sintatico init){
        super(init);
    }
    
    @Override
    public void analiseSemantica() {

    	switch (token.tag) {

    		case Tag.ID:

            	identifier = new Identifier(this);
                identifier.analiseSemantica();
		        
                {
		            try {
		                eat(Tag.IG);
		            } catch (IOException e) {
		                Logger.getLogger(AssignStmt.class.getName()).log(Level.SEVERE, null, e);
		            }
		        }
                
                simpleExpr = new SimpleExpr(this);
                simpleExpr.analiseSemantica();
                
                if (!Util.canAssign(identifier.type, simpleExpr.type)) {
                    
                	System.out.println("Erro semântico na linha " + Lexer.line + ":\n" + "Tipos incompatíveis.");
                    error();
                }
                
                break;
            
    		default:
                System.out.println("Erro sintático na linha " + Lexer.line + ":\n" + "Atribuição esperada, porém nao encontrada.");
                error();
                
        }
    }
}
