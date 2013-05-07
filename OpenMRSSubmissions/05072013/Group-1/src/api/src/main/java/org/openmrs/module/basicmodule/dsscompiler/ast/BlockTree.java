package org.openmrs.module.basicmodule.dsscompiler.ast;

import org.openmrs.module.basicmodule.dsscompiler.visitor.*;

public class BlockTree extends AST {

    public BlockTree() {
    }

    public Object accept(ASTVisitor v) {
        return v.visitBlockTree(this);
    }

}

