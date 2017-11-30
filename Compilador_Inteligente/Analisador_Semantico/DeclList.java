package Analisador_Semantico;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import Analisador_Lexico.Lexer;
import Analisador_Lexico.Tag;
import Analisador_Semantico.Sintatico;

/*
decl-list ::= decl decl-list2
switch(token.tag)
        {
            case Tag.INT:
            case Tag.STR: decl(); declList2(); break;
            default: error();
        }
*/

public class DeclList extends Sintatico {

    Decl decl;
    DeclList2 declList2;

    public DeclList(Sintatico init){
        super(init);
    }

    @Override
    public void analiseSemantica() {

        switch (token.tag) {

            case Tag.INT:
            case Tag.STR:
                decl = new Decl(this);
                decl.analiseSemantica();

                declList2 = new DeclList2(this);
                declList2.analiseSemantica();
                break;

            default:
                System.out.println("Erro semântico na linha " + Lexer.line + ":\n" );
                error();
        }
    }
}
