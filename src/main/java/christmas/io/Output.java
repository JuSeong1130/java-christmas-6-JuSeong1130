package christmas.io;

public interface Output {

    void print(Object message);

    void println(Object message);

    void printf(String format,Object... args);


}
