package org.openmrs.module.dssmodule.ast;

import org.openmrs.module.dssmodule.visitor.ASTVisitor;

public class WhileTree extends AST {

    public WhileTree() {
    }

    public Object accept(ASTVisitor v) {
        return v.visitWhileTree(this);
    }
}

