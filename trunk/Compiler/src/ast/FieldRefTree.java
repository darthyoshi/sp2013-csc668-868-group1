package ast;

import visitor.*;

public class FieldRefTree extends AST {

    public FieldRefTree() {
    }

    public Object accept(ASTVisitor v) {
        return v.visitFieldRefTree(this);
    }

}

