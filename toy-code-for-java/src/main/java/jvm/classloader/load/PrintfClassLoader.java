package jvm.classloader.load;

/**
 * 部分JVM获取的类加载器如果时根类加载器可能会返回null
 *
 *@description 【类加载】打印类加载器
 *@author Zhangjie
 *@date 2020/04/02 
 */
public class PrintfClassLoader {

    public static void main(String[] args) throws ClassNotFoundException {
        Class clazzString = Class.forName("java.lang.String");
        System.out.println(clazzString.getClassLoader());

        Class clazzBeLoadedClass = Class.forName("jvm.classloader.load.BeLoadedClass");
        System.out.println(clazzBeLoadedClass.getClassLoader());
    }

}

class BeLoadedClass {

}
