package com.code.analyze.demo.type;

import com.code.analyze.demo.RecursiveRenderStrategy;
import com.code.analyze.demo.RenderStrategy;
import com.code.analyze.demo.utils.ClassUtils;
import org.objectweb.asm.Type;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ethan Zhang
 * @email ethan.zj@antfin.com
 */
public class MethodCall {

    private int lineNumber = -1;

    private String methodName;

    private String declaringClassName;

    private String sourceFile;

    private String[] argTypes;

    private boolean inRecursive;

    private ClassMetadata classMetadata;

    private MethodMetadata methodMetadata;

    private MethodCall parent;

    private RenderStrategy renderStrategy = RecursiveRenderStrategy.getInstance();

    private List<MethodCall> children = new ArrayList<MethodCall>();

    private List<MethodCall> directChildren = new ArrayList<MethodCall>();

    public static MethodCall newMethodCall(int lineNumber, String owner, String name, String desc, boolean recusive) {
        MethodCall methodCall = new MethodCall();
        methodCall.lineNumber = lineNumber;
        methodCall.declaringClassName = ClassUtils.convertResourcePathToClassName(owner);
        methodCall.methodName = name;
        methodCall.inRecursive = recusive;

        Type[] argumentTypes = Type.getArgumentTypes(desc);
        if (argumentTypes != null && argumentTypes.length > 0) {
            String[] argTypes = new String[argumentTypes.length];
            for (int i = 0; i < argumentTypes.length; i++) {
                argTypes[i] = argumentTypes[i].getClassName();
            }

            methodCall.argTypes = argTypes;
        }

        return methodCall;
    }

    /**
     * 是否根节点
     *
     * @return true / false
     */
    public boolean isRoot() {
        return null == parent;
    }

    /**
     * 是否叶子节点
     *
     * @return true / false
     */
    public boolean isLeaf() {
        return children.isEmpty();
    }

    public String render() {
        return renderStrategy.render(this);
    }

    @Override
    public String toString() {
        StringBuilder suffix = new StringBuilder();
        if (sourceFile != null && lineNumber != -1) {
            suffix.append("(@")
                    .append(sourceFile)
                    .append(":")
                    .append(lineNumber)
                    .append(")");
        }

        if (methodMetadata != null) {
            return methodMetadata.getDeclaringClassName() + ":" + methodMetadata.getMethodName() + suffix.toString();
        }

        return declaringClassName + ":" + methodName + suffix.toString();
    }

    public int getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(int lineNumber) {
        this.lineNumber = lineNumber;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getDeclaringClassName() {
        return declaringClassName;
    }

    public void setDeclaringClassName(String declaringClassName) {
        this.declaringClassName = declaringClassName;
    }

    public String getSourceFile() {
        return sourceFile;
    }

    public void setSourceFile(String sourceFile) {
        this.sourceFile = sourceFile;
    }

    public String[] getArgTypes() {
        return argTypes;
    }

    public void setArgTypes(String[] argTypes) {
        this.argTypes = argTypes;
    }

    public boolean isInRecursive() {
        return inRecursive;
    }

    public void setInRecursive(boolean inRecursive) {
        this.inRecursive = inRecursive;
    }

    public ClassMetadata getClassMetadata() {
        return classMetadata;
    }

    public void setClassMetadata(ClassMetadata classMetadata) {
        this.classMetadata = classMetadata;
    }

    public MethodMetadata getMethodMetadata() {
        return methodMetadata;
    }

    public void setMethodMetadata(MethodMetadata methodMetadata) {
        this.methodMetadata = methodMetadata;
    }

    public MethodCall getParent() {
        return parent;
    }

    public void setParent(MethodCall parent) {
        this.parent = parent;
    }

    public List<MethodCall> getChildren() {
        return children;
    }

    public void setChildren(List<MethodCall> children) {
        this.children = children;
    }

    public List<MethodCall> getDirectChildren() {
        return directChildren;
    }

    public void setDirectChildren(List<MethodCall> directChildren) {
        this.directChildren = directChildren;
    }
}
