
//domain dependent
abstract class State {
    public Object data;

    // abstract void setData(T data);

    // abstract void setData(List<T> data);
}

public class test {
    public static void main(String[] args) {

        JugState jugstate = new JugState(1, 2);
        JugState otherstate1 = new JugState(1, 2);
        JugState otherstate2 = new JugState(2, 4);
        System.out.println(jugstate);
        System.out.println(otherstate1);
        System.out.println(otherstate2);
        System.out.println(jugstate.equals(otherstate1));
        System.out.println(jugstate.equals(otherstate2));
    }
}