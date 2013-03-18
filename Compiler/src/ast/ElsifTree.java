package ast;

import visitor.*;

public class ElsifTree extends AST {

    public ElsifTree() {
    }

    public Object accept(ASTVisitor v) {
        return v.visitElsifTree(this);
    }

}
