package gorskima.sensors.model;

public class Measurement {

    private final float value;
    private final Unit unit;

    public Measurement(float value, Unit unit) {
        this.value = value;
        this.unit = unit;
    }

    public float getValue() {
        return value;
    }

    public Unit getUnit() {
        return unit;
    }
}
