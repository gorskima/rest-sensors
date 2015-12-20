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
public class DHT22Config {

	private static final Logger LOGGER = LoggerFactory.getLogger(DHT22Config.class);

	@Value("${dht22-path}")
	private String path;

	@Value("${dht22-gpio-pin}")
	private String gpioPin;

	@Autowired
	private SensorRepository repository;

	@PostConstruct
	public void addSensor() {
		if (Files.notExists(Paths.get(path))) {
			LOGGER.warn("File {} doesn't exist, no sensors will be registered", path);
			return;
		}

		DataProvider tempReader = new ExecDataProvider(buildFullPath(path, gpioPin, "-t"));
		MeasurementProvider tempProvider = new DefaultProvider(tempReader, Prefix.NONE, Unit.CELSIUS);

		DataProvider humidityReader = new ExecDataProvider(buildFullPath(path, gpioPin, "-u"));
		MeasurementProvider humidityProvider = new DefaultProvider(humidityReader, Prefix.NONE, Unit.PERCENT);

		Sensor sensor = new Sensor("dht22", Lists.newArrayList(tempProvider, humidityProvider));
		repository.addSensor(sensor);
	}

	private String buildFullPath(String path, String gpioPin, String flag) {
		return String.format("%s -p %s %s", path, gpioPin, flag);
	}

}
