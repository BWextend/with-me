package jvm.mnemonic;

/**
 *@description 【助记符】iconst_m1——iconst_5 将int型的-1——5从常量池压入栈顶
 *@author Zhangjie
 *@date 2020/03/30 
 */
public class Iconst {

    public static final int ICONST_M1 = -1;

    public static final int ICONST_5 = 5;

    public static final int ICONST_6 = 6;

    public static void main(String[] args) {
        int iconstM1Copy = Iconst.ICONST_M1;
        int iconst5Copy = Iconst.ICONST_5;
        int iconst6Copy = Iconst.ICONST_6;
    }

}
