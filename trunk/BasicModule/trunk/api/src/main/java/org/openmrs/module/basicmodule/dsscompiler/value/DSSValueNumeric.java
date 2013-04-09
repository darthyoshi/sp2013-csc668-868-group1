/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openmrs.module.basicmodule.dsscompiler.value;

/**
 *
 * @author bierman
 */
abstract class DSSValueNumeric extends DSSValue {
    
    
    @Override
    public boolean isNumeric() {
        return true;
    }
    
    @Override
    public DSSValue concat (DSSValue b) {
        DSSValue x = DSSValueFactory.getDSSValue((this.toString()));
        x.concat (b);
        return (x);
    }

  
}
