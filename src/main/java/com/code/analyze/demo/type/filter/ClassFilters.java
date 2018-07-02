package com.code.analyze.demo.type.filter;

/**
 * @author Ethan Zhang
 * @email ethan.zj@antfin.com
 */
public abstract class ClassFilters {

    public static ClassFilter and(ClassFilter... classFilters) {
        return new ClassFilterAndComposite(classFilters);
    }

    public static ClassFilter or(ClassFilter... classFilters) {
        return new ClassFilterOrComposite(classFilters);
    }
}
