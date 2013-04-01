/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package openmrsdssvalue;
import java.util.Date;

/**
 *
 * @author bierman
 */



public class OpenmrsDSSValue {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        DSSValue a = DSSValueFactory.getDSSValue (4);
        DSSValue b = DSSValueFactory.getDSSValue (7);
        DSSValue c = DSSValueFactory.getDSSValue (21.5);
        DSSValue d = DSSValueFactory.getDSSValue (87);
        DSSValue e = DSSValueFactory.getDSSValue (3);
        
        DSSValue f = a.add(e);
        DSSValue g = c.div(e);
        DSSValue h = d.sub(b);
        
        Date x = new Date((long)((365.25*43 + 31+28+31)*24*60*60*1000));
        Date y = new Date((long)((365.25*44 + 31+28+31)*24*60*60*1000));
        DSSValue i = DSSValueFactory.getDSSValue (x);
        DSSValue j = DSSValueFactory.getDSSValue (y);
        
        DSSValue k = j.add(DSSValueFactory.getDSSValue ((long)365*24*60*60*1000));
        
        System.out.println(a.toString() + " + " + e.toString() + " = " + f.toString());
        System.out.println(c.toString() + " / " + e.toString() + " = " + g.toString());
        System.out.println(d.toString() + " - " + b.toString() + " = " + h.toString());
        System.out.println(i.toString() + " ;" + j.toString() + " + 365 = " + k.toString());
        
    }
}
