# 指令重排
## 由于指令重排导致的问题
- 编译器为了优化性能，有时会讲程序的执行顺序进行变更，即指令重排，而这会导致一些诡异的问题，例如单例模式的双重校验模型，该模型会在创建前先对对象进行盼空，若存在则直接返回对象，若不存在则尝试获取该类的锁，若未获取到则阻塞，若获取到锁则初始化该对象，然后返回对象。[程序](../toy-code-for-java/src/main/java/concurrent/Singleton.java)如下：

    ```java
    public class Singleton {

        static Singleton instance;

        static Singleton getInstance(){
            if (instance == null) {
                synchronized(Singleton.class) {
                    if (instance == null)
                        instance = new Singleton();
                }
            }
            return instance;
        }
    }
    ```

    如果在instance未被创建之前存在A、B两个线程同时调用getInstance()方法，那这里可能会导致其中一个线程返回空对象。其中原因：
    1、new操作的指令集为：

        1、分配一块内存M；
        2、在内存M上初始化对象instance；
        3、将内存M的地址赋值给对象instance。

    然而被优化后的指令集可能为：

        1、分配一块内存M；
        2、将内存M的地址赋值给对象instance；
        3、在内存M上初始化对象instance。

    2、线程A调用getInstance()方法时instance对象未被创建，然后线程A获取到了锁，随后再进行判空，最后进行new操作。如上所述new操作在操作系统层面来看不是原子操作，而是一个指令集，当线程A执行到被优化后的指令2时，此时线程B进行第一个盼空，发现instance对象不为空，遂返回instance对象，但此时线程B访问instance对象的变量时会出现报空指针异常，因为线程A还没有对instance对象进行初始化。