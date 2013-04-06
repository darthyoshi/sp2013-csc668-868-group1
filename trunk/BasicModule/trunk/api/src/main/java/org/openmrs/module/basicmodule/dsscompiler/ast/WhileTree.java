package org.openmrs.module.basicmodule.dsscompiler.ast;

import org.openmrs.module.basicmodule.dsscompiler.visitor.*;

public class WhileTree extends AST {

    public WhileTree() {
    }

    public Object accept(ASTVisitor v) {
        return v.visitWhileTree(this);
    }
}

