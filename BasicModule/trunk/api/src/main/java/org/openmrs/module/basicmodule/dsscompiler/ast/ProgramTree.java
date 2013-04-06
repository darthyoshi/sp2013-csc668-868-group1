package org.openmrs.module.basicmodule.dsscompiler.ast;

import org.openmrs.module.basicmodule.dsscompiler.visitor.*;

public class ProgramTree extends AST {

    public ProgramTree() {
    }

    public Object accept(ASTVisitor v) {
        return v.visitProgramTree(this);
    }

}

