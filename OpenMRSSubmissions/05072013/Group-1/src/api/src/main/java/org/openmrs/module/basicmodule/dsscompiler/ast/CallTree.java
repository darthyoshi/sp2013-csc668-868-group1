package org.openmrs.module.basicmodule.dsscompiler.ast;

import org.openmrs.module.basicmodule.dsscompiler.visitor.*;

public class CallTree extends AST {

    public CallTree() {
    }

    public Object accept(ASTVisitor v) {
        return v.visitCallTree(this);
    }

}

