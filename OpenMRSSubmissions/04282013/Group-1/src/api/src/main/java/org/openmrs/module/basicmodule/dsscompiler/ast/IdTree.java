package org.openmrs.module.basicmodule.dsscompiler.ast;

import org.openmrs.module.basicmodule.dsscompiler.lexer.Symbol;
import org.openmrs.module.basicmodule.dsscompiler.lexer.Token;
import org.openmrs.module.basicmodule.dsscompiler.visitor.*;

public class IdTree extends AST {
    private Symbol symbol;
    private int frameOffset = -1;   // stack location for codegen

/**
 *  @param tok - record the symbol from the token Symbol
*/
    public IdTree(Token tok) {
        this.symbol = tok.getSymbol();
    }

    public Object accept(ASTVisitor v) {
        return v.visitIdTree(this);
    }

    public Symbol getSymbol() {
        return symbol;
    }

/**
 *  @param i is the offset for this variable as determined by the code generator
*/
    public void setFrameOffset(int i) {
        frameOffset = i;
    }

/**
 *  @return the frame offset for this variable - used by codegen
*/
    public int getFrameOffset() {
        return frameOffset;
    }

}

