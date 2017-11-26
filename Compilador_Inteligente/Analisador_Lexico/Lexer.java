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
	private Tabela_Simbolos tabela_simbolos = Tabela_Simbolos.getTabSimb();

	//Recebe um símbolo e o insere na tabela
	private void reserve(Token token)
	{
		tabela_simbolos.put(token);
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
		insereReservadas();
	}

	private void insereReservadas()
	{
		//Inserção de palavras reservadas na tabela de símbolos
		reserve(new Token(Tag.PRG,"prg"));
		reserve(new Token(Tag.END, "end"));
		reserve(new Token(Tag.INT, "int"));
		reserve(new Token(Tag.STR, "string"));
		reserve(new Token(Tag.IF, "if"));
		reserve(new Token(Tag.THEN, "then"));
		reserve(new Token(Tag.ELSE, "else"));
		reserve(new Token(Tag.DO, "do"));
		reserve(new Token(Tag.WHL, "while"));
		reserve(new Token(Tag.SCN, "scan"));
		reserve(new Token(Tag.PRT, "print"));
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
                                    return new Token(Tag.DV, "/");
                        }
                        else
				break;
		}
		
		//Operadores
		switch(ch)
		{
			case '&':	if(readch('&'))
						return new Token(Tag.AN, "&&");
			case '|':	if(readch('|'))
						return new Token(Tag.OR, "||");
			case '=':	if(readch('='))
						return new Token(Tag.EQ, "==");
                                        else
                                                return new Token(Tag.IG, "=");
			case '<':	if(readch('='))
						return new Token(Tag.LE, "<=");
					else
						return new Token(Tag.LS, "<");
			case '>':	if(readch('='))
						return new Token(Tag.GE, ">=");
					else
						return new Token(Tag.GR, ">");
			case '(':	ch = ' ';
					return new Token(Tag.PR, "(");
			case ')':	ch = ' ';
					return new Token(Tag.FP, ")");
			case '+':	ch = ' ';
					return new Token(Tag.PL, "+");
			case '-':	ch = ' ';
					return new Token(Tag.MN, "-");
			case '*':	ch = ' ';
					return new Token(Tag.MP, "*");
			case '/':	ch = ' ';
					return new Token(Tag.DV, "/");
			case ',':	ch = ' ';
					return new Token(Tag.VR, ",");
			case ';':	ch = ' ';
					return new Token(Tag.PV, ";");
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
			Token token = tabela_simbolos.get(st);
                       			
			if(token != null)
				return token;

			token = new Token(Tag.ID, st);
			tabela_simbolos.put(token);
			return token;
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
			//Word w = Words.get(st);
			Token token = tabela_simbolos.get(st);
                        //System.out.println("Palavra: " +w+ "ID:" +w.getLexeme());
			if(token != null)
				return token;
			//w = new Word(st, Tag.ID, Token.ID);
			//Words.put(st,w);
			token = new Token(Tag.ID, st);
			tabela_simbolos.put(token);

			return token;
		}
		
		if(!(file.read() == -1))
			error();
			
		return Word.eof;
	}
	
	

}
