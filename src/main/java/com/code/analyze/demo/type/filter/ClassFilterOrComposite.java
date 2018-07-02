package com.code.analyze.demo.type.filter;

/**
 * @author Ethan Zhang
 * @email ethan.zj@antfin.com
 */
public class ClassFilterOrComposite implements ClassFilter {

    private final ClassFilter[] classFilters;

    public ClassFilterOrComposite(ClassFilter[] classFilters) {
        this.classFilters = classFilters;
    }

    @Override
    public boolean filter(String name) {
        if (classFilters == null) {
            return false;
        }

        for (ClassFilter classFilter : classFilters) {
            if (classFilter.filter(name)) {
                return true;
            }
        }

        return false;
    }
}
