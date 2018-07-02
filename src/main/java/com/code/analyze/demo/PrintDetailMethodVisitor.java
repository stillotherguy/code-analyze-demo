package com.code.analyze.demo;

import com.google.common.base.Joiner;
import org.objectweb.asm.*;

import java.util.Arrays;

/**
 * @author Ethan Zhang
 * @email ethan.zj@antfin.com
 */
public class PrintDetailMethodVisitor extends MethodVisitor {

    private static final Joiner JOINER = Joiner.on(',').useForNull("null");

    public PrintDetailMethodVisitor(int api) {
        super(api);
        System.out.println("start parse method=======================");
    }

    public PrintDetailMethodVisitor(int api, MethodVisitor methodVisitor) {
        super(api, methodVisitor);
    }

    @Override
    public void visitParameter(String name, int access) {
        System.out.println("visitParameter:" + JOINER.join("name=" + name, "access=" + access));
        super.visitParameter(name, access);
    }

    @Override
    public AnnotationVisitor visitAnnotationDefault() {
        System.out.println("visitAnnotationDefault:");
        return super.visitAnnotationDefault();
    }

    @Override
    public AnnotationVisitor visitAnnotation(String descriptor, boolean visible) {
        System.out.println("visitAnnotation:" + JOINER.join("descriptor=" + descriptor, "visible=" + visible));
        return super.visitAnnotation(descriptor, visible);
    }

    @Override
    public AnnotationVisitor visitTypeAnnotation(int typeRef, TypePath typePath, String descriptor, boolean visible) {
        System.out.println("visitTypeAnnotation:" + JOINER.join("typeRef=" + typeRef, "typePath=" + typePath, "descriptor=" + descriptor, "visible=" + visible));
        return super.visitTypeAnnotation(typeRef, typePath, descriptor, visible);
    }

    @Override
    public void visitAnnotableParameterCount(int parameterCount, boolean visible) {
        System.out.println("visitAnnotableParameterCount:" + JOINER.join("parameterCount=" + parameterCount, "visible=" + visible));
        super.visitAnnotableParameterCount(parameterCount, visible);
    }

    @Override
    public AnnotationVisitor visitParameterAnnotation(int parameter, String descriptor, boolean visible) {
        System.out.println("visitParameterAnnotation:" + JOINER.join("parameter=" + parameter, "descriptor=" + descriptor, "visible=" + visible));
        return super.visitParameterAnnotation(parameter, descriptor, visible);
    }

    @Override
    public void visitAttribute(Attribute attribute) {
        System.out.println("visitAttribute:" + "attribute=" + attribute);
        super.visitAttribute(attribute);
    }

    @Override
    public void visitCode() {
        System.out.println("visitCode:");
        super.visitCode();
    }

    @Override
    public void visitFrame(int type, int nLocal, Object[] local, int nStack, Object[] stack) {
        System.out.println("visitFrame:" + JOINER.join("type=" + type, "nLocal=" + nLocal, "local=" + Arrays.toString(local), "nStack=" + nStack, "stack=" + Arrays.toString(stack)));
        super.visitFrame(type, nLocal, local, nStack, stack);
    }

    @Override
    public void visitInsn(int opcode) {
        System.out.println("visitInsn:" + "opcode=" + opcode);
        super.visitInsn(opcode);
    }

    @Override
    public void visitIntInsn(int opcode, int operand) {
        System.out.println("visitIntInsn:" + JOINER.join("opcode=" + opcode, "operand=" + operand));
        super.visitIntInsn(opcode, operand);
    }

    @Override
    public void visitVarInsn(int opcode, int var) {
        System.out.println("visitVarInsn:" + JOINER.join("opcode=" + opcode, "var=" + var));
        super.visitVarInsn(opcode, var);
    }

    @Override
    public void visitTypeInsn(int opcode, String type) {
        System.out.println("visitTypeInsn:" + JOINER.join("opcode=" + opcode, "type=" + type));
        super.visitTypeInsn(opcode, type);
    }

    @Override
    public void visitFieldInsn(int opcode, String owner, String name, String descriptor) {
        System.out.println("visitFieldInsn:" + JOINER.join("opcode=" + opcode, "owner=" + owner, "name=" + name, "descriptor=" + descriptor));
        super.visitFieldInsn(opcode, owner, name, descriptor);
    }

    @Override
    public void visitMethodInsn(int opcode, String owner, String name, String descriptor, boolean isInterface) {
        System.out.println("visitMethodInsn:" + JOINER.join("opcode=" + opcode, "owner=" + owner, "name=" + name, "descriptor=" + descriptor, "isInterface=" + isInterface));
        super.visitMethodInsn(opcode, owner, name, descriptor, isInterface);
    }

