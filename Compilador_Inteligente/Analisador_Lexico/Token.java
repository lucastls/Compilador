package Analisador_Lexico;

public class Token
{
	public final int tag;
	private final String tipo;
	public final static String ID = "id";
	public final static String NUM = "num";
	public final static String LI = "literal";
	public final static String RL = "relop";
	public final static String ADL = "addop";
	public final static String ML = "mulop";
	public final static String PO = "pontuacao";
	public final static String KY = "palavra_chave";
        public final static String EOF = "erro";
        public final static String C1 = "coment_1linha";
        public final static String CM = "coment_linhas";
	
	public Token(int t, String tipo)
	{
		tag = t;
		this.tipo = tipo;
	}
	
	public String toString()
	{
		return "<" +tipo+ ">";
	}
	
	public Integer getTag()
	{
		return tag;
	}
	
	public String getTipo()
	{
		return tipo;
	}
}
