# JVM助记符

> JVM助记符是JVM便于人们记忆、并能描述指令功能和指令操作数的符号。

[玩具代码](../../../../toy-code-for-java/src/main/java/jvm/mnemonic)

## 常见助记符

- getstatic

  访问静态成员变量

- putstatic

  对静态成员变量进行赋值

- invokestatic

  调用静态方法

- ldc

  将int、float、String类型的常量从常量池压入方法栈栈顶

- bipush

  将单字节（-128— -2，6—127）的常量值从常量池中压入方法栈栈顶

- sipush

  将一个短整型（-32768— -2，6—32767）的常量值从常量池中压入方法栈栈顶

- inconst_m1——inconst_5

  将int型-1——5压入方法栈栈顶

- anewarray

  创建一个引用类型数组并将其引用值压入栈顶

- newarray

  创建一个指定原始类型数组并将其引用值压入栈顶