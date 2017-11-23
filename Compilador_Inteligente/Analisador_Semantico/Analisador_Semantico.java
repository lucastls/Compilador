package Analisador_Semantico;

import Analisador_Lexico.Lexer;
import Analisador_Sintatico.Analisador_Sintatico;

import java.io.IOException;

public class Analisador_Semantico {

    try{
        for (String filepath: args) {
            Lexer lexer = new Lexer(filepath);
            Analisador_Sintatico Sintatico = new Analisador_Sintatico(lexer);
            Sintatico.initAnalise();
            System.err.println("\n");
        }

    }catch(IOException e)
    {
        System.err.println(e.getMessage());
    }
}
