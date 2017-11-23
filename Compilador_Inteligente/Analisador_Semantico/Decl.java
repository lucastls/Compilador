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
            case Tag.INT:
            case Tag.STR: type(); identList(); eat(Tag.PV); break;
            default: error();
        }
*/

public class Decl extends Analisador_Sintatico{
    Type type;
    IdentList identList;

    public Decl(Analisador_Sintatico init){
        super(init);
    }

    @Override
    public void analiseSemantica() {

        switch (token.tag) {
            case Tag.INT:
            case Tag.STR:
                type = new Type(this);
                type.analiseSemantica();

                identList = new IdentList(this);
                identList.analiseSemantica();

                try {
                    eat(Tag.PV);
                } catch (IOException ex) {
                    Logger.getLogger(Decl.class.getName()).log(Level.SEVERE, null, ex);
                }

                break;
            default:
                System.out.println("Erro sintático na linha " + Lexer.line + ":\n" );//+ "Comando de atribução esperado, porém nao encontrado.");
                error();
        }
    }
}
