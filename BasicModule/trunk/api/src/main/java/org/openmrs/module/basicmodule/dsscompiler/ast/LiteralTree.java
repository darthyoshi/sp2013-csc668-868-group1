package org.openmrs.module.basicmodule.dsscompiler.ast;

import org.openmrs.module.basicmodule.dsscompiler.lexer.Symbol;
import org.openmrs.module.basicmodule.dsscompiler.lexer.Token;
import org.openmrs.module.basicmodule.dsscompiler.visitor.*;

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

