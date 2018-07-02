package com.code.analyze.demo;

import com.code.analyze.demo.type.MethodCall;
import com.code.analyze.demo.type.classreading.MethodCallReader;
import com.code.analyze.demo.type.classreading.MethodCallReaderFactory;
import com.code.analyze.demo.type.classreading.SimpleMethodCallReaderFactory;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.io.StringReader;
import java.util.Scanner;

/**
 * @author Ethan Zhang
 * @email ethan.zj@antfin.com
 */
public class DefaultCodeAnalzer implements CodeAnalzer {

    private final MethodCallReaderFactory methodCallReaderFactory = new SimpleMethodCallReaderFactory();

    @Override
    public MethodCall analyze(String className, String methodName, String... args) {
        MethodCallReader reader = null;
        try {
            reader = methodCallReaderFactory.getReader(TestCase.class.getName(), "hello", "java.lang.String");
            recursive(reader);

            MethodCall methodCall = reader.getMethodCall();
            return methodCall;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void recursive(MethodCallReader reader) throws IOException {
        MethodCall parent = reader.getMethodCall();
        // 如果方法内没有调用任何方法则会返回null
        if (parent != null) {
            for (MethodCall methodCall : parent.getDirectChildren()) {
                reader = methodCallReaderFactory.getReader(methodCall, parent);

                MethodCall child = reader.getMethodCall();
                if (child != null) {
                    child.setParent(parent);
                    parent.getChildren().add(child);
                }

                recursive(reader);
            }
        }
    }
}
