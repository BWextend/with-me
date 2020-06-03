package jvm.classloader.init;

import java.util.UUID;

/**
 * 常量在运行期之前的编译器便加入引用该常量的方法所属类的常量池中，所以引用常量不会导致初始化，除非常量在编译器无法确定值而只能在运行期才能确认值
 *
 *@description 【类加载】常量加载
 *@author Zhangjie
 *@date 2020/03/28 
 */
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
