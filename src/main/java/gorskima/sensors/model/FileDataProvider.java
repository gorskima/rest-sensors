package gorskima.sensors.model;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Created by mgorski on 02.02.15.
 */
public class FileDataProvider implements DataProvider {

    private final Path path;

    public FileDataProvider(Path path) {
        this.path = path;
    }

    @Override
    public String getData() {
        try {
            return new String(Files.readAllBytes(path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
