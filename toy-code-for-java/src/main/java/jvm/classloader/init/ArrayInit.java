package jvm.classloader.init;

/**
 * 对于数组实例，其类型是由JVM在运行期动态生成的。
 *
 * @description 【类加载】数组初始化
 * @author Zhangjie
 * @date 2020/03/30
 */
public class ArrayInit {

    public static void main(String[] args) {
        ArrayObject[] arrayObjects = new ArrayObject[1];
        //创建数组对象时不会初始化数组中的元素对应的对象
        System.out.println(arrayObjects.getClass());
        //所有数组对象的父类都是Object
        System.out.println(arrayObjects.getClass().getSuperclass());
        //.class不会触发初始化
        System.out.println(ArrayObject.class);

        ArrayObject[][] arrayObjectsTwo = new ArrayObject[1][1];
        System.out.println(arrayObjectsTwo.getClass());

        int[] ints = new int[1];
        System.out.println(ints.getClass());

        char[] chars = new char[1];
        System.out.println(chars.getClass());

        boolean[] booleans = new boolean[1];
        System.out.println(booleans.getClass());

        byte[] bytes = new byte[1];
        System.out.println(bytes.getClass());

        float[] floats = new float[1];
        System.out.println(floats.getClass());

        double[] doubles = new double[1];
        System.out.println(doubles.getClass());

        short[] shorts = new short[1];
        System.out.println(shorts.getClass());

        long[] longs = new long[1];
        System.out.println(longs.getClass());
    }

}

class ArrayObject{

    static {
        System.out.println("Hello ArrayObject!!!");
    }

}
