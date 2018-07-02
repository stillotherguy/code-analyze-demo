/*
 * Copyright 2002-2014 the original author or authors.
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
import com.code.analyze.demo.utils.ClassUtils;

import java.io.IOException;
import java.io.InputStream;

public class SimpleMethodCallReaderFactory implements MethodCallReaderFactory {

    @Override
    public MethodCallReader getReader(String className, String methodName, String... argTypes) throws IOException {
        InputStream inputStream = getInputStream(className);

        return new SimpleMethodCallReader(inputStream, ClassUtils.getDefaultClassLoader(), className, methodName, argTypes);
    }

    private InputStream getInputStream(String className) {
        InputStream inputStream = ClassUtils.getDefaultClassLoader().getResourceAsStream(
                ClassUtils.convertClassNameToResourcePath(className) + ClassUtils.CLASS_FILE_SUFFIX);
        if (inputStream == null) {
            int lastDotIndex = className.lastIndexOf('.');
            if (lastDotIndex != -1) {
                String innerClassName =
                        className.substring(0, lastDotIndex) + '$' + className.substring(lastDotIndex + 1);
                String innerClassResourcePath = innerClassName + ClassUtils.CLASS_FILE_SUFFIX;
                inputStream = ClassUtils.getDefaultClassLoader().getResourceAsStream(innerClassResourcePath);
            }
        }
        return inputStream;
    }

    @Override
    public MethodCallReader getReader(MethodCall methodCall, MethodCall parent) throws IOException {
        String className = methodCall.getDeclaringClassName();
        InputStream inputStream = getInputStream(className);

        return new SimpleMethodCallReader(inputStream, ClassUtils.getDefaultClassLoader(), methodCall, parent);
    }
}
