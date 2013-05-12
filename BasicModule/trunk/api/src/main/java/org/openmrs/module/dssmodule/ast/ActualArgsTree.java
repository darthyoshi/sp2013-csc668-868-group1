package org.openmrs.module.dssmodule.ast;

import org.openmrs.module.dssmodule.visitor.ASTVisitor;

public class ActualArgsTree extends AST {

    public ActualArgsTree() {
    }

    public Object accept(ASTVisitor v) {
        return v.visitActualArgsTree(this);
    }

}

