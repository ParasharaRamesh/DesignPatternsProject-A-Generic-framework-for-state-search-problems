
//domain dependent
class JugState {
    // member variables
    private Integer jug1;
    private Integer jug2;

    public JugState(int jug1, int jug2) {
        this.jug1 = jug1;
        this.jug2 = jug2;
    }

    @Override
    public String toString() {
        String result = " Jug1 : " + jug1 + "Jug2 :" + jug2;
        return result;
    }

    // @Override
    // public int hashCode() {
    // int hash = 3;
    // hash = 53 * hash
    // + ((this.jug1 != null && this.jug2 != null) ? (this.jug1.hashCode() +
    // this.jug1.hashCode()) : 0);
    // hash = 53 * hash + this.jug1 + this.jug2;
    // return hash;
    // }

    @Override
    public boolean equals(Object o) {
        System.out.println("\tinside equals");
        if (o == this) {
            return true;
        }

        if (!(o instanceof JugState)) {
            return false;
        }

        JugState other = (JugState) o;

        System.out.println("\tinside this" + this);
        System.out.println("\tinside other" + other);
        return other.jug1 == this.jug1 && other.jug2 == this.jug2;
    }

}
