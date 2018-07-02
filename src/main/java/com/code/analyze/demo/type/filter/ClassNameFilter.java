package com.code.analyze.demo.type.filter;

/**
 * @author Ethan Zhang
 * @email ethan.zj@antfin.com
 */
public class ClassNameFilter implements ClassFilter {

    private final String className;

    public ClassNameFilter(String className) {
        this.className = className;
    }

    @Override
    public boolean filter(String name) {
        return className.equals(name);
    }
}
