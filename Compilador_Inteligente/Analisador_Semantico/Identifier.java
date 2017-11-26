package Analisador_Semantico;

import Analisador_Lexico.Tabela_Simbolos;
import Analisador_Lexico.Tag;
import Analisador_Lexico.Lexer;
import Analisador_Lexico.Token;
import Analisador_Sintatico.Analisador_Sintatico;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/*identifier -> letter {letter|digit}
 private void identifier() throws IOException
    {
        switch(token.tag)
        {
            case Tag.ID: eat(Tag.ID); break;
            default: error();
        }
    }
*/

public class Identifier extends Sintatico {
	
	public Identifier(Sintatico init) {
		super(init);
	}

	@Override
	public void analiseSemantica() {

		switch(token.tag) {

			case Tag.ID:
				
				Token tok = tabela_simbolos.get(token.getTipo());
				
				if (isDecl) {
					
					this.type = init.type;
					
					if (tok != null) {
						
						tok = new Token(Tag.ID, token.getTipo());
						tok.tipo = this.type;
						tabela_simbolos.put(tok);
						lista.add(tok);
						
					}
					
				} else { 
					
					if (!tok.declaration) {
				
						System.out.println("Erro semântico na linha " + Lexer.line + ":\n" + "Utilização de identificador não definido '" + token.getTipo() + "'.");
						error();
					}
					
					this.type = tok.tipo;
					
				} 
				
				{
					try {
						eat(Tag.ID);
					} catch (IOException e) {
						Logger.getLogger(Identifier.class.getName()).log(Level.SEVERE, null, e);
					}
				}
				break;	
				
			default:
				System.out.println("Erro sintático na linha " + Lexer.line + ":\n" + "Identificador esperado, porém não encontrada.");
				error();

		}
	}	

}
