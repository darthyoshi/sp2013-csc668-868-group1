package org.openmrs.module.dssmodule.ast;

import org.openmrs.module.dssmodule.visitor.ASTVisitor;

public class ObjectDeclTree extends AST {

    public ObjectDeclTree() {
    }

 
    public Object accept(ASTVisitor v) {
        return v.visitObjectDeclTree(this);
    }

}