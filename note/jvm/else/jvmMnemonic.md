# JVM助记符

> JVM助记符是JVM便于人们记忆、并能描述指令功能和指令操作数的符号。

## 常见助记符【注：代码示例请自行编译后查看字节码文件内容】

- getstatic

  [代码示例](../../../toy-code-for-java/src/main/java/jvm/mnemonic/GetStatic.java)，访问静态成员变量

- putstatic

  [代码示例](../../../toy-code-for-java/src/main/java/jvm/mnemonic/PutStatic.java)，对静态成员变量进行赋值

- invokestatic

  [代码示例](../../../toy-code-for-java/src/main/java/jvm/mnemonic/InvokeStatic.java)，调用静态方法

- ldc

  [代码示例](../../../toy-code-for-java/src/main/java/jvm/mnemonic/Ldc.java)，将int、float、String类型的常量从常量池压入方法栈栈顶

- bipush

  [代码示例](../../../toy-code-for-java/src/main/java/jvm/mnemonic/BiPush.java)，将单字节（-128— -2，6—127）的常量值从常量池中压入方法栈栈顶

- sipush

  [代码示例](../../../toy-code-for-java/src/main/java/jvm/mnemonic/SiPush.java)，将一个短整型（-32768— -2，6—32767）的常量值从常量池中压入方法栈栈顶

- inconst_m1——inconst_5

  [代码示例](../../../toy-code-for-java/src/main/java/jvm/mnemonic/Iconst.java)，将int型-1——5压入方法栈栈顶

- anewarray

  [代码示例](../../../toy-code-for-java/src/main/java/jvm/mnemonic/ANewArray.java)，创建一个引用类型数组并将其引用值压入栈顶                              

- newarray

  [代码示例](../../../toy-code-for-java/src/main/java/jvm/mnemonic/NewArray.java)，创建一个指定原始类型数组并将其引用值压入栈顶