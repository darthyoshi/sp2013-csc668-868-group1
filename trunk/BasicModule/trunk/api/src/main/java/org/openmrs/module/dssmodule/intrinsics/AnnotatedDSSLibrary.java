package org.openmrs.module.dssmodule.intrinsics;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.openmrs.module.dssmodule.interpreter.DSSFunction;
import org.openmrs.module.dssmodule.interpreter.DSSLibrary;
import org.openmrs.module.dssmodule.interpreter.Evaluator;
import org.openmrs.module.dssmodule.interpreter.ExecutionContext;
import org.openmrs.module.dssmodule.value.DSSValue;
import org.openmrs.module.dssmodule.value.DSSValueFactory;

/**
 * An AnnotatedDSSLibrary will use reflection to examine its own methods and 
 * generate DSSFunctions if they are annotated as @DSSIntrinsic. See DemoLibrary 
 * for an example of this use. Arguments and return types will be converted 
 * to/from the corresponding DSS data types where necessary (although you 
 * can also use DSSValue or more specific types when desired.)
 * 
 * Short synopsis of usage:
 *  - Create a class that extends AnnotatedDSSLibrary
 *  - Annotate methods which should be exposed as intrinsics with @DSSIntrinsic
 *  - Annotate arguments which use identifier syntax with @DSSIdentifier
 *  - Install the class in an interpreter (see DSSProgram.INTRINSICS)
 * 
 * 
 * The following conversions should be supported (meaning that methods should 
 * generally restrict arguments/return values to the types on the right hand 
 * side.)
 * 
 * DSS Type     Java type
 * -------------------------------------------
 * integer  <-> long
 * float    <-> double
 * boolean  <-> boolean
 * date     <-> java.util.Date
 * string   <-> java.lang.String
 * list     <-> java.util.List<DSSValue>
 * object   <-> java.util.Map<String, DSSValue>
 *     
 * @author woeltjen
 */
public abstract class AnnotatedDSSLibrary implements DSSLibrary {
    private static Map<Class<?>, Class<?>> PRIMITIVE_MAP = makePrimitiveMap();

    @Override
    public Map<String, DSSFunction> getFunctions(ExecutionContext context) {
        Map<String, DSSFunction> functions = new HashMap<String, DSSFunction>();
        
        // Ferret out any intrinsic methods defined in this class
        for (Method m : getClass().getDeclaredMethods()) {
            DSSIntrinsic annotation = m.getAnnotation(DSSIntrinsic.class);
            if (annotation != null) {
                functions.put(m.getName(), new MethodCall(context.getEvaluator(), m));
            }            
        }
        
        return functions;
    }
    
    private static Map<Class<?>, Class<?>> makePrimitiveMap() {
        Map<Class<?>, Class<?>> map = new HashMap<Class<?>, Class<?>>();
        map.put(byte.class, Byte.class);
        map.put(short.class, Short.class);
        map.put(int.class, Integer.class);
        map.put(long.class, Long.class);
        map.put(float.class, Float.class);
        map.put(double.class, Double.class);
        map.put(char.class, Character.class);
        map.put(boolean.class, Boolean.class);
        return map;
    }
    
    private class MethodCall extends DSSFunction {
        private Evaluator evaluator;
        private Method method;
        private Set<Integer> passAsIdentifiers;
        
        public MethodCall(Evaluator evaluator, Method method) {
            this.evaluator = evaluator;
            this.method = method;
            this.passAsIdentifiers = new HashSet<Integer>();
            Annotation[][] paramAnnotations = method.getParameterAnnotations();           
            for (int i = 0; i < paramAnnotations.length; i++) {
                for (int j = 0; j < paramAnnotations[i].length; j++) {
                    if (DSSIdentifier.class.isAssignableFrom(
                            paramAnnotations[i][j].annotationType())) {
                        passAsIdentifiers.add(i);
                    }
                }
            }
        }

        @Override
        public DSSValue call(DSSValue... args) {
            try {
                // Convert DSSValue arguments to plain Java arguments
                Class<?>[] types = method.getParameterTypes();
                Object[] suppliedArgs = new Object[types.length];
                for (int i = 0; i < types.length; i++) {
                    Class<?> type = types[i];
                    if (type.isPrimitive()) {
                        type = PRIMITIVE_MAP.get(type);
                    }
                    suppliedArgs[i] = i < args.length ?
                            evaluator.castTo(type, args[i]) : null;
                }
                // Execute the method, give the return value as a DSSValue
                Object returnValue = method.invoke(AnnotatedDSSLibrary.this, 
                        suppliedArgs);
                return evaluator.toDSSValue(returnValue);
            } catch (Exception e) {
                System.err.println("Error executing intrinsic " + method.getName());
                e.printStackTrace();
                return DSSValueFactory.getDSSValue(); // TODO: DSSNull
            }
        }

        @Override
        public boolean passAsIdentifier(int argumentIndex) {
            return passAsIdentifiers.contains(argumentIndex);
        }
        
        
    }
}
