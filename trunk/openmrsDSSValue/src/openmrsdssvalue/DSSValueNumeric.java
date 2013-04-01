/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package openmrsdssvalue;

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
