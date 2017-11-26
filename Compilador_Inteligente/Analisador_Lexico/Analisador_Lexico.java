package Analisador_Lexico;

import java.io.*;
import java.util.Scanner;

public class Analisador_Lexico
{
	public static void main(String[] args)
	{
		String nomeArquivo = "/home/mateus/IdeaProjects/Compilador_Int/Compilador_Inteligente/testes/teste.txt";
		try{
			Lexer lexer = new Lexer(nomeArquivo);
			Token out = lexer.scan();
			//lexer.printTable();
			while(out.getTag()!=63555)
                        {
                            //System.out.println(lexer.Words.toString());
                            System.out.println(out+ " - ID: " +out.getTag());
                            out = lexer.scan();
                            
                            
                        }
                        //System.out.println(lexer.Words.toString());
		}catch(IOException e)
		{
			System.err.println(e.getMessage());
		}

		System.out.println("\n");
		System.out.println(Tabela_Simbolos.getTabSimb());
	}
}
