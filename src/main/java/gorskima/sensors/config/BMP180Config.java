package gorskima.sensors.config;

import com.google.common.collect.Lists;
import gorskima.sensors.model.*;
import gorskima.sensors.service.SensorRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Configuration
public class BMP180Config {

	private static final Logger LOGGER = LoggerFactory.getLogger(BMP180Config.class);

	@Value("${i2c-bmp085-path}")
	private String path;

	@Autowired
	private SensorRepository repository;

	@PostConstruct
	public void addSensor() {
		Path dir = Paths.get(path);

		if (Files.notExists(dir)) {
			LOGGER.warn("Directory {} doesn't exist, no sensors will be registered", path);
			return;
		}

		Path tempPath = dir.resolve("temp0_input");
		DataProvider tempReader = new FileDataProvider(tempPath);
		MeasurementProvider tempProvider = new DefaultProvider(tempReader, Prefix.DECI, Unit.CELSIUS);

		Path pressurePath = dir.resolve("pressure0_input");
		DataProvider pressureReader = new FileDataProvider(pressurePath);
		MeasurementProvider pressureProvider = new DefaultProvider(pressureReader, Prefix.NONE, Unit.PASCAL);

		Sensor sensor = new Sensor("bmp085", Lists.newArrayList(tempProvider, pressureProvider));
		repository.addSensor(sensor);
	}

}
