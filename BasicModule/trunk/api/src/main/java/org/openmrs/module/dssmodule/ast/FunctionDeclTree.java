package org.openmrs.module.dssmodule.ast;

import org.openmrs.module.dssmodule.visitor.ASTVisitor;

public class FunctionDeclTree extends AST {

    public FunctionDeclTree() {
    }

    public Object accept(ASTVisitor v) {
        return v.visitFunctionDeclTree(this);
    }

}

