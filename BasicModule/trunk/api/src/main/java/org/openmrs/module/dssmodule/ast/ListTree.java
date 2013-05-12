package org.openmrs.module.dssmodule.ast;

import org.openmrs.module.dssmodule.visitor.ASTVisitor;

public class ListTree extends AST {

    public ListTree() {
    }

    public Object accept(ASTVisitor v) {
        return v.visitListTree(this);
    }

}

