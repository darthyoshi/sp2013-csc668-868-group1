package ast;

import lexer.Symbol;
import lexer.Token;
import visitor.*;

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

