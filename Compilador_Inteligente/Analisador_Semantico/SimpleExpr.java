package Analisador_Semantico;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import Analisador_Lexico.Lexer;
import Analisador_Lexico.Tag;
import Analisador_Semantico.Sintatico;
import Util.Util;

/*
simple-expr ::= term simple-expr2

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

public class SimpleExpr extends Sintatico{

    Term term;
    SimpleExpr2 simpleExpr2;

    public SimpleExpr(Sintatico init){
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
                term.analiseSemantica();

                this.type = term.type;

                simpleExpr2 = new SimpleExpr2(this);
                simpleExpr2.analiseSemantica();

                if (!simpleExpr2.type.equals("void")) {

                    if (!Util.isNumeric(term.type) || !Util.isNumeric(simpleExpr2.type)) {

                        if (!term.type.equals(simpleExpr2.type)) {
                            System.out.println("Erro semantico na linha " + lexer.line + ":\n" + "Operador incompatível com type do operando.");
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
