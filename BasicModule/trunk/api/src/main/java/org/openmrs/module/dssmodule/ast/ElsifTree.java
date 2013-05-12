package org.openmrs.module.dssmodule.ast;

import org.openmrs.module.dssmodule.visitor.ASTVisitor;

public class ElsifTree extends AST {

    public ElsifTree() {
    }

    public Object accept(ASTVisitor v) {
        return v.visitElsifTree(this);
    }

}
