package gorskima.sensors.service;

import com.google.common.collect.Lists;
import gorskima.sensors.model.Sensor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SensorRepository {

	private List<Sensor> sensors = Lists.newLinkedList();

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
