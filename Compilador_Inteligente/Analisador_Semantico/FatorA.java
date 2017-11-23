package Analisador_Semantico;

import Analisador_Lexico.Tag;
import Analisador_Lexico.Lexer;
import Analisador_Semantico.Sintatico;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/*factor-a -> factor | ! factor | "-" factor
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
*/

public class FatorA extends Sintatico {
    
    Factor factor;
    
    public FatorA(Sintatico init){
        super(init);
    }
    
    @Override
    public void analiseSemantica() {
        
    	switch (token.tag) {
            	
		case Tag.LI:
    		case Tag.ID:
		case Tag.NUM:
		case Tag.PR:
			factor = new Factor(this);
                	factor.analiseSemantica();
                	this.type = factor.type;
                	break;                
                case Tag.EX: {
		        try {
		            eat( Tag.EX);
		        } catch (IOException e) {
		            Logger.getLogger(FatorA.class.getName()).log(Level.SEVERE, null, e);
		        }
		    }
	            factor = new Factor(this);
	            factor.analiseSemantica();
	            if (!factor.type.equals("bool")) {
	                System.out.println("Erro Semântico na linha " + lexer.line + ":\n" + "Operador booleano esperado.");
	                error();
	            }
	            this.type = factor.type;
	            break;
        
            	case Tag.MN: {
		            try {
		                eat(Tag.MN);
		            } catch (IOException e) {
		                Logger.getLogger(FatorA.class.getName()).log(Level.SEVERE, null, e);
		            }
    		}
	            factor = new Factor(this);
	            factor.analiseSemantica();
	            if ((!factor.type.equals("inteiro"))&&(!factor.type.equals("literal"))) {
	                System.out.println("Erro Semântico na linha " + lexer.line + ":\n" + "Operador númerico esperado.");
	                error();
	            }
	            this.type = factor.type;
	            break;
            
            default:
                System.out.println("Erro sintatico na linha " + lexer.line + ":\n" + "Expressao esperada nao encontrada.");
                error();
        }
    }
    
}
