package Analisador_Lexico;
import java.io.*;
import java.util.*;

public class Lexer
{
	public static int line = 1;
	private char ch = ' ';
	private FileReader file;
	private Token token;
	private String lexeme;
	private static Lexer inst;

	protected static Hashtable<String, Word> Words = new Hashtable<String, Word>();

	//Recebe um símbolo e o insere na tabela
	private void reserve(Word w)
	{
		Words.put(w.getLexeme(), w);
	}

	//À partir do arquivo insere as palavras reservadas na tabela de símbolos
	public Lexer(String fileName) throws FileNotFoundException
	{
		try
		{
			file = new FileReader(fileName);
		}catch(FileNotFoundException e)
		 {
		 	System.out.println("Arquivo não encontrado");
		 	throw e;
		 }
		//Inserção de palavras reservadas na tabela de símbolos
		reserve(new Word ("program", Tag.PRG, Token.KY));
		reserve(new Word ("end", Tag.END, Token.KY));
		reserve(new Word ("int", Tag.INT, Token.KY));
		reserve(new Word ("string", Tag.STR, Token.KY));
		reserve(new Word ("if", Tag.IF, Token.KY));
		reserve(new Word ("then", Tag.THEN, Token.KY));
		reserve(new Word ("else", Tag.ELSE, Token.KY));
		reserve(new Word ("do", Tag.DO, Token.KY));
		reserve(new Word ("while", Tag.WHL, Token.KY));
		reserve(new Word ("scan", Tag.SCN, Token.KY));
		reserve(new Word ("print", Tag.PRT, Token.KY));
                //reserve(new Word ("\"", Tag.AS, Token.PO));
	}

	public static Lexer getInst(String fileName) throws FileNotFoundException
	{
		if(inst == null)
		{
			inst = new Lexer(fileName);
			return inst;
		}
		return inst;
	}
	
	//lê o próximo caractere (vide slide)
	private void readch() throws IOException
	{
		ch = (char) file.read();
	}
	
	//lê o próximo caractere e verifica se é igual a c
	private boolean readch(char c) throws IOException
	{
		readch();
		if(ch != c)
			return false;
		ch = ' ';
		return true;
	}
	
	public void error()
	{
		System.err.println("\nErro lexico linha " +line+ ": caractere não reconhecido: "+"\"" +ch+ "\"");
		System.exit(0);
	}
	
	public void error(String Msg)
	{
		System.err.println(Msg);
		System.exit(0);
	}
	
	public void printTable()
	{
		for (String name: Words.keySet())
		{
		    String key = name;//.toString();
		    String value = Words.get(name).toString();  
		    System.out.println(key + " " + value);  
		} 
	}
	
	public Token scan() throws IOException
	{
		for(;; readch())
		{
			if(ch == ' ' || ch == '\t' || ch == '\r' || ch == '\b')
				continue;
			else if(ch == '\n')
				line++;
                        //Comentário...
                        else if(ch == '/')
                        {
                                readch();
                                //...de uma linha
                                if(ch == '/')
                                {
                                        for(;; readch())
                                        {
                                                if(Integer.valueOf(ch) == 65535)
                                                        System.exit(0);
                                                if(ch == '\n')
                                                {
                                                        line++;
                                                        break;
                                                }
                                        }
                                }
                                //...de várias linhas
                                else if(ch == '*')
                                {
                                	int linhaInicComment = line;
                                	while(true)
									{
										readch();
										if(Integer.valueOf(ch) == 65535)
										{
											error("\nComentario iniciado na linha " +linhaInicComment+ " sem fim!");
										}
										if(ch == '\n')
										{
											line++;
											continue;
										}
										if(ch == '*')
										{
											if(readch('/'))
											{
												ch = ' ';
												return scan();
											}
											else
											{
												continue;
											}
										}
									}

                                	/*
									//char ch2 = ' ';
									int linhaInicComment = line;
									readch();

									while(true)//for(;; readch())
									{

										if(ch=='*')
										{

											if(readch('/'))
											{
												return Word.cm;
											}

										}

										if(ch == '\n')
											line++;
										readch();
										if(Integer.valueOf(ch) == 65535)
										{
											error("\nComentario iniciado na linha " +linhaInicComment+ " sem fim!");
										}
									}*/
                                }
                                else
                                    return Word.dv;
                        }
                        else
				break;
		}
		
		//Operadores
		switch(ch)
		{
			case '&':	if(readch('&'))
						return Word.an;
			case '|':	if(readch('|'))
						return Word.or;
			case '=':	if(readch('='))
						return Word.eq;
                                        else
                                                return Word.ig;
			case '<':	if(readch('='))
						return Word.le;
					else
						return Word.ls;
			case '>':	if(readch('='))
						return Word.ge;
					else
						return Word.gr;
			case '(':	ch = ' ';
					return Word.pr;
			case ')':	ch = ' ';
					return Word.fp;
			case '+':	ch = ' ';
					return Word.pl;
			case '-':	ch = ' ';
					return Word.mn;
			case '*':	ch = ' ';
					return Word.mp;
			case '/':	ch = ' ';
					return Word.dv;
			case ',':	ch = ' ';
					return Word.vr;
			case ';':	ch = ' ';
					return Word.pv;
		}
		
		//Literais
		if(ch == '"')
		{
			StringBuilder s = new StringBuilder();
			
			do{
				s.append(ch);
				readch();
				if(ch == '"')
				{
					s.append(ch);
					readch();
					break;
				}
				else if(ch == '\n')
				{
					error();
					return Word.eof;
					
				}
			}while((int)ch >= 0 && (int)ch <= 255);
			
			String st = s.toString();
			Word w = Words.get(st);
                       			
			if(w != null)
				return w;
			
			w = new Word(st, Tag.LI, Token.LI);
			Words.put(st,w);
			return w;
		}
		
		//Números
		if(Character.isDigit(ch))
		{
			int value = 0;
			do{
				value = 10*value + Character.digit(ch,10);
				readch();				
			}while(Character.isDigit(ch));
			return new Num(value);
		}
		
		//Identificadores
		if(Character.isLetter(ch))
		{
			StringBuffer s = new StringBuffer();
			do{
				s.append(ch);
				readch();
			}while(Character.isLetterOrDigit(ch));
			
			String st = s.toString();
			Word w = Words.get(st);
                        //System.out.println("Palavra: " +w+ "ID:" +w.getLexeme());
			if(w != null)
				return w;
			w = new Word(st, Tag.ID, Token.ID);
			Words.put(st,w);
			return w;
		}
		
		if(!(file.read() == -1))
			error();
			
		return Word.eof;
	}
	
	

}
