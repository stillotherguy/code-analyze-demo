package com.code.analyze.demo;

import com.code.analyze.demo.type.MethodCall;

public interface CodeAnalzer {

    MethodCall analyze(String className, String methodName, String... args);
}
