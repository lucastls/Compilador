package Analisador_Semantico;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import Analisador_Lexico.Lexer;
import Analisador_Lexico.Tag;
import Analisador_Semantico.Sintatico;

/*
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
                decl.analiseSemantica();
                declList2.analiseSemantica();
                break;

            default:
                System.out.println("Erro sintático na linha " + Lexer.line + ":\n" );//+ "Comando de atribução esperado, porém nao encontrado.");
                error();
        }
    }
}
