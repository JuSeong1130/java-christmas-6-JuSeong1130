package christmas.io;

public class SystemOutput implements Output {
    @Override
    public void print(Object message) {
        System.out.print(message);
    }

    @Override
    public void println(Object message) {
        System.out.println(message);
    }

    @Override
    public void printf(String format, Object... args) {
        System.out.printf(format, args);
    }
}
