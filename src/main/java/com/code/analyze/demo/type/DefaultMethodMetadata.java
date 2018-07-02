package com.code.analyze.demo.type;


import com.code.analyze.demo.utils.ClassUtils;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

/**
 * @author Ethan Zhang
 * @email ethan.zj@antfin.com
 */
public class DefaultMethodMetadata implements MethodMetadata {

    private String methodName;

    private int access;

    private String declaringClassName;

    private String returnTypeName;

    private String[] exceptionNames;

    private String[] argTypes;

    private boolean interfaceMethod;

    private String descriptor;

    private String signature;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DefaultMethodMetadata that = (DefaultMethodMetadata) o;

        if (interfaceMethod != that.interfaceMethod) return false;
        if (methodName != null ? !methodName.equals(that.methodName) : that.methodName != null) return false;
        if (declaringClassName != null ? !declaringClassName.equals(that.declaringClassName) : that.declaringClassName != null)
            return false;
        return descriptor != null ? descriptor.equals(that.descriptor) : that.descriptor == null;
    }

    @Override
    public int hashCode() {
        int result = methodName != null ? methodName.hashCode() : 0;
        result = 31 * result + (declaringClassName != null ? declaringClassName.hashCode() : 0);
        result = 31 * result + (interfaceMethod ? 1 : 0);
        result = 31 * result + (descriptor != null ? descriptor.hashCode() : 0);
        return result;
    }

    public static DefaultMethodMetadata newMethodMetadata(int access, String className, String name, String desc, String signature, String[] exceptions) {
        DefaultMethodMetadata methodMetadata = new DefaultMethodMetadata();
        methodMetadata.setMethodName(name);
        methodMetadata.setDescriptor(desc);
        methodMetadata.setAccess(access);
        methodMetadata.setDeclaringClassName(ClassUtils.convertResourcePathToClassName(className));

        String returnType = Type.getReturnType(desc).getClassName();
        if (returnType != null) {
            returnType = ClassUtils.convertResourcePathToClassName(returnType);
        }

        methodMetadata.setReturnTypeName(returnType);

        Type[] argumentTypes = Type.getArgumentTypes(desc);
        if (argumentTypes != null && argumentTypes.length > 0) {
            String[] argTypes = new String[argumentTypes.length];
            for (int i = 0; i < argumentTypes.length; i++) {
                argTypes[i] = argumentTypes[i].getClassName();
            }

            methodMetadata.setargTypeNames(argTypes);
        }

        if (exceptions != null && exceptions.length > 0) {
            String[] names = new String[exceptions.length];
            for (int i = 0; i < exceptions.length; i++) {
                names[i] = ClassUtils.convertResourcePathToClassName(exceptions[i]);
            }

            methodMetadata.setExceptionNames(names);
        }

        return methodMetadata;
    }

    @Override
    public String getDescriptor() {
        return descriptor;
    }

    @Override
    public String getSignature() {
        return signature;
    }

    public void setDescriptor(String descriptor) {
        this.descriptor = descriptor;
    }

    @Override
    public String getMethodName() {
        return this.methodName;
    }

    @Override
    public boolean isAbstract() {
        return ((this.access & Opcodes.ACC_ABSTRACT) != 0);
    }

    @Override
    public boolean isStatic() {
        return ((this.access & Opcodes.ACC_STATIC) != 0);
    }

    @Override
    public boolean isFinal() {
        return ((this.access & Opcodes.ACC_FINAL) != 0);
    }

    @Override
    public boolean isOverridable() {
        return (!isStatic() && !isFinal() && ((this.access & Opcodes.ACC_PRIVATE) == 0));
    }

    @Override
    public boolean isInterfaceMethod() {
        return interfaceMethod;
    }

    public void setInterfaceMethod(boolean interfaceMethod) {
        this.interfaceMethod = interfaceMethod;
    }

    @Override
    public String[] getExceptionNames() {
        return exceptionNames;
    }

    @Override
    public String getDeclaringClassName() {
        return this.declaringClassName;
    }

    @Override
    public String getReturnTypeName() {
        return this.returnTypeName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public int getAccess() {
        return access;
    }

    public void setAccess(int access) {
        this.access = access;
    }

    public void setDeclaringClassName(String declaringClassName) {
        this.declaringClassName = declaringClassName;
    }

    public void setReturnTypeName(String returnTypeName) {
        this.returnTypeName = returnTypeName;
    }

    public void setExceptionNames(String[] exceptionNames) {
        this.exceptionNames = exceptionNames;
    }

    public void setargTypeNames(String[] argTypes) {
        this.argTypes = argTypes;
    }

    @Override
    public String[] getArgTypes() {
        return argTypes;
    }

    public void setArgTypes(String[] argTypes) {
        this.argTypes = argTypes;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }
}
