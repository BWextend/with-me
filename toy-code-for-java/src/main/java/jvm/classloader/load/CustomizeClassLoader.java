package jvm.classloader.load;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.apache.commons.lang3.StringUtils;

/**
 *@description 【类加载器】自定义类加载器
 *@author Zhangjie
 *@date 2020/04/06 
 */
public class CustomizeClassLoader extends ClassLoader {

    public CustomizeClassLoader(ClassLoader parent) {
        super(parent);
    }

    public CustomizeClassLoader() {
        super();
    }

    @Override
    public Class<?> findClass(String className) {
        System.out.println("CustomizeClassLoader load class : " + className);
        if (StringUtils.isEmpty(className)) {
            throw new NullPointerException("加载类路径为空");
        }
        String classPath = "target/classes/" + className.replace(".", "/") + ".class";
        File file = new File(classPath);
        if (!file.exists()) {
            throw new NullPointerException("加载类不存在");
        }
        byte[] readIntegratedContent = null;
        InputStream inputStream = null;
        ByteArrayOutputStream byteArrayOutputStream = null;
        //1、使用io流以相对路径来读取加载类的内容
        try {
            inputStream = new FileInputStream(classPath);
            byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] readContent = new byte[64];
            int readResult;
            while (-1 != (readResult = inputStream.read(readContent))) {
                byteArrayOutputStream.write(readContent, 0, readResult);
            }
            //2、将其转换为字节数组
            readIntegratedContent = byteArrayOutputStream.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != byteArrayOutputStream) {
                try {
                    byteArrayOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != inputStream) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        //3、获取Class对象
        return null == readIntegratedContent ? null : defineClass(className, readIntegratedContent, 0, readIntegratedContent.length);
    }

    public static void main(String[] args) throws Exception {
        //默认父加载器为系统加载器
        CustomizeClassLoader customizeClassLoaderDefault = new CustomizeClassLoader();
        System.out.println(customizeClassLoaderDefault.getParent());
        Class<?> clazzDefault = customizeClassLoaderDefault.loadClass("jvm.classloader.init.ArrayInit");
        if (null == clazzDefault) {
            throw new NullPointerException("自定义父加载器加载类Class为空");
        }
        Object objectByGinseng = clazzDefault.newInstance();
        System.out.println(objectByGinseng);

        System.out.println("==============================================================");

        //配置父加载器为扩展类加载器
        CustomizeClassLoader customizeClassLoaderBootstrap = new CustomizeClassLoader(sun.security.ec.SunEC.class.getClassLoader());
        System.out.println(customizeClassLoaderBootstrap.getParent());
        Class<?> clazzByBootstrap = customizeClassLoaderBootstrap.loadClass("jvm.classloader.init.ArrayInit");
        if (null == clazzByBootstrap){
            throw new NullPointerException("父加载器为扩展类加载器的自定义加载器加载类Class为空");
        }
        Object objectByBootstrap = clazzByBootstrap.newInstance();
        System.out.println(objectByBootstrap);

        System.out.println("=============================================================");

        //同一个类加载器对象不会加载一个类两次
        Class<?> clazzByBootstrapAgain = customizeClassLoaderBootstrap.loadClass("jvm.classloader.init.ArrayInit");
        if (null == clazzByBootstrapAgain){
            throw new NullPointerException("父加载器为扩展类加载器的自定义加载器加载类Class为空");
        }
        Object objectByBootstrapAgain = clazzByBootstrap.newInstance();
        System.out.println(objectByBootstrapAgain);

        System.out.println("=============================================================");

        //模仿生成的自定义加载器将jvm.classloader.init.ArrayInit类重新加载了一次，因为customizeClassLoaderBootstrap与customizeClassLoaderBootstrapCopy不是同一个加载器对象
        CustomizeClassLoader customizeClassLoaderBootstrapCopy = new CustomizeClassLoader(sun.security.ec.SunEC.class.getClassLoader());
        System.out.println(customizeClassLoaderBootstrapCopy.getParent());
        Class<?> clazzByBootstrapCopy = customizeClassLoaderBootstrapCopy.loadClass("jvm.classloader.init.ArrayInit");
        if (null == clazzByBootstrapCopy){
            throw new NullPointerException("父加载器为扩展类加载器模仿者的自定义加载器加载类Class为空");
        }
        Object objectByBootstrapCopy = clazzByBootstrap.newInstance();
        System.out.println(objectByBootstrapCopy);
    }

}
