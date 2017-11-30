package Analisador_Lexico;

import java.util.Collection;
import java.util.HashMap;

public class Tabela_Simbolos {
    private static Tabela_Simbolos inst;
    private HashMap<String, Token> hashMap;

    private Tabela_Simbolos()
    {
        hashMap = new HashMap<String, Token>();
    }

    public static Tabela_Simbolos getTabSimb()
    {
        if(inst == null)
        {
            inst = new Tabela_Simbolos();
            return inst;
        }

        return inst;
    }

    public static Tabela_Simbolos getInst()
    {
        if(inst == null)
        {
            inst = new Tabela_Simbolos();
            return inst;
        }

        return inst;
    }

    public void put(Token token)
    {

        /*if (hashMap.containsKey(token.getLexeme())) {
            System.out.print("\n"+token.getLexeme());
            System.out.print("\nErro semantico na linha " + Lexer.line + ": Token da já existente na tabela.");
            System.exit(0);
        }
        else {
            hashMap.put(token.getLexeme(), token);
        }*/
        hashMap.put(token.getLexeme(), token);
    }

    public Token get(String tipo)
    {
        Token token = hashMap.get(tipo);
        return token;
    }

    @Override
    public String toString()
    {
        String str = "---------Tabela de Símbolos---------\n\n";
        Collection<Token> tok = hashMap.values();

        for (Token token: tok)
        {

            str = str + "<" + token.lexeme+ ", " + token.tag + ">" + "\n";
        }

        return str;
    }
}
