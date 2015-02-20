package gorskima.sensors.model;

import com.google.common.io.CharStreams;

import java.io.IOException;
import java.io.InputStreamReader;

public class ExecDataProvider implements DataProvider {

    private final String command;

    public ExecDataProvider(String command) {
        this.command = command;
    }

    @Override
    public String getData() {
        try {
			Process start = Runtime.getRuntime().exec(command);
            start.waitFor();

            try (InputStreamReader r = new InputStreamReader(start.getInputStream())) {
                return CharStreams.toString(r);
            }
        } catch (InterruptedException | IOException e) {
            throw new RuntimeException(e);
        }
    }

}
