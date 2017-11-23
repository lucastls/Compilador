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
            case Tag.ID: identifier(); identList2(); break;
            default: error();
        }
*/

public class IdentList extends Analisador_Sintatico{

    Identifier identifier;
    IdentList2 identList2;

    public IdentList(Analisador_Sintatico init){
        super(init);
    }

    @Override
    public void analiseSemantica() {

        switch (token.tag) {
            case Tag.ID:

                this.type = init.type;

                identifier = new Identifier(this);
                identifier.declaration = true;
                identifier.analiseSemantica();

                identList2 = new IdentList2(this);
                identList2.analiseSemantica();

                break;
            default:
                System.out.println("Erro sintático na linha " + Lexer.line + ":\n" );//+ "Comando de atribução esperado, porém nao encontrado.");
                error();
        }
    }
}
