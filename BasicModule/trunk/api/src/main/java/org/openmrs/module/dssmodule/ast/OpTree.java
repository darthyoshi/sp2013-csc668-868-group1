package org.openmrs.module.dssmodule.ast;

import org.openmrs.module.dssmodule.visitor.ASTVisitor;
import org.openmrs.module.dssmodule.lexer.Symbol;
import org.openmrs.module.dssmodule.lexer.Token;

public class OpTree extends AST {
    private Symbol symbol;

/**
 *  @param tok contains the Symbol which indicates the specific relational operator
*/
    public OpTree(Token tok) {
        this.symbol = tok.getSymbol();
    }

    public Object accept(ASTVisitor v) {
        return v.visitOpTree(this);
    }

    public Symbol getSymbol() {
        return symbol;
    }

}

