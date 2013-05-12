package org.openmrs.module.dssmodule.ast;

import org.openmrs.module.dssmodule.visitor.ASTVisitor;

public class ProgramTree extends AST {

    public ProgramTree() {
    }

    public Object accept(ASTVisitor v) {
        return v.visitProgramTree(this);
    }

}

