package gorskima.sensors.model;

public enum Prefix {

    NONE(1), DECI(10), MILLI(1000);

    public final int factor;

    private Prefix(int factor) {
        this.factor = factor;
    }
}
