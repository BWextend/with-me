package jvm.mnemonic;

/**
 * @description 【助记符】ldc 将int、float、String类型的值从常量池压入栈顶
 * @author Zhangjie
 * @date 2020/03/30
 */
public class Ldc {

    public static final String LDC_STRING = "Hello LDC!!!";

    public static void main(String[] args) {
        String str = LDC_STRING;
    }
}
