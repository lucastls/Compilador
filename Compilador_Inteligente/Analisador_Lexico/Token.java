package Analisador_Lexico;

public class Token
{
	public final int tag;
	public String tipo;
	public String lexeme;
	public boolean declaration;

	public Token(int t, String lexeme)
	{
		tag = t;
		this.lexeme = lexeme;
		this.tipo = "";
	}
	
	public String toString()
	{
		return "<" +lexeme + ", "+ tag+ ">";
	}
	
	public Integer getTag()
	{
		return tag;
	}
	
	public String getLexeme()
	{
		return lexeme;
	}
}
