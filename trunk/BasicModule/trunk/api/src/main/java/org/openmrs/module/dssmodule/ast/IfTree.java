package org.openmrs.module.dssmodule.ast;

import org.openmrs.module.dssmodule.visitor.ASTVisitor;

public class IfTree extends AST {

    public IfTree() {
    }

    public Object accept(ASTVisitor v) {
        return v.visitIfTree(this);
    }

}

