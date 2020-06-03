package jvm.mnemonic;

/**
 *@description 【助记符】getstatic 访问静态变量的值
 *@author Zhangjie
 *@date 2020/03/30 
 */
public class GetStatic {

    public static String GET_STATIC_STRING = "Hello GetStatic!!!";

    public static void main(String[] args) {
        GET_STATIC_STRING.length();
    }

}

