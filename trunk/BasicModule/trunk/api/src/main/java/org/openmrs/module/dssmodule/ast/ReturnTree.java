package org.openmrs.module.dssmodule.ast;

import org.openmrs.module.dssmodule.visitor.ASTVisitor;

public class ReturnTree extends AST {

    public ReturnTree() {
    }

    public Object accept(ASTVisitor v) {
        return v.visitReturnTree(this);
    }

}

