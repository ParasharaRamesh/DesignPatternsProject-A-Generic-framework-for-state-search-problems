package Distance;

import java.util.HashMap;

public class DistanceState implements Cloneable {
    private String city;
    private static HashMap<String, DistanceState> pool = new HashMap<String, DistanceState>();

    private DistanceState(String city) {
        this.city = city;
    }

    public String getCity() {
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
    public DistanceState clone() throws CloneNotSupportedException {
        return (DistanceState) super.clone();
    }

    @Override
    public String toString() {
        String result = " City: " + this.city;
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof DistanceState)) {
            return false;
        }

        DistanceState other = (DistanceState) o;

        return other.city.equals(this.city);
    }
}
