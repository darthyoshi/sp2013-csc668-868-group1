package org.openmrs.module.dssmodule.ast;

import org.openmrs.module.dssmodule.visitor.ASTVisitor;

public class ObjectTree extends AST {

    public ObjectTree() {
    }

 
    public Object accept(ASTVisitor v) {
        return v.visitObjectTree(this);
    }

}