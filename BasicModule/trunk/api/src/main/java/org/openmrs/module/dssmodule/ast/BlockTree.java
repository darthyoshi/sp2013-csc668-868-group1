package org.openmrs.module.dssmodule.ast;

import org.openmrs.module.dssmodule.visitor.ASTVisitor;

public class BlockTree extends AST {

    public BlockTree() {
    }

    public Object accept(ASTVisitor v) {
        return v.visitBlockTree(this);
    }

}

