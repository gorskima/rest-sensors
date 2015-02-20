package gorskima.sensors.config;

import com.google.common.collect.Lists;
import gorskima.sensors.model.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.nio.file.Path;
import java.nio.file.Paths;

@Configuration
public class DHT22Config {

	@Value("${dht22-path}")
	private String path;

	@Bean
	public Sensor getDHT22Sensor() {
		DataProvider tempReader = new ExecDataProvider(path + " -t");
		MeasurementProvider tempProvider = new DefaultProvider(tempReader, Prefix.NONE, Unit.CELSIUS);

		DataProvider humidityReader = new ExecDataProvider(path + " -u");
		MeasurementProvider humidityProvider = new DefaultProvider(humidityReader, Prefix.NONE, Unit.PERCENT);

		return new Sensor("dht22", Lists.newArrayList(tempProvider, humidityProvider));
	}

}
