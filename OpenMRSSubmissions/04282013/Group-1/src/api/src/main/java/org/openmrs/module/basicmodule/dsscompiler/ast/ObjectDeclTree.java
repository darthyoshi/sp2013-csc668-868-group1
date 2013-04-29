package org.openmrs.module.basicmodule.dsscompiler.ast;

import org.openmrs.module.basicmodule.dsscompiler.visitor.*;

public class ObjectDeclTree extends AST {

    public ObjectDeclTree() {
    }

 
    public Object accept(ASTVisitor v) {
        return v.visitObjectDeclTree(this);
    }

}