package Analisador_Semantico;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import Analisador_Lexico.Lexer;
import Analisador_Lexico.Tag;

/*
switch(token.tag)
        {
            case Tag.INT:
            case Tag.STR: decl(); declList2(); break;
            default: error();
        }
*/

public class DeclList extends Analisador_Sintatico {

    Decl decl;
    DeclList1 declList1;

    public DeclList(Analisador_Sintatico head){
        super(head);
    }

    @Override
    public void analiseSemantica() {

        switch (token.tag) {

            case Tag.INT:
                decl = new Decl(this);
                decl.analise();

                try {
                    eat(';');
                } catch (IOException ex) {
                    Logger.getLogger(DeclList.class.getName()).log(Level.SEVERE, null, ex);
                }

                declList2 = new DeclList2(this);
                declList2.analiseSemantica();
                break;

            default:
                System.out.println("Erro sintático na linha " + Lexer.line + ":\n" );//+ "Comando de atribução esperado, porém nao encontrado.");
                erro();
        }
    }
}
