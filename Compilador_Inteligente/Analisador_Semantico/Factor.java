package Analisador_Semantico;

import Analisador_Lexico.Tag;
import Analisador_Lexico.Lexer;
import Analisador_Sintatico.Analisador_Sintatico;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/*factor -> identifier | constant | "(" expression ")"
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
*/


public class Factor extends Analisador_Sintatico {
    
    Identifier identifier;
    Constant constant;
    Expression expression;
    
    public Factor(Analisador_Sintatico init) {
        super(init);
    }
    
    @Override
    public void analiseSemantica() {
        
    	switch (token.tag) {          
            case Tag.NUM:
            case Tag.LI:
            	constant = new Constant(this);
                constant.analiseSemantica();
                this.tipo = constant.tipo;
                break;
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
break;
           
            case Tag.PR: {
            	
            	try {
            		eat(Tag.PR);
	            } catch (IOException e) {
	                Logger.getLogger(Factor.class.getName()).log(Level.SEVERE, null, e);
	            }
            	
	        }
            
            expression = new Expression(this);
            expression.analiseSemantica();
            this.tipo = expression.tipo;
            
            {
            	try {
	                eat(Tag.FP);
	            } catch (IOException e) {
	                Logger.getLogger(Factor.class.getName()).log(Level.SEVERE, null, e);
	            }
	        }
            break;                                                                                                                                
            
            default:
                System.out.println("Erro sintático na linha " + Lexer.ine + ":\n" + "Expressão esperada não encontrada.");
                error();
        }
    }
    
}



