package gorskima.sensors.model;

import java.util.Scanner;

public class OneWireThermTemperatureExtractor implements DataProvider {

    private final DataProvider dataProvider;

    public OneWireThermTemperatureExtractor(DataProvider dataProvider) {
        this.dataProvider = dataProvider;
    }

    @Override
    public String getData() {
        String data = dataProvider.getData();
        Scanner scanner = new Scanner(data);
        scanner.nextLine();
        String secondLine = scanner.nextLine();
        return secondLine.substring(29);
    }
}
