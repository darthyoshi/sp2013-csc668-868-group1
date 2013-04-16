package org.openmrs.module.basicmodule.dsscompiler.intrinsics;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.openmrs.module.basicmodule.dsscompiler.interpreter.DSSFunction;
import org.openmrs.module.basicmodule.dsscompiler.interpreter.DSSLibrary;
import org.openmrs.module.basicmodule.dsscompiler.interpreter.Evaluator;
import org.openmrs.module.basicmodule.dsscompiler.interpreter.ExecutionContext;
import org.openmrs.module.basicmodule.dsscompiler.value.DSSValue;

/**
 *
 * @author woeltjen
 */
public abstract class AnnotatedDSSLibrary implements DSSLibrary {
    private static Map<Class<?>, Class<?>> PRIMITIVE_MAP = makePrimitiveMap();

    public Map<String, DSSFunction> getFunctions(ExecutionContext context) {
        Map<String, DSSFunction> functions = new HashMap<String, DSSFunction>();
        
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
                System.out.println("ERROR FOR " + method.getName());
                e.printStackTrace();
                return null; // TODO: DSSNull
            }
        }

        @Override
        public boolean passAsIdentifier(int argumentIndex) {
            return passAsIdentifiers.contains(argumentIndex);
        }
        
        
    }
}