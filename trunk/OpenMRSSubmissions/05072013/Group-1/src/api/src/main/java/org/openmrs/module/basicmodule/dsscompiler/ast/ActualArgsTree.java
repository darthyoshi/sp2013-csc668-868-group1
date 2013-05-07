package org.openmrs.module.basicmodule.dsscompiler.ast;

import org.openmrs.module.basicmodule.dsscompiler.visitor.*;

public class ActualArgsTree extends AST {

    public ActualArgsTree() {
    }

    public Object accept(ASTVisitor v) {
        return v.visitActualArgsTree(this);
    }

}

