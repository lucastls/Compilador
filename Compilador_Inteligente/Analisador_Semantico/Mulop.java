package Analisador_Semantico;

import Analisador_Lexico.Tag;
import Analisador_Lexico.Lexer;
import Analisador_Semantico.Sintatico;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/* mulop -> "*" | "/" | "&&"
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
*/

public class Mulop extends Sintatico {
    
    public Mulop(Sintatico init) {
        super(init);
    }
    
    @Override
    public void analiseSemantica() {
        
    	switch(token.tag) {
	    
	    	case Tag.MP: {
	            
	    		try {
	                eat(Tag.MP);
	            } catch (IOException e) {
	                Logger.getLogger(Mulop.class.getName()).log(Level.SEVERE, null, e);
	            }
	        }
            this.type = init.type;
			break;
		    
	    	case Tag.DV: {
	            
	    		try {
	                eat(Tag.DV);
	            } catch (IOException e) {
	                Logger.getLogger(Mulop.class.getName()).log(Level.SEVERE, null, e);
	            }
	        }
            this.type = init.type;
			break;
			
		    case Tag.AN: {
	            
		    	try {
	                eat(Tag.AN);
	            } catch (IOException e) {
	                Logger.getLogger(Mulop.class.getName()).log(Level.SEVERE, null, e);
	            }
	        }
            this.type = "bool";
			break;
		    
		    default:
	                System.out.println("Erro sintático na linha " + Lexer.line + ":\n"+ "Operador aritmético esperado, porém nao encontrado.");
	                error();
	}
    }
}
