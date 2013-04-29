package org.openmrs.module.basicmodule.dsscompiler.ast;

import org.openmrs.module.basicmodule.dsscompiler.visitor.*;

public class IfTree extends AST {

    public IfTree() {
    }

    public Object accept(ASTVisitor v) {
        return v.visitIfTree(this);
    }

}

