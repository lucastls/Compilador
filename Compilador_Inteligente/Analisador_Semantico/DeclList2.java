package Analisador_Semantico;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import Analisador_Lexico.Lexer;
import Analisador_Lexico.Tag;
import Analisador_Semantico.Sintatico;


/*
private void declList2() throws IOException
    {
        switch(token.tag)
        {
            case Tag.INT:
            case Tag.STR: decl(); declList2(); break;
            case Tag.ID: break;
            case Tag.IF: break;
            case Tag.DO: break;
            case Tag.SCN: break;
            case Tag.PRT: break;
            default: error();
        }
    }
*/

public class DeclList2 extends Sintatico {

    Decl decl;
    DeclList2 declList2;

    public DeclList2(Sintatico init){
        super(init);
    }

    @Override
    public void analiseSemantica() {

        switch (token.tag) {

            case Tag.INT:
                decl = new Decl(this);
                decl.analiseSemantica();

                try {
                    eat(Tag.PV);
                } catch (IOException ex) {
                    Logger.getLogger(DeclList2.class.getName()).log(Level.SEVERE, null, ex);
                }

                declList2 = new DeclList2(this);
                decl.analiseSemantica();
                declList2.analiseSemantica();
                break;

            case Tag.STR:
                decl = new Decl(this);
                decl.analiseSemantica();

                try {
                    eat(Tag.PV);
                } catch (IOException ex) {
                    Logger.getLogger(DeclList2.class.getName()).log(Level.SEVERE, null, ex);
                }

                declList2 = new DeclList2(this);
                decl.analiseSemantica();
                declList2.analiseSemantica();
                break;

            case Tag.ID: break;
            case Tag.IF: break;
            case Tag.DO: break;
            case Tag.SCN: break;
            case Tag.PRT: break;

            default:
                System.out.println("Erro sintático na linha " + Lexer.line + ":\n" );//+ "Comando de atribução esperado, porém nao encontrado.");
                error();
        }
    }
}
