package Analisador_Semantico;

import Analisador_Lexico.Tag;
import Analisador_Lexico.Token;
import Analisador_Lexico.Lexer;
import Analisador_Lexico.Tabela_Simbolos;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class Sintatico {

    public Sintatico init;
    public String type;
    public boolean declaration;
    public static Tabela_Simbolos tabela_simbolos = Tabela_Simbolos.getInst();
    public static List<Token> lista = new ArrayList<Token>();
    public static Lexer lexer;
    public static Token token;

    public static int contChamada = 0;

    public static boolean isDecl = false;

    protected Sintatico(Sintatico init)
    {
        this.init = init;
        this.type = "void";
        this.declaration = false;
        contChamada++;
    }

    protected void error()
    {
        System.exit(0);
    }

    protected void eat(int tag) throws IOException
    {

        if(token.tag == tag)
        {
            debugg();
            System.out.print("eat "+token);
            token = lexer.scan();
        }
        else
        {
            System.out.println("Erro Sintático na linha " + Lexer.line + ":\n" + "Token esperado: \"" + token.getTag() + "\"\n" + "Token encontrado: \"" + Tag.getLexeme(tag) + "\"");
            error();
        }
    }

    public void debugg()
    {
        for(int i = 0; i < contChamada; i++)
        {
            System.out.print("  ");
        }
        System.out.print("\n");
    }

    public abstract void analiseSemantica();
}
