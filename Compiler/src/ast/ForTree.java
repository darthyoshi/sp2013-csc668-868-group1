package ast;

import visitor.*;

public class ForTree extends AST {

    public ForTree() {
    }

 
    public Object accept(ASTVisitor v) {
        return v.visitForTree(this);
    }

}