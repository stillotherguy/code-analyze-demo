package com.code.analyze.demo.type.filter;

/**
 * @author Ethan Zhang
 * @email ethan.zj@antfin.com
 */
public class EmptyClassFilter implements ClassFilter {
    @Override
    public boolean filter(String name) {
        return true;
    }
}
