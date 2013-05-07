package org.openmrs.module.basicmodule.dsscompiler.ast;

import org.openmrs.module.basicmodule.dsscompiler.visitor.*;

public class ObjectTree extends AST {

    public ObjectTree() {
    }

 
    public Object accept(ASTVisitor v) {
        return v.visitObjectTree(this);
    }

}