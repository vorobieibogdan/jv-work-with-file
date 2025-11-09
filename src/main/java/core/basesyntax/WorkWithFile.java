package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";
    private static final String SEPARATOR = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        int supplySum = 0;
        int buySum = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(SEPARATOR);
                if (parts[0].equals(SUPPLY)) {
                    supplySum += Integer.parseInt(parts[1]);
                } else if (parts[0].equals(BUY)) {
                    buySum += Integer.parseInt(parts[1]);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from file: " + fromFileName, e);
        }

        int result = supplySum - buySum;
        String report = SUPPLY + SEPARATOR + supplySum + "\n"
                + BUY + SEPARATOR + buySum + "\n"
                + RESULT + SEPARATOR + result;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file: " + toFileName, e);
        }
    }
}

