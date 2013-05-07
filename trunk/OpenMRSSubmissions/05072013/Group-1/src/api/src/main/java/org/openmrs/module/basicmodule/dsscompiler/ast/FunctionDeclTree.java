package org.openmrs.module.basicmodule.dsscompiler.ast;

import org.openmrs.module.basicmodule.dsscompiler.visitor.*;

public class FunctionDeclTree extends AST {

    public FunctionDeclTree() {
    }

    public Object accept(ASTVisitor v) {
        return v.visitFunctionDeclTree(this);
    }

}

