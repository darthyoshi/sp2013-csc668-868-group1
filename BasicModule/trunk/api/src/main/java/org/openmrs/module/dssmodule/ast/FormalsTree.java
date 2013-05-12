package org.openmrs.module.dssmodule.ast;

import org.openmrs.module.dssmodule.visitor.ASTVisitor;

public class FormalsTree  extends AST {

    public FormalsTree() {
    }

    public Object accept(ASTVisitor v) {
        return v.visitFormalsTree(this);
    }
}
