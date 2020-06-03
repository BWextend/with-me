package jvm.mnemonic;

import java.util.UUID;

/**
 *@description 【助记符】putstatic 对静态变量进行赋值
 *@author Zhangjie
 *@date 2020/03/30 
 */
public class PutStatic {

    public static String PUT_STATIC_FINAL_STRING;

    public static void main(String[] args) {
        PUT_STATIC_FINAL_STRING = "hello Putstatic!!!";
    }

}
