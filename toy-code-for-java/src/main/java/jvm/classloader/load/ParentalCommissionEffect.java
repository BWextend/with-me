package jvm.classloader.load;

import jvm.classloader.init.ArrayInit;

/**
 * 1、确保Java核心类库的安全：比如`java.lang.Object`由于有双亲委托机制的存在导致最终会被Java本身的类加载器加载，
 * 而不是被自定义类加载器加载，那样的话会存在很多同一个类但是却互不兼容、互不可见的Class对象。
 *
 * 2、确保与Java核心类库中的类不会被`binary name`【即包名+类名】一致的自定义类替代，根本不会被加载到JVM中。
 *
 * 3、不同类加载器对象对`binary name`相同的类创建的命名空间是不同的，不通的。这些来自不同命名空间`binary name`却相同的是并存在JVM中的，许多框架有用到这项技术。
 *
 * @description 双亲委托机制的作用
 * @author Zhangjie
 * @date 2020/05/26
 */
public class ParentalCommissionEffect {

    public static void main(String[] args) throws Exception {
        CustomizeClassLoader customizeClassLoader = new CustomizeClassLoader(sun.security.ec.SunEC.class.getClassLoader());
        Class<?> arrayInitClass = customizeClassLoader.loadClass("jvm.classloader.init.ArrayInit");
        CustomizeClassLoader customizeClassLoaderCopy = new CustomizeClassLoader(sun.security.ec.SunEC.class.getClassLoader());
        Class<?> arrayInitClassCopy = customizeClassLoaderCopy.loadClass("jvm.classloader.init.ArrayInit");
        System.out.println(customizeClassLoader);
        System.out.println(customizeClassLoaderCopy);
        System.out.println(arrayInitClass == arrayInitClassCopy);
        System.out.println(arrayInitClass.getClassLoader());
        System.out.println(arrayInitClassCopy.getClassLoader());
//        ArrayInit arrayInit = (ArrayInit) arrayInitClass.newInstance();
//        arrayInit = (ArrayInit) arrayInitClassCopy.newInstance();

        //1、保证了Java核心类库的安全
        System.out.println("\n==========================================================\n");
        System.out.println("1、确保Java核心类库的安全");
        CustomizeClassLoader customizeClassLoaderParentsApp = new CustomizeClassLoader();
        Class<?> arrayInitClassParentsApp = customizeClassLoaderParentsApp.loadClass("jvm.classloader.init.ArrayInit");
        CustomizeClassLoader customizeClassLoaderParentsAppCopy = new CustomizeClassLoader();
        Class<?> arrayInitClassParentsAppCopy = customizeClassLoaderParentsAppCopy.loadClass("jvm.classloader.init.ArrayInit");
        System.out.println(customizeClassLoaderParentsApp);
        System.out.println(customizeClassLoaderParentsAppCopy);
        System.out.println(arrayInitClassParentsApp == arrayInitClassParentsAppCopy);
        System.out.println(arrayInitClassParentsApp.getClassLoader());
        System.out.println(arrayInitClassParentsAppCopy.getClassLoader());
        ArrayInit arrayInitParentsApp = (ArrayInit) arrayInitClassParentsApp.newInstance();
        arrayInitParentsApp = (ArrayInit) arrayInitClassParentsAppCopy.newInstance();

        //2、确保与Java核心类库中的类不会被`binary name`【即包名+类名】一致的自定义类替代。
        System.out.println("\n==========================================================\n");
        System.out.println("2、确保与Java核心类库中的类不会被`binary name`【即包名+类名】一致的自定义类替代，根本不会被加载到JVM中");
        Class<?> stringClass = customizeClassLoaderParentsApp.loadClass("java.lang.String");
        System.out.println(stringClass.getClassLoader());
    }

}
