package gorskima.sensors.endpoint;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.AbstractJsonpResponseBodyAdvice;

import gorskima.sensors.model.Measurement;
import gorskima.sensors.model.MeasurementProvider;
import gorskima.sensors.model.Sensor;
import gorskima.sensors.service.SensorRepository;

@RestController
@RequestMapping("/sensors")
public class SensorEndpoint {

	@Autowired
	private SensorRepository sensorRepository;

	@RequestMapping(method = RequestMethod.GET)
	public List<Sensor> getSensors() {
		return sensorRepository.getSensors();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Sensor getSensor(@PathVariable("id") String id) {
		return sensorRepository.getSensor(id);
	}

    @RequestMapping(value = "/{id}/measurement", method = RequestMethod.GET)
    public List<Measurement> getSensorMeasurements(@PathVariable("id") String id) {
        return sensorRepository.getSensor(id).getMeasurementProviders().stream()
                .map(MeasurementProvider::getMeasurement).collect(Collectors.toList());
    }

    @RequestMapping(value = "/{id}/measurement/{index}", method = RequestMethod.GET)
    public Measurement getSensorMeasurement(@PathVariable("id") String id, @PathVariable("index") Integer index) {
        return sensorRepository.getSensor(id).getMeasurementProviders().get(index).getMeasurement();
    }

}
