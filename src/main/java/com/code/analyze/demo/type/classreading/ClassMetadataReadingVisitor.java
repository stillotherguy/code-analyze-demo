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

import com.code.analyze.demo.type.ClassMetadata;
import com.code.analyze.demo.type.DefaultClassMetadata;
import com.code.analyze.demo.type.DefaultMethodMetadata;
import com.code.analyze.demo.type.MethodCall;
import com.code.analyze.demo.type.filter.ClassFilter;
import com.code.analyze.demo.type.filter.EmptyClassFilter;
import com.code.analyze.demo.type.filter.JdkClassFilter;
import com.code.analyze.demo.utils.ClassUtils;
import org.objectweb.asm.*;

import java.util.Arrays;

class ClassMetadataReadingVisitor extends ClassVisitor {

    private ClassMetadata classMetadata;

    private MethodCall methodCall;

    private MethodCall parent;

    private String sourceFile;

    private boolean inRecursive;

    private final ClassFilter classFilter = new EmptyClassFilter();

    private final String methodName;

    private final String[] argTypes;

    public ClassMetadataReadingVisitor(ClassLoader classLoader, String methodName, String[] argTypes) {
        super(Opcodes.ASM6);
        this.methodName = methodName;

        if (argTypes == null) {
            this.argTypes = new String[0];
        } else {
            this.argTypes = argTypes;
        }
    }

    public ClassMetadataReadingVisitor(ClassLoader classLoader, MethodCall parent, MethodCall methodCall) {
        this(classLoader, methodCall.getMethodName(), methodCall.getArgTypes());
        this.parent = parent;
        this.methodCall = methodCall;
        this.inRecursive = methodCall.isInRecursive();
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
        if (classMetadata == null) {
            return new EmptyMethodVisitor();
        }

        Type[] argTypes = Type.getArgumentTypes(desc);
        String[] types = new String[argTypes.length];
        for (int i = 0; i < argTypes.length; i++) {
            types[i] = argTypes[i].getClassName();
        }

        if (!(name.equals(methodName) && Arrays.equals(this.argTypes, types))) {
            return new EmptyMethodVisitor();
        }

        if ((access & Opcodes.ACC_BRIDGE) != 0 || (access & Opcodes.ACC_STATIC) != 0) {
            return new EmptyMethodVisitor();
        }

        DefaultMethodMetadata methodMetadata = DefaultMethodMetadata.newMethodMetadata(access, classMetadata.getClassName(), name, desc, signature, exceptions);
        if (methodCall == null) {
            methodCall = new MethodCall();
        }

        methodCall.setMethodMetadata(methodMetadata);
        methodCall.setClassMetadata(classMetadata);
        methodCall.setSourceFile(sourceFile);

        if (inRecursive) {
            return new EmptyMethodVisitor();
        }

        return new MethodMetadataReadingVisitor(classFilter, classMetadata, this.methodCall, this.parent);
    }

    @Override
    public void visit(int version, int access, String name, String signature, String supername, String[] interfaces) {
        if (classFilter.filter(ClassUtils.convertResourcePathToClassName(name))) {
            return;
        }

        classMetadata = DefaultClassMetadata.newMetadata(version, access, name, signature, supername, interfaces);
    }

    @Override
    public void visitOuterClass(String owner, String name, String desc) {
        DefaultClassMetadata defaultClassMetadata = (DefaultClassMetadata) classMetadata;
        defaultClassMetadata.setEnclosingClassName(ClassUtils.convertResourcePathToClassName(owner));
    }

    @Override
    public void visitInnerClass(String name, String outerName, String innerName, int access) {
        if (outerName != null) {
            String fqName = ClassUtils.convertResourcePathToClassName(name);
            String fqOuterName = ClassUtils.convertResourcePathToClassName(outerName);
            if (classMetadata.getClassName().equals(fqName)) {
                DefaultClassMetadata defaultClassMetadata = (DefaultClassMetadata) classMetadata;
                defaultClassMetadata.setEnclosingClassName(fqOuterName);
                defaultClassMetadata.setIndependentInnerClass((access & Opcodes.ACC_STATIC) != 0);
            } else if (classMetadata.getClassName().equals(fqOuterName)) {
                classMetadata.getMemberClassNames().add(fqName);
            }
        }
    }

    @Override
    public void visitSource(String source, String debug) {
        sourceFile = source;
    }

    @Override
    public AnnotationVisitor visitAnnotation(String desc, boolean visible) {
        // no-op
        return new EmptyAnnotationVisitor();
    }

    @Override
    public void visitAttribute(Attribute attr) {
        // no-op
    }

    @Override
    public FieldVisitor visitField(int access, String name, String desc, String signature, Object value) {
        // no-op
        return new EmptyFieldVisitor();
    }

    @Override
    public void visitEnd() {
        // no-op
    }

    private static class EmptyAnnotationVisitor extends AnnotationVisitor {

        public EmptyAnnotationVisitor() {
            super(Opcodes.ASM6);
        }

        @Override
        public AnnotationVisitor visitAnnotation(String name, String desc) {
            return this;
        }

        @Override
        public AnnotationVisitor visitArray(String name) {
            return this;
        }
    }

    private static class EmptyMethodVisitor extends MethodVisitor {

        public EmptyMethodVisitor() {
            super(Opcodes.ASM6);
        }
    }

    private static class EmptyFieldVisitor extends FieldVisitor {

        public EmptyFieldVisitor() {
            super(Opcodes.ASM6);
        }
    }

    public ClassMetadata getClassMetadata() {
        return classMetadata;
    }

    public MethodCall getMethodCall() {
        return methodCall;
    }
}
