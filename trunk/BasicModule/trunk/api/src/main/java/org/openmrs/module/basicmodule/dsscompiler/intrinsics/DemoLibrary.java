package org.openmrs.module.basicmodule.dsscompiler.intrinsics;

import org.openmrs.module.basicmodule.dsscompiler.interpreter.Interpreter;
import org.openmrs.module.basicmodule.dsscompiler.parser.Parser;
import org.openmrs.module.basicmodule.dsscompiler.value.DSSValue;

/**
 * Demonstrates the use of AnnotatedDSSLibrary to semi-automatically 
 * create intrinsics.
 * 
 * This library provides some basic math functions (sin, min, max) and a 
 * log function (similar to alert) as intrinsics.
 * 
 * See the DSS source at the bottom for an example of how these methods appear 
 * & can be used by a DSS program.
 * 
 * @author woeltjen
 */
public class DemoLibrary extends AnnotatedDSSLibrary {
    /*
     * Using @DSSIntrinsic indicates that a method should be provided as 
     * an intrinsic function when the library is installed. Common Java types 
     * may be used for arguments and return values - these will be converted 
     * automatically to DSSValue types when possible.
     */
    @DSSIntrinsic
    public double sin (double r) {
        return Math.sin(r);
    }
    
    /*
     * The conversion of types is not mandatory, however. Arguments and/or 
     * return values can be given as DSSValues, as well (this is useful if, 
     * for instance, you need to access the Timestamp of a DSSValue)
     */
    @DSSIntrinsic
    public DSSValue min (DSSValue a, DSSValue b) {
        return a.lessthan(b) ? a : b;
    }
    
    @DSSIntrinsic
    public DSSValue max (DSSValue a, DSSValue b) {
        return a.greaterthan(b) ? a : b;
    }
    
    /*
     * Some intrinsic methods, such as alerts and reads, take arguments that 
     * are passed as unquoted strings. This requires a little bit of special 
     * case processing on the interpreter's part; to indicate that this is 
     * needed, annotate an argument with @DSSIdentifier
     */
    @DSSIntrinsic
    public void log(@DSSIdentifier String level, String message) {
        System.out.println(level + "\t" + message);
    }
    
    
    /*
     * This main method serves to test and demonstrate the behavior of 
     * the AnnotatedDSSLibrary
     */
    public static void main(String[] args) {
        try {
            Parser p = new Parser("libdemo.dss");
            Interpreter i = new Interpreter();
            i.install(new DemoLibrary());
            i.defineConstant("pi", 3.14159);
            i.interpret(p.execute());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

/** SOURCE OF libdemo.dss
 * 
program
// FIBONACCI SEQUENCE
function fib(x) {
    if x<2 then { return x } else { return fib(x-2) + fib(x-1) }
}

// MAIN BLOCK
{
    log(patientSummary,   fib(5))
    log(patientSummary,   "sin of pi/2 is " + sin(3.14159/2))

    log(patientDashboard, "max of 1 and 2 is " + max(1,2))
    log(patientDashboard, "min of 1 and 2 is " + min(1,2))
}
 */