import java.util.*;

//domain dependent
class JugState implements Cloneable {
    // capacities of each jug
    public static Integer capacityJug1 = 3;
    public static Integer capacityJug2 = 5;

    // member variables
    private Integer jug1;
    private Integer jug2;

    public JugState(Integer jug1, Integer jug2) {
        this.jug1 = jug1;
        this.jug2 = jug2;
    }

    public Integer getJug1() {
        return this.jug1;
    }

    public Integer getJug2() {
        return this.jug2;
    }

    public void setJug1(Integer jug1) {
        this.jug1 = jug1;
    }

    public void setJug2(Integer jug2) {
        this.jug2 = jug2;
    }

    @Override
    public String toString() {
        String result = " Jug1 : " + jug1 + ";Jug2 : " + jug2;
        return result;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash
                + ((this.jug1 != null && this.jug2 != null) ? (this.jug1.hashCode() + this.jug1.hashCode()) : 0);
        hash = 53 * hash + this.jug1 + this.jug2;
        return hash;
    }

    @Override
    public boolean equals(Object o) {
        // System.out.println("\tinside equals");
        if (o == this) {
            return true;
        }

        if (!(o instanceof JugState)) {
            return false;
        }

        JugState other = (JugState) o;

        // System.out.println("\tinside this" + this);
        // System.out.println("\tinside other" + other);
        return other.jug1 == this.jug1 && other.jug2 == this.jug2;
    }

    @Override
    public JugState clone() {
        // System.out.println("inside clone");
        return new JugState(this.jug1, this.jug2);
    }
    // throws CloneNotSupportedExceptionin
}

class DistanceState implements Cloneable {
    private String city;
    private static HashMap<String, DistanceState> pool = new HashMap<String, DistanceState>();
    // static {
    // pool = new HashMap<String, DistanceState>();
    // }

    private DistanceState(String city) {
        this.city = city;
    }

    public String getCity(String city) {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public static DistanceState createCity(String city) {
        if (pool.get(city) != null) {
            return pool.get(city);
        }
        DistanceState newCity = new DistanceState(city);
        pool.put(city, newCity);
        return newCity;
    }

    @Override
    public DistanceState clone() {
        // System.out.println("inside clone");
        return new DistanceState(this.city);
    }

    @Override
    public String toString() {
        String result = " City: " + this.city;
        return result;
    }

    @Override
    public boolean equals(Object o) {
        // System.out.println("\tinside equals");
        if (o == this) {
            return true;
        }

        if (!(o instanceof DistanceState)) {
            return false;
        }

        DistanceState other = (DistanceState) o;

        return other.city == this.city;
    }

}
