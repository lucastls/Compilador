package analisador_lexico;

public class Num extends Token
{
	public final int value;
	
	public Num(int value)
	{
		super(Tag.NUM, Token.NUM);
		this.value = value;
	}
	
	@Override
	public String toString()
	{
		return "<" +super.getTipo()+ ", " +value+ ">";
	}
}
