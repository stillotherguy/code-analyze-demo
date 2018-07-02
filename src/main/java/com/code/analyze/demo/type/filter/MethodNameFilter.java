package com.code.analyze.demo.type.filter;

import jdk.internal.org.objectweb.asm.Type;

import java.util.Arrays;

/**
 * @author Ethan Zhang
 * @email ethan.zj@antfin.com
 */
public class MethodNameFilter implements MethodFilter {

    private final String methodName;
    private final String[] argTypes;

    public MethodNameFilter(String methodName, String[] argTypes) {
        this.methodName = methodName;
        this.argTypes = argTypes;
    }

    @Override
    public boolean filter(String name, String desc) {
        Type[] argTypes = Type.getArgumentTypes(desc);
        boolean match = false;
        if (argTypes != null && argTypes.length > 0) {
            String[] types = new String[argTypes.length];
            for (int i = 0; i < argTypes.length; i++) {
                types[i] = argTypes[i].getClassName();
            }

            match = Arrays.equals(this.argTypes, types);
        }

        return name.equals(methodName) && match;
    }
}
