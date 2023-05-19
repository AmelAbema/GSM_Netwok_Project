package LogicPACK;

public enum Station {
    VRD(0),
    VBD(1),
    BTS(2),
    BSC(3);
    final int value;
    Station(int value) {
        this.value = value;
    }
}
