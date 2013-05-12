/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openmrs.module.dssmodule.value;

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

    @Override
    public DSSValue power (DSSValue b) {
        if (b.isNumeric()) {
            // Use integer type if both sides are integers - otherwise, float
            return isInt() && b.isInt() ?
                    DSSValueFactory.getDSSValue( (long) Math.pow(toInt(), b.toInt())) :
                    DSSValueFactory.getDSSValue( Math.pow(toFloat(), b.toFloat()));        
        }
        return DSSValueFactory.getDSSValue();
    }  
}
