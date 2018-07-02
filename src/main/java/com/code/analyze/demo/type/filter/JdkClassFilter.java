package com.code.analyze.demo.type.filter;

/**
 * @author Ethan Zhang
 * @email ethan.zj@antfin.com
 */
public class JdkClassFilter implements ClassFilter {
    @Override
    public boolean filter(String name) {
        return !name.startsWith("java");
    }
}
