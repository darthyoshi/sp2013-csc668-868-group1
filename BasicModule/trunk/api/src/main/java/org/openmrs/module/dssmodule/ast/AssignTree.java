package org.openmrs.module.dssmodule.ast;

import org.openmrs.module.dssmodule.visitor.ASTVisitor;

public class AssignTree extends AST {

    public AssignTree() {
    }

    public Object accept(ASTVisitor v) {
        return v.visitAssignTree(this);
    }

}

