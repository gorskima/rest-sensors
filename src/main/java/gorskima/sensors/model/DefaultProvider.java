package gorskima.sensors.model;

public class DefaultProvider implements MeasurementProvider {

    private final DataProvider dataProvider;
    private final Prefix prefix;
    private final Unit unit;

    public DefaultProvider(DataProvider dataProvider, Prefix prefix, Unit unit) {
        this.dataProvider = dataProvider;
        this.prefix = prefix;
        this.unit = unit;
    }

    @Override
    public Measurement getMeasurement() {
        String data = dataProvider.getData();
        Float aFloat = new Float(data);
        return new Measurement(aFloat / prefix.factor, unit);
    }
}
