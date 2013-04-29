package org.openmrs.module.basicmodule.dsscompiler.ast;

import org.openmrs.module.basicmodule.dsscompiler.visitor.*;

public class ElsifTree extends AST {

    public ElsifTree() {
    }

    public Object accept(ASTVisitor v) {
        return v.visitElsifTree(this);
    }

}
