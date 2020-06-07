package jvm.classloader.load;

import java.sql.Driver;
import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * @description 加载mysql驱动程序实现类
 * @author Zhangjie
 * @date 2020/06/01
 */
public class MySqlDriveLoad {

    public static void main(String[] args) {
//        Thread.currentThread().setContextClassLoader(MySqlDriveLoad.class.getClassLoader().getParent());

        ServiceLoader<Driver> serviceLoader = ServiceLoader.load(Driver.class);

        Iterator<Driver> iterator = serviceLoader.iterator();
        while (iterator.hasNext()){
            Driver driver = iterator.next();
            System.out.println("drive:" + driver + " " + driver.getClass().getClassLoader());
        }
        System.out.println(Thread.currentThread().getContextClassLoader());
        System.out.println(Driver.class.getClassLoader());


    }

}
