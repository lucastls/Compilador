package Analisador_Semantico;

import Analisador_Lexico.Tag;
import Analisador_Lexico.Lexer;
import Analisador_Semantico.Sintatico;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/*constant -> integer_const | literal 
 private void constant() throws IOException
    {
        switch(token.tag)
        {
            case Tag.LI: literal(); break;
            case Tag.NUM: integerConst(); break;
            default: error();
        }
    }

*/

public class Constant extends Sintatico {
    
    public Constant(Sintatico init){
        super(init);
    }
    
    @Override
    public void analiseSemantica() {
        
    	switch (token.tag) {
		    case Tag.NUM: {
	            
		    	try {
	                eat(Tag.NUM);
	            } catch (IOException e) {
	                Logger.getLogger(Constant.class.getName()).log(Level.SEVERE, null, e);
	            }
	        }
            this.type = "inteiro";
			break;
		    
		    case Tag.LI: {
	            
		    	try {
	                eat(Tag.LI);
	            } catch (IOException e) {
	                Logger.getLogger(Constant.class.getName()).log(Level.SEVERE, null, e);
	            }
	        }
            this.type = "literal";
			break;
	
		    default:
	                System.out.println("Erro sintático na linha " + Lexer.line + ":\n" + "Constante númerica esperada porém não encontrada.");
			error();
    	}
    }
    
}
