package gorskima.sensors.config;

import com.google.common.collect.Lists;
import gorskima.sensors.model.*;
import gorskima.sensors.service.SensorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

@Configuration
public class OneWireConfig {

	@Value("${w1-devices-path}")
	private String path;

	@Autowired
	private SensorRepository repository;

	@PostConstruct
	public void addSensors() throws IOException {
		Stream<Path> oneWireDevices = Files.list(Paths.get(path));
		Stream<Path> thermDevices = oneWireDevices.filter(p -> p.getFileName().toString().startsWith("28-"));
		Stream<Sensor> sensors = thermDevices.map(device -> createSensor(device));
		sensors.forEach(repository::addSensor);
	}

	private Sensor createSensor(Path path) {
		String id = path.getFileName().toString();
		DataProvider w1slaveReader = new FileDataProvider(path.resolve("w1_slave"));
		DataProvider tempExtractor = new OneWireThermTemperatureExtractor(w1slaveReader);
		MeasurementProvider tempProvider = new DefaultProvider(tempExtractor, Prefix.MILLI, Unit.CELSIUS);
		return new Sensor(id, Lists.newArrayList(tempProvider));
	}

}
