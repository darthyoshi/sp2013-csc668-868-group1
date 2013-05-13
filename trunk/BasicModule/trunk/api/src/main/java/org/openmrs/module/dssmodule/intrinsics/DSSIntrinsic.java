package org.openmrs.module.dssmodule.intrinsics;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Annotation which indicates that a method should be exposed as an 
 * intrinsic function, when declared by a subclass of AnnotatedDSSLibrary.
 * @author woeltjen
 */
@Retention(value=RetentionPolicy.RUNTIME)
public @interface DSSIntrinsic {

}
