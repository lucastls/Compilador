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
                AS = 34, // "
				EX = 33, // "!"
		
		//outros
		NUM = 273,
		ID = 274,
		LI = 275,
                C1 = 276,
                CM = 277,
                EOF = 63555;
}
