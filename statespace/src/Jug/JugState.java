package Jug;

public class JugState implements Cloneable {
    public static Integer capacityJug1 = 3;
    public static Integer capacityJug2 = 5;

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
        String result = " Jug1 : " + jug1 + "; Jug2 : " + jug2;
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
        if (o == this) {
            return true;
        }

        if (!(o instanceof JugState)) {
            return false;
        }

        JugState other = (JugState) o;

        return other.jug1.equals(this.jug1) && other.jug2.equals(this.jug2);
    }

    @Override
    public JugState clone() throws CloneNotSupportedException {
        return (JugState) super.clone();
    }
}