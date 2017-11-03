package Analisador_Lexico2;

import java.io.*;
import java.util.Scanner;

public class Analisador_Lexico2
{
	public static void main(String[] args)
	{
                Scanner in = new Scanner(System.in);
                System.out.print("Nome do arquivo de teste: ");
                String nome = in.next();
		try{
			Lexer lexer = new Lexer(nome);
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
		
	}
}
