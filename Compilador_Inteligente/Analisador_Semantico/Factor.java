package Analisador_Semantico;

import Analisador_Lexico.Tag;
import Analisador_Lexico.Lexer;
import Analisador_Semantico.Sintatico;

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


public class Factor extends Sintatico {
    
    Identifier identifier;
    Constant constant;
    Expression expression;
    
    public Factor(Sintatico init) {
        super(init);
    }
    
    @Override
    public void analiseSemantica() {
        
    	switch (token.tag) {          
            case Tag.NUM:
            case Tag.LI:
            	constant = new Constant(this);
                constant.analiseSemantica();
                this.type = constant.type;
                break;
	    case Tag.ID:
                
            	identifier = new Identifier(this);
                identifier.analiseSemantica();
                this.type = identifier.type;
		        
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
        this.type = expression.type;

        {
            try {
                eat(Tag.FP);
            } catch (IOException e) {
                Logger.getLogger(Factor.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        break;

        default:
            System.out.println("Erro sintático na linha " + Lexer.line + ":\n" + "Expressão esperada não encontrada.");
            error();
        }
    }
    
}



