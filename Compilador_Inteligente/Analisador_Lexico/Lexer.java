package Analisador_Lexico;
import java.io.*;
import java.util.*;

public class Lexer
{
	public static int line = 1;
	private char ch = ' ';
	private FileReader file;
	private static Lexer inst;

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
		reserve(new Token(Tag.PRG,"program"));
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
		reserve(new Token(Tag.EX, "not"));
		reserve(new Token(Tag.AN, "and"));
		reserve(new Token(Tag.OR, "or"));
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
			String literal = "\"";
			readch();

			while (ch != '\n' && ch != '"')
			{
				literal += ch;
				readch();
			}

			if(ch == '"')
			{
				readch();
				literal += "\"";
				return new Token(Tag.LI, literal);
			}
			else
			{
				System.out.println("Literal inválido na linha " +line);
				System.exit(0);
			}
		}
		
		//Números
		String number = "";
		if(Character.isDigit(ch))
		{
			if(ch == '0')
			{
				number += ch;
				readch();

				if(!Character.isLetterOrDigit(ch))
				{
					return new Token(Tag.NUM, number);
				}
				else
				{
					return null;
				}
			}
			else
			{
				while (Character.isDigit(ch))
				{
					number += ch;
					readch();
				}

				return new Token(Tag.NUM, number);
			}
		}
		
		//Identificadores
		StringBuffer s = new StringBuffer();
		if(Character.isLetter(ch))
		{

			do{
				s.append(ch);
				readch();
			}while(Character.isLetterOrDigit(ch));
			
			String id = s.toString();
			Token token = tabela_simbolos.get(id);
            if(token != null)
				return token;
			token = new Token(Tag.ID, id);
			//if(tabela_simbolos.get(token.lexeme) != null)
				tabela_simbolos.put(token);

			return token;
		}
		else if(ch == '_')
		{
			s.append(ch);
			readch();

			if(Character.isLetterOrDigit(ch))
			{
				do
				{
					s.append(ch);
					readch();
				}while (Character.isLetterOrDigit(ch));

				String id = s.toString();
				Token tok = tabela_simbolos.get(id);

				if(tok != null)
					return tok;

				tok = new Token(Tag.ID, id);
				tabela_simbolos.put(tok);

				return tok;
			}
		}
		
		if(!(file.read() == -1))
			error();
			
		return null;
	}
	
	

}
