package org.openmrs.module.dssmodule.ast;

import org.openmrs.module.dssmodule.visitor.ASTVisitor;
import org.openmrs.module.dssmodule.lexer.Symbol;
import org.openmrs.module.dssmodule.lexer.Token;

public class LiteralTree extends AST {
    private Symbol symbol;

/**
 *  @param tok is the Token containing the String representation of the integer
 *  literal; we keep the String rather than converting to an integer value
 *  so we don't introduce any machine dependencies with respect to integer
 *  representations
*/
    public LiteralTree(Token tok) {
        this.symbol = tok.getSymbol();
    }

    public Object accept(ASTVisitor v) {
        return v.visitLiteralTree(this);
    }

    public Symbol getSymbol() {
        return symbol;
    }

}

