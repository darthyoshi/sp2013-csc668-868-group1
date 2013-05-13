package org.openmrs.module.dssmodule.intrinsics;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Annotation which indicates that an argument should be passed as a raw 
 * identifier when interpreted in a DSS1 program.
 * @author woeltjen
 */
@Retention(value=RetentionPolicy.RUNTIME)
public @interface DSSIdentifier {
    
}
