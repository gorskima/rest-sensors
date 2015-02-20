package gorskima.sensors.service;

import gorskima.sensors.model.Sensor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SensorRepository {

	@Autowired
	private List<Sensor> sensors;

	public void addSensor(Sensor sensor) {
		sensors.add(sensor);
	}

    public List<Sensor> getSensors() {
        return sensors;
    }

    public Sensor getSensor(String id) {
		return sensors.stream().filter(s -> s.getId().equals(id)).findFirst().get();
	}
}
