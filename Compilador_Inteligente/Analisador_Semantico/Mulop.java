package Analisador_Semantico;

import Analisador_Lexico.Tag;
import Analisador_Lexico.Lexer;
import Analisador_Sintatico.Analisador_Sintatico;

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

public class Mulop extends Analisador_Sintatico {
    
    public Mulop(Analisador_Sintatico init) {
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
            this.tipo = init.tipo;
			break;
		    
	    	case Tag.DV: {
	            
	    		try {
	                eat(Tag.DV);
	            } catch (IOException e) {
	                Logger.getLogger(Mulop.class.getName()).log(Level.SEVERE, null, e);
	            }
	        }
            this.tipo = init.tipo;
			break;
			
		    case Tag.AN: {
	            
		    	try {
	                eat(Tag.AN);
	            } catch (IOException e) {
	                Logger.getLogger(Mulop.class.getName()).log(Level.SEVERE, null, e);
	            }
	        }
            this.tipo = "bool";
			break;
		    
		    default:
	                System.out.println("Erro sintático na linha " + Lexer.line + ":\n"+ "Operador aritmético esperado, porém nao encontrado.");
	                error();
	}
    }
}
