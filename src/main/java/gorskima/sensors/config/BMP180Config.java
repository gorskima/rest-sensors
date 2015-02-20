package gorskima.sensors.config;

import com.google.common.collect.Lists;
import gorskima.sensors.model.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.nio.file.Path;
import java.nio.file.Paths;

@Configuration
public class BMP180Config {

	@Value("${i2c-bmp085-path}")
	private String path;

	@Bean
	public Sensor getBMP180Sensor() {
		Path tempPath = Paths.get(path).resolve("temp0_input");
		DataProvider tempReader = new FileDataProvider(tempPath);
		MeasurementProvider tempProvider = new DefaultProvider(tempReader, Prefix.DECI, Unit.CELSIUS);

		Path pressurePath = Paths.get(path).resolve("pressure0_input");
		DataProvider pressureReader = new FileDataProvider(pressurePath);
		MeasurementProvider pressureProvider = new DefaultProvider(pressureReader, Prefix.NONE, Unit.PASCAL);

		return new Sensor("bmp085", Lists.newArrayList(tempProvider, pressureProvider));
	}

}
