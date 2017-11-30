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

			while(true)
			{
				Token out = lexer.scan();
				if(out == null)
					break;
				System.out.print(out + "\n");
			}
		}catch(IOException e)
		{
			System.err.println(e.getMessage());
		}

		System.out.println("\n");
		System.out.println(Tabela_Simbolos.getTabSimb());
	}
}
