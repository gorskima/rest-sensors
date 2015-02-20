package gorskima.sensors.model;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Test;

public class FileDataProviderTest {

    @Test
    public void testReading() throws URISyntaxException {
        Path path = Paths.get(getClass().getResource("/sensor_data").toURI());
        FileDataProvider provider = new FileDataProvider(path);

        assertThat(provider.getData(), is("123.45\n"));
    }

}
