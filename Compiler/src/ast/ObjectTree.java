package ast;

import visitor.*;

public class ObjectTree extends AST {

    public ObjectTree() {
    }

 
    public Object accept(ASTVisitor v) {
        return v.visitObjectTree(this);
    }

}