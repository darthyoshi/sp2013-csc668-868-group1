package org.openmrs.module.basicmodule.dsscompiler.ast;

import org.openmrs.module.basicmodule.dsscompiler.visitor.ASTVisitor;

public class FormalsTree  extends AST {

    public FormalsTree() {
    }

    public Object accept(ASTVisitor v) {
        return v.visitFormalsTree(this);
    }
}
