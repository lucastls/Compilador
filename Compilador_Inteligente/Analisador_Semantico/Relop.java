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
            case Tag.EQ: eat(Tag.EQ); break;
            case Tag.GR: eat(Tag.GR); break;
            case Tag.GE: eat(Tag.GE); break;
            case Tag.LS: eat(Tag.LS); break;
            case Tag.LE: eat(Tag.LE); break;
            case Tag.NE: eat(Tag.NE); break;
            default: error();
        }
*/

public class Relop extends Sintatico{
    public Relop(Sintatico init){
        super(init);
    }

    @Override
    public void analiseSemantica() {

        switch (token.tag) {
            case Tag.EQ:
                try {
                    eat(Tag.EQ);
                } catch (IOException ex) {
                    Logger.getLogger(Relop.class.getName()).log(Level.SEVERE, null, ex);
                }
                this.type="bool";
                break;

            case Tag.GR:
                try {
                    eat(Tag.GR);
                } catch (IOException ex) {
                    Logger.getLogger(Relop.class.getName()).log(Level.SEVERE, null, ex);
                }
                this.type="bool";
                break;

            case Tag.GE:
                try {
                    eat(Tag.GE);
                } catch (IOException ex) {
                    Logger.getLogger(Relop.class.getName()).log(Level.SEVERE, null, ex);
                }
                this.type="bool";
                break;

            case Tag.LS:
                try {
                    eat(Tag.LS);
                } catch (IOException ex) {
                    Logger.getLogger(Relop.class.getName()).log(Level.SEVERE, null, ex);
                }
                this.type="bool";
                break;

            case Tag.LE:
                try {
                    eat(Tag.LE);
                } catch (IOException ex) {
                    Logger.getLogger(Relop.class.getName()).log(Level.SEVERE, null, ex);
                }
                this.type="bool";
                break;


            case Tag.NE:
                try {
                    eat(Tag.NE);
                } catch (IOException ex) {
                    Logger.getLogger(Relop.class.getName()).log(Level.SEVERE, null, ex);
                }
                this.type="bool";
                break;

            default:
                System.out.println("Erro sintático na linha " + Lexer.line + ":\n" );//+ "Comando de atribução esperado, porém nao encontrado.");
                error();
        }
    }
}
