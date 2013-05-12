package org.openmrs.module.dssmodule.ast;

import org.openmrs.module.dssmodule.visitor.ASTVisitor;

public class CallTree extends AST {

    public CallTree() {
    }

    public Object accept(ASTVisitor v) {
        return v.visitCallTree(this);
    }

}

