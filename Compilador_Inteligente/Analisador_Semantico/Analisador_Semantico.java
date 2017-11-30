package Analisador_Semantico;

import Analisador_Lexico.Analisador_Lexico;
import Analisador_Lexico.Lexer;
import Analisador_Lexico.Tabela_Simbolos;
import Analisador_Sintatico.Analisador_Sintatico;

import java.io.IOException;
import java.util.Scanner;


public class Analisador_Semantico {

    public static void main(String[] args) throws IOException
    {

        String nomeArquivo = "/home/mateus/IdeaProjects/Compilador_Int/Compilador_Inteligente/testes/teste3.txt";

        Sintatico.lexer = Lexer.getInst(nomeArquivo);
        Sintatico.token = Sintatico.lexer.scan();
        Sintatico.tabela_simbolos = Tabela_Simbolos.getInst();

        Program program = new Program(null);
        program.analiseSemantica();

        System.out.print("\n" + Sintatico.tabela_simbolos);
        System.out.print("\nAnálise Semântica realizada com sucesso!\n");
    }
}
