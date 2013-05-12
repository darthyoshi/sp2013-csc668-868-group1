package org.openmrs.module.dssmodule.ast;

import org.openmrs.module.dssmodule.visitor.ASTVisitor;

public class FieldRefTree extends AST {

    public FieldRefTree() {
    }

    public Object accept(ASTVisitor v) {
        return v.visitFieldRefTree(this);
    }

}

