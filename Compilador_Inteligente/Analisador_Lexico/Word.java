package Analisador_Lexico;

public class Word extends Token
{
	private String lexeme = "";
	
        //Operadores de relação
        public static final Word ig = new Word("=", Tag.IG, RL);
	public static final Word eq = new Word("==", Tag.EQ, RL);
	public static final Word ne = new Word("!=", Tag.NE, RL);
	public static final Word le = new Word("<=", Tag.LE, RL);
	public static final Word ge = new Word(">=", Tag.GE, RL);
	public static final Word gr = new Word(">", Tag.GR, RL);
	public static final Word ls = new Word("<",Tag.LS, RL);
	
        //Operadores de adição
	public static final Word or = new Word("||",Tag.OR, ADL);
	public static final Word pl = new Word("+", Tag.PL, ADL);
	public static final Word mn = new Word("-", Tag.MN, ADL);
	
        //Operadores de multiplicação
	public static final Word an = new Word("&&",Tag.AN, ML);
	public static final Word mp = new Word("*", Tag.MP, ML);
	public static final Word dv = new Word("/", Tag.DV, ML);
	
        //Operadores de pontuação + parênteses
	public static final Word pr = new Word("(", Tag.PR, PO);
	public static final Word fp = new Word(")", Tag.FP, PO);
	public static final Word vr = new Word(",", Tag.VR, PO);
	public static final Word pv = new Word(";", Tag.PV, PO);
   // public static final Word as = new Word("\"", Tag.AS, PO);
        
	//Comentário
	public static final Word c1 = new Word("//", Tag.C1, C1);
	public static final Word cm = new Word("/* */", Tag.CM, CM);


	//EOF
	public static final Word eof = new Word("EOF", Tag.EOF, EOF);

	public Word(String s, int tag, String tipo)
	{
		super(tag, tipo);
		lexeme = s;
	}
	
	public String toString()
	{
		if(super.getTipo().equals(KY))
			return "<" +lexeme+ ">";
                if(super.getTipo().equals(PO))
                        return "< '" +lexeme+ "', " +super.getTipo()+ ">";
		return "<" +lexeme+ ", " +super.getTipo()+ ">";
	}
	
	public String getLexeme()
	{
		return lexeme;
	}
}
