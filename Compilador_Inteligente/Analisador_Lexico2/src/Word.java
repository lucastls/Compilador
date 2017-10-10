package analisador_lexico;

public class Word extends Token
{
	private String lexeme = "";
	
        //Operadores de relação
        public static final Word ig = new Word("=", Tag.IG, Token.RL);
	public static final Word eq = new Word("==", Tag.EQ, Token.RL);
	public static final Word ne = new Word("!=", Tag.NE, Token.RL);
	public static final Word le = new Word("<=", Tag.LE, Token.RL);
	public static final Word ge = new Word(">=", Tag.GE, Token.RL);
	public static final Word gr = new Word(">", Tag.GR, Token.RL);
	public static final Word ls = new Word("<",Tag.LS, Token.RL);
	
        //Operadores de adição
	public static final Word or = new Word("||",Tag.OR, Token.ADL);
	public static final Word pl = new Word("+", Tag.PL, Token.ADL);
	public static final Word mn = new Word("-", Tag.MN, Token.ADL);
	
        //Operadores de multiplicação
	public static final Word an = new Word("&&",Tag.AN, Token.ML);
	public static final Word mp = new Word("*", Tag.MP, Token.ML);
	public static final Word dv = new Word("/", Tag.DV, Token.ML);
	
        //Operadores de pontuação + parênteses
	public static final Word pr = new Word("(", Tag.PR, Token.PO);
	public static final Word fp = new Word(")", Tag.FP, Token.PO);
	public static final Word vr = new Word(",", Tag.VR, Token.PO);
	public static final Word pv = new Word(";", Tag.PV, Token.PO);
        public static final Word as = new Word("\"", Tag.AS, Token.PO);
        
        //Comentário
        public static final Word c1 = new Word("//", Tag.C1, Token.C1);
        public static final Word cm = new Word("/* */", Tag.CM, Token.CM);
        
        
        //EOF
        public static final Word eof = new Word("EOF", Tag.EOF, Token.EOF);
	
	public Word(String s, int tag, String tipo)
	{
		super(tag, tipo);
		lexeme = s;
	}
	
	public String toString()
	{
		if(super.getTipo().equals(Token.KY))
			return "<" +lexeme+ ">";
                if(super.getTipo().equals(Token.PO))
                        return "< '" +lexeme+ "', " +super.getTipo()+ ">";
		return "<" +lexeme+ ", " +super.getTipo()+ ">";
	}
	
	public String getLexeme()
	{
		return lexeme;
	}
}
