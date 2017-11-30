package Analisador_Lexico;

public class Tag
{
	public final static int
		//palavras reservadas
		PRG = 256,
		END = 257,
		INT = 258,
		STR = 259,
		IF = 260,
		THEN = 261,
		ELSE = 262,
		DO = 263,
		WHL = 264,
		SCN = 265,
		PRT = 266,
		
		//operadores e pontuação
		IG = 61, // "="
		EQ = 267, // "=="
		NE = 268, // "!="
		LE = 269, // "<="
		GE = 270, // ">="
		GR = 62, // ">"
		LS = 60, // "<"
		OR = 271, // "||"
		AN = 272, // "&&"
		PL = 43, // "+"
		MN = 45, // "-"
		MP = 42, // "*"
		DV = 47, // "/"
		PR = 40, // "("
		FP = 41, // ")"
		VR = 44, // ","
		PV = 59, // ";"
		EX = 33, // "!"
		
		//outros
		NUM = 273,
		ID = 274,
		LI = 275;

	public static String getLexeme(int tag)
	{
		String s;
		switch (tag)
		{
			case 256: s = "program";
				break;

			case 257: s = "end";
				break;

			case 258: s = "int";
				break;

			case 259: s = "string";
				break;

			case 260: s = "if";
				break;

			case 261: s = "then";
				break;

			case 262: s = "else";
				break;

			case 263: s = "do";
				break;

			case 264: s = "while";
				break;

			case 265: s = "scan";
				break;

			case 266: s = "print";
				break;

			case 61: s = "=";
				break;

			case 267: s = "==";
				break;

			case 268: s = "!=";
				break;

			case 269: s = "<=";
				break;

			case 270: s = ">=";
				break;

			case 62: s = ">";
				break;

			case 60: s = "<";
				break;

			case 271: s = "||";
				break;

			case 272: s = "&&";
				break;

			case 43: s = "+";
				break;

			case 45: s = "-";
				break;

			case 42: s = "*";
				break;

			case 47: s = "/";
				break;

			case 40: s = "(";
				break;

			case 41: s = ")";
				break;

			case 44: s = ",";
				break;

			case 59: s = ";";
				break;

			case 33: s = "!";
				break;

			case 273: s = "num";
				break;

			case 274: s = "id";
				break;

			case 275: s = "literal";
				break;

			default: s = String.valueOf((char) tag);
		}
		return s;
	}
}
