public class Util {
    public static Boolean DEBUG = true;

    public static void debug(String str) {
        if (DEBUG) {
            System.out.println(str);
        }
    }

    public static <T> T convertInstanceOfObject(Object o, Class<T> clazz) {
        try {
            return clazz.cast(o);
        } catch (ClassCastException e) {
            return null;
        }
    }

}