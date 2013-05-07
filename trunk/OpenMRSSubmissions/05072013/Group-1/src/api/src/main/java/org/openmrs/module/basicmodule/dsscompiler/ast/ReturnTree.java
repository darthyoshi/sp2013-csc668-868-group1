package org.openmrs.module.basicmodule.dsscompiler.ast;

import org.openmrs.module.basicmodule.dsscompiler.visitor.*;

public class ReturnTree extends AST {

    public ReturnTree() {
    }

    public Object accept(ASTVisitor v) {
        return v.visitReturnTree(this);
    }

}

