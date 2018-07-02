package com.code.analyze.demo;

import com.code.analyze.demo.type.MethodCall;

/**
 * @author Ethan Zhang
 * @email ethan.zj@antfin.com
 */
public class Main {

    public static void main(String[] args) throws Exception {
        CodeAnalyzer codeAnalyzer = new DefaultCodeAnalyzer();
        MethodCall methodCall = codeAnalyzer.analyze(TestCase.class.getName(), "hello", "java.lang.String");
        if (methodCall != null) {
            System.out.println(methodCall.render());
        }
    }
}
