/*
 * Copyright 2002-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.code.analyze.demo.type.classreading;

import com.code.analyze.demo.type.MethodCall;

import com.code.analyze.demo.type.ClassMetadata;
import org.objectweb.asm.ClassReader;

import java.io.IOException;
import java.io.InputStream;

final class SimpleMethodCallReader implements MethodCallReader {

    private final ClassMetadata classMetadata;

    private final MethodCall methodCall;

    SimpleMethodCallReader(InputStream inputStream, ClassLoader classLoader, String className, String methodName, String[] argTypes) throws IOException {
        ClassReader classReader;
        try {
            classReader = new ClassReader(inputStream);
        } finally {
            inputStream.close();
        }

        ClassMetadataReadingVisitor visitor = new ClassMetadataReadingVisitor(classLoader, methodName, argTypes);
        //visitor.setMethodFilter(new MethodNameFilter(methodName, argTypes));
        //visitor.setClassFilter(ClassFilters.and(new JdkClassFilter(), new ClassNameFilter(className)));

        classReader.accept(visitor, 0);

        this.classMetadata = visitor.getClassMetadata();
        this.methodCall = visitor.getMethodCall();
    }

    public SimpleMethodCallReader(InputStream inputStream, ClassLoader classLoader, MethodCall methodCall, MethodCall parent) throws IOException {
        ClassReader classReader;
        try {
            classReader = new ClassReader(inputStream);
        } finally {
            inputStream.close();
        }

        ClassMetadataReadingVisitor visitor = new ClassMetadataReadingVisitor(classLoader, parent, methodCall);
        //visitor.setMethodFilter(new MethodNameFilter(methodName, argTypes));
        //visitor.setClassFilter(ClassFilters.and(new JdkClassFilter(), new ClassNameFilter(className)));

        classReader.accept(visitor, 0);

        this.classMetadata = visitor.getClassMetadata();
        this.methodCall = visitor.getMethodCall();
    }


    @Override
    public ClassMetadata getClassMetadata() {
        return this.classMetadata;
    }

    @Override
    public MethodCall getMethodCall() {
        return methodCall;
    }
}
