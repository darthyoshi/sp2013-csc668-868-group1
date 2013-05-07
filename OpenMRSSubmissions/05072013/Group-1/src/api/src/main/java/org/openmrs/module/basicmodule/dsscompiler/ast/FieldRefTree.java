package org.openmrs.module.basicmodule.dsscompiler.ast;

import org.openmrs.module.basicmodule.dsscompiler.visitor.*;

public class FieldRefTree extends AST {

    public FieldRefTree() {
    }

    public Object accept(ASTVisitor v) {
        return v.visitFieldRefTree(this);
    }

}

