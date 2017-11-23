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
            case Tag.INT: eat(Tag.INT); break;
            case Tag.STR: eat(Tag.STR); break;
            default: error();
        }
*/

public class Type extends Sintatico{

    public Type(Sintatico init){
        super(init);
    }

    @Override
    public void analiseSemantica() {

        switch (token.tag) {
            case Tag.INT:
                try {
                    eat(Tag.INT);
                } catch (IOException ex) {
                    Logger.getLogger(Type.class.getName()).log(Level.SEVERE, null, ex);
                }
                this.type="inteiro";
                break;
            case Tag.STR:
                try {
                    eat(Tag.STR);
                } catch (IOException ex) {
                    Logger.getLogger(Type.class.getName()).log(Level.SEVERE, null, ex);
                }
                this.type="literal";
                break;
            default:
                System.out.println("Erro sintático na linha " + Lexer.line + ":\n" );//+ "Comando de atribução esperado, porém nao encontrado.");
                error();
        }
    }

}
