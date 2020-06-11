# 初始化

## 1、主动使用

只有当第一次主动引用时才会被初始化，主动引用分以下几种场景：

- 遇到`new`、`getstatic`、`putstatic`或`invokestatic`这4条字节码指令。生成这4条指令的最常见的Java代码场景就是：
  - 使用new关键字实例化对象
  - 读取或设置一个类的静态字段（被final修饰、已在编译期把结果放入常量池的静态字段除外）
  - 调用一个类的静态方法
- 使用`java.lang.reflect`包的方法对类进行反射调用。
- 初始化一个类时，若发现其父类未被初始化，则会对其父类进行初始化。
- 当虚拟机启动时，用户需要指定一个要执行的主类（包含main()方法的那个类），虚拟机会先初始化这个主类。
- 【待确认】当使用JDK1.7的动态语言支持时，如果一个`java.lang.invoke.MethodHandle`实例最后的解析结果`REF_getstatic`、`REF_putstatic`、`REF_invokestatic`的方法句柄，并且这个方法句柄对应的类没有进行过初始化，则虚拟机会对这个类进行初始化。

## 2、常量加载

常量在运行期之前的编译器便加入引用该常量的方法所属类的常量池中，所以引用常量不会导致初始化，除非常量在编译器无法确定值而只能在运行期才能确认值。代码示例如下【[完整代码](../../../toy-code-for-java/src/main/java/jvm/classloader/init/ConstantInit.java)】：

```java
public class ConstantInit {

    public static void main(String[] args) {
        System.out.println(ConstantAttribution.CONSTANT_STRING);
        System.out.println("=======================================");
        System.out.println(UncertainConstantAttribution.UNCERTAIN_CONSTANT_STRING);
    }

}

class ConstantAttribution {

    public static final String CONSTANT_STRING = "hello ConstantAttribution";

    static {
        System.out.println("ConstantAttribution static block");
    }

}

class UncertainConstantAttribution {

    public static final String UNCERTAIN_CONSTANT_STRING = UUID.randomUUID().toString();

    static {
        System.out.println("UncertainConstantAttribution static block");
    }

}
```

运行结果如下：

> hello ConstantAttribution
>
> =======================================
>
> UncertainConstantAttribution static block
> ad48f707-289d-4e8f-9d1c-51ed0caf17e7

可以看见`ConstantAttribution`类的静态代码块没有被执行，原因在于常量`ConstantAttribution.CONSTANT_STRING`在编译期便确定好值并加入常量池。下面是反汇编后得到的Java汇编指令集：

```java
$ javap -c ConstantInit.class
Compiled from "ConstantInit.java"
public class jvm.classloader.init.ConstantInit {
  public jvm.classloader.init.ConstantInit();
    Code:
       0: aload_0
       1: invokespecial #1                  // Method java/lang/Object."<init>":()V
       4: return

  public static void main(java.lang.String[]);
    Code:
       0: getstatic     #2                  // Field java/lang/System.out:Ljava/io/PrintStream;
       3: ldc           #4                  // String hello ConstantAttribution
       5: invokevirtual #5                  // Method java/io/PrintStream.println:(Ljava/lang/String;)V
       8: getstatic     #2                  // Field java/lang/System.out:Ljava/io/PrintStream;
      11: ldc           #6                  // String =======================================
      13: invokevirtual #5                  // Method java/io/PrintStream.println:(Ljava/lang/String;)V
      16: getstatic     #2                  // Field java/lang/System.out:Ljava/io/PrintStream;
      19: getstatic     #7                  // Field jvm/classloader/init/UncertainConstantAttribution.UNCERTAIN_CONSTANT_STRING:Ljava/lang/String;
      22: invokevirtual #5                  // Method java/io/PrintStream.println:(Ljava/lang/String;)V
      25: return
}
```

可以看见`hello ConstantAttribution`这个字符串是通过`ldc`指令获取，即从常量池中被压入方法栈栈顶的；而常量`UncertainConstantAttribution.UNCERTAIN_CONSTANT_STRING`则是通过`getstatic`指令进行获取，而`getstatic`指令被视作主动引用中的一种，遂会对类`UncertainConstantAttribution`进行初始化。跟踪类加载过程可发现类`ConstantAttribution`没有被初始化，而类`UncertainConstantAttribution`则被初始化，如下：

> [Loaded jvm.classloader.init.UncertainConstantAttribution from file:/D:/with-me/toy-code-for-java/target/classes/]

其次从输出上可看见类`ConstantAttribution`的静态代码块未被执行，类`UncertainConstantAttribution`的静态代码块被执行。

### 2.1、疑惑

- 在编译器将某个常量加入常量池是否等于初始化？否则为什么该常量在编译器加入常量池后就不会在在运行期进行初始化？

## 3、数组对象初始化



















