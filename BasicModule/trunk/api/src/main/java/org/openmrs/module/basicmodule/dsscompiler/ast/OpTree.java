package org.openmrs.module.basicmodule.dsscompiler.ast;

import org.openmrs.module.basicmodule.dsscompiler.lexer.Symbol;
import org.openmrs.module.basicmodule.dsscompiler.lexer.Token;
import org.openmrs.module.basicmodule.dsscompiler.visitor.*;

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

