测试类：
```
public class TestCase {

    static {
        System.out.println("static construct");
    }

    {
        System.out.println("obj construct");
    }

    @Resource
    private Object obj;

    private Map<String, Object> hello(String name) {

        hello1();

        return null;
    }

    private void hello1() {hello2();hello3();}
    private void hello2() {hello4();}
    private void hello3() {hello5();}
    private void hello4() {hello6();}
    private void hello5() {hello7();}
    private void hello6() {
        System.out.println();
    }
    private void hello7() {hello5();}

    public static void main(String[] args) {
        new TestCase().hello("");
    }
}

```
堆栈分析api：
```
CodeAnalzer codeAnalzer = new DefaultCodeAnalzer();
MethodCall methodCall = codeAnalzer.analyze(TestCase.class.getName(), "hello", "java.lang.String");
if (methodCall != null) {
    System.out.println(methodCall.render());
}
```
输出结果：
```
`---+com.code.analyze.demo.TestCase:hello
    `---+com.code.analyze.demo.TestCase:hello1(@TestCase.java:28)
        +---+com.code.analyze.demo.TestCase:hello2(@TestCase.java:33)
        |   `---+com.code.analyze.demo.TestCase:hello4(@TestCase.java:34)
        |       `---+com.code.analyze.demo.TestCase:hello6(@TestCase.java:36)
        |           `---+java.io.PrintStream:println(@PrintStream.java:39)
        |               `---+java.io.PrintStream:newLine(@PrintStream.java:696)
        |                   +---java.io.PrintStream:ensureOpen(@PrintStream.java:543)
        |                   +---+java.io.BufferedWriter:newLine(@BufferedWriter.java:544)
        |                   |   `---java.io.BufferedWriter:write
        |                   +---+java.io.BufferedWriter:flushBuffer(@BufferedWriter.java:545)
        |                   |   +---java.io.BufferedWriter:ensureOpen(@BufferedWriter.java:126)
        |                   |   `---java.io.Writer:write(@Writer.java:129)
        |                   +---+java.io.OutputStreamWriter:flushBuffer(@OutputStreamWriter.java:546)
        |                   |   `---+sun.nio.cs.StreamEncoder:flushBuffer(@StreamEncoder.java:185)
        |                   |       +---sun.nio.cs.StreamEncoder:isOpen(@StreamEncoder.java:103)
        |                   |       `---+sun.nio.cs.StreamEncoder:implFlushBuffer(@StreamEncoder.java:104)
        |                   |           +---java.nio.ByteBuffer:position
        |                   |           `---+sun.nio.cs.StreamEncoder:writeBytes(@StreamEncoder.java:291)
        |                   |               +---java.nio.ByteBuffer:flip
        |                   |               +---java.nio.ByteBuffer:limit
        |                   |               +---java.nio.ByteBuffer:position
        |                   |               +---java.nio.channels.WritableByteChannel:write(@WritableByteChannel.java:218)
        |                   |               +---java.nio.ByteBuffer:array(@ByteBuffer.java:221)
        |                   |               +---java.nio.ByteBuffer:arrayOffset(@ByteBuffer.java:221)
        |                   |               +---+java.io.OutputStream:write(@OutputStream.java:221)
        |                   |               |   `---java.io.OutputStream:write(@OutputStream.java:116)
        |                   |               `---java.nio.ByteBuffer:clear
        |                   +---java.io.OutputStream:flush(@OutputStream.java:548)
        |                   +---java.lang.Thread:currentThread
        |                   `---+java.lang.Thread:interrupt(@Thread.java:552)
        |                       +---java.lang.Thread:currentThread
        |                       +---+java.lang.Thread:checkAccess(@Thread.java:916)
        |                       |   +---java.lang.System:getSecurityManager
        |                       |   `---+java.lang.SecurityManager:checkAccess(@SecurityManager.java:1390)
        |                       |       +---java.lang.Thread:getThreadGroup(@Thread.java:675)
        |                       |       `---+java.lang.SecurityManager:checkPermission(@SecurityManager.java:676)
        |                       |           `---java.security.AccessController:checkPermission
        |                       +---java.lang.Thread:interrupt0(@Thread.java:921)
        |                       +---sun.nio.ch.Interruptible:interrupt(@Interruptible.java:922)
        |                       `---java.lang.Thread:interrupt0(@Thread.java:926)
        `---+com.code.analyze.demo.TestCase:hello3(@TestCase.java:33)
            `---+com.code.analyze.demo.TestCase:hello5(@TestCase.java:35)
                `---+com.code.analyze.demo.TestCase:hello7(@TestCase.java:37)
                    `---com.code.analyze.demo.TestCase:hello5(@TestCase.java:41)

```