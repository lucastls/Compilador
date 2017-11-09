package Analisador_Lexico;

public class Num extends Token
{
	public final int value;
	
	public Num(int value)
	{
		super(Tag.NUM, NUM);
		this.value = value;
	}
	
	@Override
	public String toString()
	{
		return "<" +super.getTipo()+ ", " +value+ ">";
	}
}
