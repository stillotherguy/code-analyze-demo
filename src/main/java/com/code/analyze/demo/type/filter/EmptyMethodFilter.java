package com.code.analyze.demo.type.filter;

/**
 * @author Ethan Zhang
 * @email ethan.zj@antfin.com
 */
public class EmptyMethodFilter implements MethodFilter {
    @Override
    public boolean filter(String name, String desc) {
        return true;
    }
}
