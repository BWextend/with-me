package jvm.classloader.init;

import java.util.UUID;

/**
 * 接口只有在成员被访问时才会被初始化
 *
 * @description 【类加载】接口初始化
 * @author Zhangjie
 * @date 2020/04/01
 */
public class InterfaceInit {

    public static void main(String[] args) {
        System.out.println(InterfaceInitCase.threadForParent);
    }

}

interface InterfaceInitCaseGrandpa {

    Thread threadForGrandpa = new Thread() {
        {
            System.out.println("hello InterfaceInitCaseGrandpa!!!");
        }
    };

}

interface InterfaceInitParent extends InterfaceInitCaseGrandpa {

    Thread threadForParent = new Thread() {
        {
            System.out.println("hello InterfaceInitParent!!!");
        }
    };

}

class InterfaceInitCase implements InterfaceInitParent {

    static {
        System.out.println("hello InterfaceInitCase!!!");
    }

}