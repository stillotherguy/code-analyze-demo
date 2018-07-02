package com.code.analyze.demo.type.filter;

/**
 * @author Ethan Zhang
 * @email ethan.zj@antfin.com
 */
public class ConstructorMethodFilter implements MethodFilter {

    private static final String CONSTURCTOR = "<init>";
    private static final String CLASS_CONSTRUCTOR = "<cinit>";

    @Override
    public boolean filter(String name, String desc) {
        return CONSTURCTOR.equals(name) || CLASS_CONSTRUCTOR.equals(name);
    }
}
