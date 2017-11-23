package Analisador_Semantico;

import Analisador_Lexico.Lexer;
import Analisador_Lexico.Tag;
import Analisador_Sintatico.Analisador_Sintatico;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
switch(token.tag)
        {
            case Tag.PL: eat(Tag.PL); break;/
            case Tag.MN: eat(Tag.MN); break;
            case Tag.OR: eat(Tag.OR); break;
            default: error();
        }
*/

public class Addop extends Analisador_Sintatico{
    public Addop(Analisador_Sintatico init){
        super(init);
    }

    @Override
    public void analiseSemantica() {

        switch (token.tag) {
            case Tag.PL:
                try {
                    eat(Tag.PL);
                } catch (IOException ex) {
                    Logger.getLogger(Addop.class.getName()).log(Level.SEVERE, null, ex);
                }
                this.type=init.type;
                break;

            case Tag.MN:
                try {
                    eat(Tag.MN);
                } catch (IOException ex) {
                    Logger.getLogger(Addop.class.getName()).log(Level.SEVERE, null, ex);
                }
                this.type=init.type;
                break;

            case Tag.OR:
                try {
                    eat(Tag.OR);
                } catch (IOException ex) {
                    Logger.getLogger(Addop.class.getName()).log(Level.SEVERE, null, ex);
                }
                this.type="bool";
                break;

            default:
                System.out.println("Erro sintático na linha " + Lexer.line + ":\n" );//+ "Comando de atribução esperado, porém nao encontrado.");
                error();
        }
    }
}
