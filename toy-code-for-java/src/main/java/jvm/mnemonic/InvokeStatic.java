package jvm.mnemonic;

/**
 *@description 【助记符】invokestatic 调用静态方法
 *@author Zhangjie
 *@date 2020/03/30 
 */
public class InvokeStatic {

    public static void invokeStaticSayHi(){
        System.out.println("Hi InvokeStatic!!!");
    }

    public static void main(String[] args) {
        InvokeStatic.invokeStaticSayHi();
    }

}
