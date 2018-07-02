package com.code.analyze.demo;

import com.code.analyze.demo.type.MethodCall;

public interface CodeAnalyzer {

    MethodCall analyze(String className, String methodName, String... args);
}
