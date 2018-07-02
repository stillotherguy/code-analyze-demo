package com.code.analyze.demo.type;


import com.code.analyze.demo.utils.ClassUtils;
import org.objectweb.asm.Opcodes;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @author Ethan Zhang
 * @email ethan.zj@antfin.com
 */
public class DefaultClassMetadata implements ClassMetadata {
    private String className;

    private boolean isInterface;

    private boolean isAnnotation;

    private boolean isAbstract;

    private boolean isFinal;

    private String enclosingClassName;

    private boolean independentInnerClass;

    private String superClassName;

    private String[] interfaces;

    private Set<String> memberClassNames = new LinkedHashSet<String>();

    @Override
    public String toString() {
        return "DefaultClassMetadata{" +
                "className='" + className + '\'' +
                ", isInterface=" + isInterface +
                ", isAnnotation=" + isAnnotation +
                ", isAbstract=" + isAbstract +
                ", isFinal=" + isFinal +
                ", enclosingClassName='" + enclosingClassName + '\'' +
                ", independentInnerClass=" + independentInnerClass +
                ", superClassName='" + superClassName + '\'' +
                ", interfaces=" + Arrays.toString(interfaces) +
                ", memberClassNames=" + memberClassNames +
                '}';
    }

    public static DefaultClassMetadata newMetadata(int version, int access, String name, String signature, String supername, String[] interfaces) {
        DefaultClassMetadata classMetadata = new DefaultClassMetadata();
        classMetadata.className = ClassUtils.convertResourcePathToClassName(name);
        classMetadata.isInterface = ((access & Opcodes.ACC_INTERFACE) != 0);
        classMetadata.isAnnotation = ((access & Opcodes.ACC_ANNOTATION) != 0);
        classMetadata.isAbstract = ((access & Opcodes.ACC_ABSTRACT) != 0);
        classMetadata.isFinal = ((access & Opcodes.ACC_FINAL) != 0);
        if (supername != null && !classMetadata.isInterface) {
            classMetadata.superClassName = ClassUtils.convertResourcePathToClassName(supername);
        }
        classMetadata.interfaces = new String[interfaces.length];
        for (int i = 0; i < interfaces.length; i++) {
            classMetadata.interfaces[i] = ClassUtils.convertResourcePathToClassName(interfaces[i]);
        }

        return classMetadata;
    }

    public void setIndependentInnerClass(boolean independentInnerClass) {
        this.independentInnerClass = independentInnerClass;
    }

    public void setEnclosingClassName(String enclosingClassName) {
        this.enclosingClassName = enclosingClassName;
    }

    public void setMemberClassNames(Set<String> memberClassNames) {
        this.memberClassNames = memberClassNames;
    }

    @Override
    public String getClassName() {
        return className;
    }

    @Override
    public boolean isInterface() {
        return isInterface;
    }

    @Override
    public boolean isAnnotation() {
        return isAnnotation;
    }

    @Override
    public boolean isAbstract() {
        return isAbstract;
    }

    @Override
    public boolean isConcrete() {
        return !(this.isInterface || this.isAbstract);
    }

    @Override
    public boolean isFinal() {
        return isFinal;
    }

    @Override
    public boolean isIndependent() {
        return (this.enclosingClassName == null || this.independentInnerClass);
    }

    @Override
    public boolean hasEnclosingClass() {
        return (this.enclosingClassName != null);
    }

    @Override
    public String getEnclosingClassName() {
        return enclosingClassName;
    }

    @Override
    public boolean hasSuperClass() {
        return (this.superClassName != null);
    }

    @Override
    public String getSuperClassName() {
        return superClassName;
    }

    @Override
    public String[] getInterfaceNames() {
        return this.interfaces;
    }

    @Override
    public Set<String> getMemberClassNames() {
        return this.memberClassNames;
    }
}
