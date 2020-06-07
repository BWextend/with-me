package jvm.classloader.init;

/**
 * 初始化是按照静态变量的生命顺序进行初始化的
 *
 * @description 【类加载】从准备阶段到初始化结算
 * @author Zhangjie
 * @date 2020/03/31
 */
public class FromReadyToInit {

    public static void main(String[] args) {
        FromReadyToInitClass.printFromReadyToInitClass();
    }

}

/**
 * 从准备阶段到初始化阶段：
 * 1、num1与num2在准备阶段被置为int类型默认值0
 * 2、调用printFromReadyToInitClass方法时打印静态变量num1触发FromReadyToInitClass初始化：
 *      num1没有初始化代码，所以其值为准备阶段设置的值0；
 *      fromReadyToInitClass因为new的原因导致调用构造方法，num1++ 之后为1，num1++ 之后也为1；
 *      fromReadyToInitClass完成初始化后将num2初始化为2。
 */
class FromReadyToInitClass {

    public static int num1;

    private static FromReadyToInitClass fromReadyToInitClass = new FromReadyToInitClass();

    private FromReadyToInitClass() {
        num1 ++;
        num2 ++;
    }

    public static int num2 = 2;

    public static void printFromReadyToInitClass(){
        System.out.println(num1);
        System.out.println(num2);
    }

}
