package concurrent;

public class Singleton {

    static Singleton instance;

    public static Singleton getInstance(){
        if (instance == null) {
            synchronized(Singleton.class) {
                if (instance == null)
                    instance = new Singleton();
            }
        }
        return instance;
    }
}
