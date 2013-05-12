package org.openmrs.module.dssmodule.ast;

import org.openmrs.module.dssmodule.visitor.ASTVisitor;

public class ForTree extends AST {

    public ForTree() {
    }

 
    public Object accept(ASTVisitor v) {
        return v.visitForTree(this);
    }

}