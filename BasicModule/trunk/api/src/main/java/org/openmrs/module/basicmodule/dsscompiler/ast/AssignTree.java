package org.openmrs.module.basicmodule.dsscompiler.ast;

import org.openmrs.module.basicmodule.dsscompiler.visitor.*;

public class AssignTree extends AST {

    public AssignTree() {
    }

    public Object accept(ASTVisitor v) {
        return v.visitAssignTree(this);
    }

}

