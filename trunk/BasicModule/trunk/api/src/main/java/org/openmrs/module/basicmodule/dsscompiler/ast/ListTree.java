package org.openmrs.module.basicmodule.dsscompiler.ast;

import org.openmrs.module.basicmodule.dsscompiler.visitor.*;

public class ListTree extends AST {

    public ListTree() {
    }

    public Object accept(ASTVisitor v) {
        return v.visitListTree(this);
    }

}

