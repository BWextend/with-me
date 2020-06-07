package jvm.classloader.load;

/**
 * @description Java自带加载器加载路径
 * @author Zhangjie
 * @date 2020/05/18
 */
public class LoaderPath {

    public static void main(String[] args) {
        //根加载器
        System.out.println(System.getProperty("sun.boot.class.path"));
        //扩展加载器
        System.out.println(System.getProperty("java.ext.dirs"));
        //系统加载器
        System.out.println(System.getProperty("java.class.path"));
    }

}
