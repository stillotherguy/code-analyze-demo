package com.code.analyze.demo;

import com.code.analyze.demo.type.MethodCall;
import com.code.analyze.demo.type.classreading.MethodCallReader;
import com.code.analyze.demo.type.classreading.MethodCallReaderFactory;
import com.code.analyze.demo.type.classreading.SimpleMethodCallReaderFactory;

import java.io.IOException;

/**
 * @author Ethan Zhang
 * @email ethan.zj@antfin.com
 */
public class DefaultCodeAnalyzer implements CodeAnalyzer {

    private final MethodCallReaderFactory methodCallReaderFactory = new SimpleMethodCallReaderFactory();

    @Override
    public MethodCall analyze(String className, String methodName, String... args) {
        MethodCallReader reader = null;
        try {
            reader = methodCallReaderFactory.getReader(TestCase.class.getName(), "hello", "java.lang.String");
            recursive(reader);

            return reader.getMethodCall();
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
