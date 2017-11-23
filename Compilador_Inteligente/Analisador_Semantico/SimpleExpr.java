package Analisador_Semantico;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import Analisador_Lexico.Lexer;
import Analisador_Lexico.Tag;
import Analisador_Sintatico.Analisador_Sintatico;

/*
switch(token.tag)
        {
            case Tag.LI:
            case Tag.ID:
            case Tag.NUM:
            case Tag.PR:
            case Tag.EX:
            case Tag.MN:  term(); simpleExpr2(); break;
            default:error();

        }
 */

public class SimpleExpr extends Analisador_Sintatico{

    Term term;
    SimpleExpr2 simpleExpr2;

    public SimpleExpr(Analisador_Sintatico init){
        super(init);
    }

    @Override
    public void analiseSemantica() {

        switch (token.tag) {
            case Tag.LI:
            case Tag.ID:
            case Tag.NUM:
            case Tag.PR:
            case Tag.EX:
            case Tag.MN:

                term = new Term(this);
                simpleExpr.analiseSemantica();

                expression2 = new Expression2(this);
                expression2.analiseSemanticaSemantica();

                if (!simpleExpr2.tipo.equals("void")) {

                    if (!Util.isNumeric(term.tipo) || !Util.isNumeric(simpleExpr2.tipo)) {

                        if (!term.type.equals(simpleExpr2.type)) {
                            System.out.println("Erro semantico na linha " + Lexico.numLinha + ":\n" + "Operador incompatível com tipo do operando.");
                            error();
                        }
                    }
                }

                if (Util.isNumeric(term.type) && Util.isNumeric(simpleExpr2.type)) {
                    this.type = Util.getNumericType(term.type, simpleExpr2.type);
                } else {
                    this.type = simpleExpr2.type;
                }

                break;

            default:
                System.out.println("Erro sintático na linha " + Lexer.line + ":\n" + "Expressão esperada não encontrada.");
                error();
        }
    }
}
