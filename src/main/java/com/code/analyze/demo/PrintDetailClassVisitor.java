package com.code.analyze.demo;

import com.google.common.base.Joiner;
import org.objectweb.asm.*;

import java.util.Arrays;

/**
 * @author Ethan Zhang
 * @email ethan.zj@antfin.com
 */
public class PrintDetailClassVisitor extends ClassVisitor {

    private final Joiner JOINER = Joiner.on(',').useForNull("null");

    public PrintDetailClassVisitor(int api) {
        super(api);
        System.out.println("start parse class=======================");
    }

    public PrintDetailClassVisitor(int api, ClassVisitor classVisitor) {
        super(api, classVisitor);
    }

    @Override
    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
        System.out.println("visit:" + JOINER.join("version=" + version, "access=" + access,
                "name=" + name, "signature=" + signature, "superName=" + superName, "interfaces=" + Arrays.toString(interfaces)));
        super.visit(version, access, name, signature, superName, interfaces);
    }

    @Override
    public void visitSource(String source, String debug) {
        System.out.println("visitSource:" + JOINER.join("source=" + source, "debug=" + debug));
        super.visitSource(source, debug);
    }

    @Override
    public ModuleVisitor visitModule(String name, int access, String version) {
        System.out.println("visitModule:" + JOINER.join("name=" + name, "access=" + access, "version=" + version));
        return super.visitModule(name, access, version);
    }

    @Override
    public void visitOuterClass(String owner, String name, String descriptor) {
        System.out.println("visitOuterClass:" + JOINER.join("owner=" + owner, "name=" + name, "descriptor=" + descriptor));
        super.visitOuterClass(owner, name, descriptor);
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
    public void visitAttribute(Attribute attribute) {
        System.out.println("visitAttribute:" + "attribute=" + attribute);
        super.visitAttribute(attribute);
    }

    @Override
    public void visitInnerClass(String name, String outerName, String innerName, int access) {
        System.out.println("visitInnerClass:" + JOINER.join("name=" + name, "outerName=" + outerName, "innerName=" + innerName, "access=" + access));
        super.visitInnerClass(name, outerName, innerName, access);
    }

    @Override
    public FieldVisitor visitField(int access, String name, String descriptor, String signature, Object value) {
        System.out.println("visitField:" + JOINER.join("access=" + access, "name=" + name, "descriptor=" + descriptor, "signature=" + signature, "value=" + value));
        return super.visitField(access, name, descriptor, signature, value);
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
        System.out.println("visitMethod:" + JOINER.join("access=" + access, "name=" + name, "descriptor=" + descriptor, "signature=" + signature, "exceptions=" + Arrays.toString(exceptions)));
        return new PrintDetailMethodVisitor(Opcodes.ASM6);
    }

    @Override
    public void visitEnd() {
        System.out.println("visitEnd:");
        System.out.println("end parse class=======================");
        super.visitEnd();
    }
}
