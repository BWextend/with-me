package jvm.mnemonic;

/**
 * @description 【助记符】sipush 将单字节（即2^8，-128——127）的常量值从常量池压入栈顶
 * @author Zhangjie
 * @date 2020/03/30
 */
public class SiPush {

    public static final int SI_PUSH_NUMBER = 129;

    public static void main(String[] args) {
        int siPushNumberCopy = SiPush.SI_PUSH_NUMBER;
    }

}
