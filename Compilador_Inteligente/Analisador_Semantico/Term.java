package Analisador_Semantico;

import Analisador_Lexico.Tag;
import Analisador_Lexico.Lexer;
import Analisador_Sintatico.Analisador_Sintatico;
import Util.Util;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/*term -> FatorA | term mulop FatorA 
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

*/

public class Term extends Sintatico {

    FatorA fatorA;
    Term2 term2;

    public Term(Sintatico init) {
        super(init);
    }

    @Override
    public void analiseSemantica() {
       
    	switch (token.tag) {
            
            case Tag.LI:
            case Tag.ID:
	    case Tag.NUM:
            case Tag.PR:
            case Tag.EX:
            case Tag.MN:
                fatorA = new FatorA(this);
                fatorA.analiseSemantica();
                this.type = fatorA.type;
                term2 = new Term2(this);
                term2.analiseSemantica();
                
                if (!term2.type.equals("void")) {
                    
                	if (!Util.isNumeric(fatorA.type) || !Util.isNumeric(term2.type)) {
                        
                		if (!fatorA.type.equals(term2.type)) {
                        
                            System.out.println("Erro semântico na linha " + Lexer.line + ":\n" + "Operador incompaível com o type do operando.");
                            error();
                        }
                    }
                }
                
                if (Util.isNumeric(term2.type) && Util.isNumeric(fatorA.type)) {
                    this.type = Util.getNumericType(fatorA.type, term2.type);
                } else {
                    this.type = term2.type;
                } 
                
                break;
                
            default:
                System.out.println("Erro sintático na linha " + Lexer.line + ":\n"+ "Expressão esperada não encontrada.");
                error();
        }
    }

}
