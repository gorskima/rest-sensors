package gorskima.sensors.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

public class Sensor {

    private final String id;
    private final List<MeasurementProvider> measurementProviders;

    public Sensor(String id, List<MeasurementProvider> measurementProviders) {
        this.id = id;
        this.measurementProviders = measurementProviders;
    }

    public String getId() {
        return id;
    }

    @JsonIgnore
    public List<MeasurementProvider> getMeasurementProviders() {
		return measurementProviders;
	}

}
