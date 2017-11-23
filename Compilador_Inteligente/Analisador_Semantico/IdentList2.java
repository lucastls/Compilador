package Analisador_Semantico;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import Analisador_Lexico.Lexer;
import Analisador_Lexico.Tag;
import Analisador_Sintatico.Analisador_Sintatico;

/*
switch(token.tag)
        {
            case Tag.VR: eat(Tag.VR); identifier(); identList2(); break;
            case Tag.PV: break;
            default: error();
        }
*/

public class IdentList2 extends Analisador_Sintatico{

    Identifier identifier;
    IdentList2 identList2;

    public IdentList2(Analisador_Sintatico init){
        super(init);
    }

    @Override
    public void analiseSemantica() {

        switch (token.tag) {
            case Tag.VR:
                try {
                    eat(Tag.VR);
                } catch (IOException ex) {
                    Logger.getLogger(IdentList2.class.getName()).log(Level.SEVERE, null, ex);
                }

                identifier = new Identifier(this);
                identifier.declaration = true;
                identifier.analiseSemantica();
                identList2 = new IdentList2(this);
                identList2.analiseSemantica();
                break;

            case Tag.PV: break;

            default:
                System.out.println("Erro sintático na linha " + Lexer.line + ":\n" );//+ "Comando de atribução esperado, porém nao encontrado.");
                error();

        }
    }
}
