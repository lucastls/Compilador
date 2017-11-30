package Analisador_Semantico;

import Analisador_Lexico.Tag;
import Analisador_Lexico.Lexer;
import Analisador_Sintatico.Analisador_Sintatico;
import Util.Util;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/*term2 ::= mulop factor-a term2 | lambda
private void term2() throws IOException
    {
        switch(token.tag)
        {
            case Tag.MP:
            case Tag.DV:
            case Tag.AN: mulop(); fatorA(); term2(); break;
            case Tag.PV: break;
            case Tag.PL: break;
            case Tag.MN: break;
            case Tag.OR: break;
            case Tag.THEN: break;
            case Tag.END: break;
            case Tag.FP: break;
            case Tag.EQ: break;
            case Tag.GR: break;
            case Tag.GE: break;
            case Tag.LS: break;
            case Tag.LE: break;
            case Tag.NE: break;
            default: error();
        }
    }
*/

public class Term2 extends Sintatico {

    Mulop mulop;
    FatorA fatorA;
    Term2 term2;

    public Term2(Sintatico init) {
        
    	super(init);
        this.type = init.type;
        
    }

    @Override
    public void analiseSemantica() {


        switch (token.tag)
        {
            case Tag.MP:
            case Tag.DV:
            case Tag.AN:
                mulop = new Mulop(this);
                mulop.analiseSemantica();
                this.type = mulop.type;

                fatorA = new FatorA(this);
                fatorA.analiseSemantica();

                if (!Util.isNumeric(mulop.type) || !Util.isNumeric(fatorA.type)) {

                    if (!mulop.type.equals(fatorA.type)) {

                        System.out.println("Erro semântico na linha " + Lexer.line + ":\n" + "Operador incompatível com o tipo do operando.");
                        error();
                    }
                }

                term2 = new Term2(this);
                term2.analiseSemantica();

                if (!term2.type.equals("void")) {

                    if (!mulop.type.equals(term2.type)) {
                        System.out.println("Erro semântico na linha " + Lexer.line + ":\n" + "Operador incompaível com o tipo do operando.");
                        error();
                    }

                }

                if (Util.isNumeric(mulop.type)) {
                    this.type = Util.getNumericType(fatorA.type, term2.type);
                } else {
                    this.type = mulop.type;
                }

                break;

            case Tag.PV: break;
            case Tag.PL: break;
            case Tag.MN: break;
            case Tag.OR: break;
            case Tag.THEN: break;
            case Tag.END: break;
            case Tag.FP: break;
            case Tag.EQ: break;
            case Tag.GR: break;
            case Tag.GE: break;
            case Tag.LS: break;
            case Tag.LE: break;
            case Tag.NE: break;

            default:
                System.out.println("Erro sintático na linha " + Lexer.line + ":\n"+ "Expressão esperada não encontrada.");
                error();
        }

    }

}
