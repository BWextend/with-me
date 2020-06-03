package jvm.classloader.load;

/**
 * -XX:+TraceClassLoading，用于追踪运行当前类的加载信息并打印出来（可以在 IDEA 对应的类的 VM option 中进行配置）
 *
 * JVM 参数的格式：
 * -XX:+<option>，表示开启 option 选项
 * -XX:-<option>，表示关闭 option 选项
 * -XX:<option>=value，表示将 option 的值设置为 value
 *
 * @description 【类加载】类主动使用与被动使用的区别
 * @author Zhangjie
 * @date 2020/03/28
 */
public class DifferenceBetweenActivePassiveUse {

    public static void main(String[] args) {
        System.out.println(DifferenceBetweenActivePassiveUseChild.parentStr);
        System.out.println("===================================================");
        System.out.println(DifferenceBetweenActivePassiveUseChild.childStr);
    }

}

class DifferenceBetweenActivePassiveUseParent {

    public static String parentStr = "hello DifferenceBetweenActivePassiveUseParent";

    static {
        System.out.println("DifferenceBetweenActivePassiveUseParent static block");
    }

}

class DifferenceBetweenActivePassiveUseChild extends DifferenceBetweenActivePassiveUseParent {

    public static String childStr = "hello DifferenceBetweenActivePassiveUseChild";

    static {
        System.out.println("DifferenceBetweenActivePassiveUseChild static block");
    }

}
