/*
 * Copyright 2002-2015 the original author or authors.
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
import com.code.analyze.demo.type.MethodCall;
import com.code.analyze.demo.type.MethodMetadata;
import com.code.analyze.demo.type.filter.ClassFilter;
import com.code.analyze.demo.type.filter.ConstructorMethodFilter;
import com.code.analyze.demo.type.filter.MethodFilter;
import com.code.analyze.demo.utils.ClassUtils;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class MethodMetadataReadingVisitor extends MethodVisitor {

    private final ClassMetadata classMetadata;

    private final MethodCall methodCall;

    private final MethodCall parent;

    private final MethodFilter methodFilter = new ConstructorMethodFilter();

    private ClassFilter classFilter;

    private int lineNumber;

    public MethodMetadataReadingVisitor(ClassFilter classFilter, ClassMetadata classMetadata, MethodCall methodCall, MethodCall parent) {
        super(Opcodes.ASM6);
        this.classFilter = classFilter;
        this.classMetadata = classMetadata;
        this.methodCall = methodCall;
        this.parent = parent;
    }

    @Override
    public void visitMethodInsn(int opcode, String owner, String name, String desc, boolean itf) {
        String className = ClassUtils.convertResourcePathToClassName(owner);
        MethodMetadata methodMetadata = methodCall.getMethodMetadata();

        if (classFilter.filter(className) || methodFilter.filter(name, desc)) {
            return;
        }

        // 防止直接递归调用
        boolean recur = false;
        if (className.equals(classMetadata.getClassName()) && name.equals(methodMetadata.getMethodName()) && desc.equals(methodMetadata.getDescriptor())) {
            // warn
            recur = true;
        }

        MethodCall parent = this.parent;
        while (parent != null) {
            if (className.equals(parent.getMethodMetadata().getDeclaringClassName()) && name.equals(parent.getMethodMetadata().getMethodName()) && desc.equals(parent.getMethodMetadata().getDescriptor())) {
                // warn
                recur = true;
                break;
            }

            parent = parent.getParent();
        }

        MethodCall methodCall = MethodCall.newMethodCall(lineNumber, owner, name, desc, recur);
        this.methodCall.getDirectChildren().add(methodCall);
    }



    @Override
    public void visitParameter(String name, int access) {
        super.visitParameter(name, access);
    }

    @Override
    public void visitLineNumber(int line, Label start) {
        lineNumber = line;
    }

    @Override
    public void visitEnd() {
        super.visitEnd();
    }
}
