package gorskima.sensors.model;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import java.io.IOException;
import java.net.URISyntaxException;

import org.junit.Test;

public class ExecDataProviderTest {

    @Test
    public void testReading() throws URISyntaxException, IOException, InterruptedException {
        ExecDataProvider provider = new ExecDataProvider("echo foobar");

        assertThat(provider.getData(), is("foobar\n"));
    }

}
