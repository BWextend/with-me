package jvm.classloader.load;

/**
 * 使用-XX:TraceClassUnloading配置来跟踪类的卸载
 *
 *@description 【类加载器】类的卸载
 *@author Zhangjie
 *@date 2020/04/13 
 */
public class ClassUninstall {

    public static void main(String[] args) throws Exception {
        Class clazzString = String.class;
        Object str = clazzString.newInstance();

        CustomizeClassLoader customizeClassLoader = new CustomizeClassLoader(sun.security.ec.SunEC.class.getClassLoader());
        Class<?> clazzDefault = customizeClassLoader.loadClass("jvm.classloader.init.ArrayInit");
        Object objectDefault = clazzDefault.newInstance();

        str = null;
        clazzString = null;

        objectDefault = null;
        clazzDefault = null;
        customizeClassLoader = null;

        System.gc();

    }

}