    @Override
    public void visitInvokeDynamicInsn(String name, String descriptor, Handle bootstrapMethodHandle, Object... bootstrapMethodArguments) {
        System.out.println("visitInvokeDynamicInsn:" + JOINER.join("name=" + name, "descriptor=" + descriptor, "bootstrapMethodHandle=" + bootstrapMethodHandle, "bootstrapMethodArguments=" + Arrays.toString(bootstrapMethodArguments)));
        super.visitInvokeDynamicInsn(name, descriptor, bootstrapMethodHandle, bootstrapMethodArguments);
    }

    @Override
    public void visitJumpInsn(int opcode, Label label) {
        System.out.println("visitJumpInsn:" + JOINER.join("opcode=" + opcode, "label=" + label));
        super.visitJumpInsn(opcode, label);
    }

    @Override
    public void visitLabel(Label label) {
        System.out.println("visitLabel:" + "label=" + label);
        super.visitLabel(label);
    }

    @Override
    public void visitLdcInsn(Object value) {
        System.out.println("visitLdcInsn:" + "value=" + value);
        super.visitLdcInsn(value);
    }

    @Override
    public void visitIincInsn(int var, int increment) {
        System.out.println("visitIincInsn:" + JOINER.join("var=" + var, "increment=" + increment));
        super.visitIincInsn(var, increment);
    }

    @Override
    public void visitTableSwitchInsn(int min, int max, Label dflt, Label... labels) {
        System.out.println("visitTableSwitchInsn:" + JOINER.join("min=" + min, "max=" + max, "dflt=" + dflt, "labels=" + Arrays.toString(labels)));
        super.visitTableSwitchInsn(min, max, dflt, labels);
    }

    @Override
    public void visitLookupSwitchInsn(Label dflt, int[] keys, Label[] labels) {
        System.out.println("visitLookupSwitchInsn:" + JOINER.join("dflt=" + dflt, "keys=" + Arrays.toString(keys), "labels=" + Arrays.toString(labels)));
        super.visitLookupSwitchInsn(dflt, keys, labels);
    }

    @Override
    public void visitMultiANewArrayInsn(String descriptor, int numDimensions) {
        System.out.println("visitMultiANewArrayInsn:" + JOINER.join("descriptor=" + descriptor, "numDimensions=" + numDimensions));
        super.visitMultiANewArrayInsn(descriptor, numDimensions);
    }

    @Override
    public AnnotationVisitor visitInsnAnnotation(int typeRef, TypePath typePath, String descriptor, boolean visible) {
        System.out.println("visitInsnAnnotation:" + JOINER.join("typeRef=" + typeRef, "typePath=" + typePath, "descriptor=" + descriptor, "visible=" + visible));
        return super.visitInsnAnnotation(typeRef, typePath, descriptor, visible);
    }

    @Override
    public void visitTryCatchBlock(Label start, Label end, Label handler, String type) {
        System.out.println("visitTryCatchBlock:" + JOINER.join("start=" + start, "end=" + end, "handler=" + handler, "type=" + type));
        super.visitTryCatchBlock(start, end, handler, type);
    }

    @Override
    public AnnotationVisitor visitTryCatchAnnotation(int typeRef, TypePath typePath, String descriptor, boolean visible) {
        System.out.println("visitTryCatchAnnotation:" + JOINER.join("typeRef=" + typeRef, "typePath=" + typePath, "descriptor=" + descriptor, "visible=" + visible));
        return super.visitTryCatchAnnotation(typeRef, typePath, descriptor, visible);
    }

    @Override
    public void visitLocalVariable(String name, String descriptor, String signature, Label start, Label end, int index) {
        System.out.println("visitLocalVariable:" + JOINER.join("name=" + name, "descriptor=" + descriptor, "signature=" + signature, "start=" + start, "end=" + end, "index=" + index));
        super.visitLocalVariable(name, descriptor, signature, start, end, index);
    }

    @Override
    public AnnotationVisitor visitLocalVariableAnnotation(int typeRef, TypePath typePath, Label[] start, Label[] end, int[] index, String descriptor, boolean visible) {
        System.out.println("visitLocalVariableAnnotation:" + JOINER.join("typeRef=" + typeRef, "typePath=" + typePath, "start=" + Arrays.toString(start), "end=" + Arrays.toString(end), "index=" + Arrays.toString(index), "descriptor=" + descriptor, "visible=" + visible));
        return super.visitLocalVariableAnnotation(typeRef, typePath, start, end, index, descriptor, visible);
    }

    @Override
    public void visitLineNumber(int line, Label start) {
        System.out.println("visitLineNumber:" + JOINER.join("line=" + line, "start=" + start));
        super.visitLineNumber(line, start);
    }

    @Override
    public void visitMaxs(int maxStack, int maxLocals) {
        System.out.println("visitMaxs:" + JOINER.join("maxStack=" + maxStack, "maxLocals=" + maxLocals));
        super.visitMaxs(maxStack, maxLocals);
    }

    @Override
    public void visitEnd() {
        System.out.println("visitEnd:");
        System.out.println("end parse method=======================");
        super.visitEnd();
    }
}
