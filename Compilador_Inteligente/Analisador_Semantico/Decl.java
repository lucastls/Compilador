package Analisador_Semantico;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.List;

import Analisador_Lexico.Lexer;
import Analisador_Lexico.Tag;
import Analisador_Lexico.Token;
import Analisador_Semantico.Sintatico;

/*
switch(token.tag)
        {
            case Tag.INT:
            case Tag.STR: type(); identList(); eat(Tag.PV); break;
            default: error();
        }
*/

public class Decl extends Sintatico{
    Type type;
    IdentList identList;

    public Decl(Sintatico init){
        super(init);
    }

    @Override
    public void analiseSemantica() {

        switch (token.tag) {
            case Tag.INT:
            case Tag.STR:
                isDecl = true;
                type = new Type(this);
                type.analiseSemantica();

                setTipo(lista, type);
                lista.clear();


                identList = new IdentList(this);
                identList.analiseSemantica();

                isDecl = false;

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

    public void setTipo(List<Token> lista, Type tipo)
    {
        for(Token tok : lista)
        {
            tok.tipo = tipo.type;
            tok.declaration = true;
        }
    }
}
