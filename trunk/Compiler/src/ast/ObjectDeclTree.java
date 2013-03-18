package ast;

import visitor.*;

public class ObjectDeclTree extends AST {

    public ObjectDeclTree() {
    }

 
    public Object accept(ASTVisitor v) {
        return v.visitObjectDeclTree(this);
    }

}