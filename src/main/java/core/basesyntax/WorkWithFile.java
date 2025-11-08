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
        String report = createReport(readFile(fromFileName));
        writeToFile(toFileName, report);
    }

    private String[] readFile(String fromFileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            return reader.lines().toArray(String[]::new);
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from file: " + fromFileName, e);
        }
    }

    private String createReport(String[] lines) {
        int supplySum = 0;
        int buySum = 0;

        for (String line : lines) {
            String[] parts = line.split(SEPARATOR);
            String operation = parts[0];
            int amount = Integer.parseInt(parts[1]);

            if (operation.equals(SUPPLY)) {
                supplySum += amount;
            } else if (operation.equals(BUY)) {
                buySum += amount;
            }
        }

        int result = supplySum - buySum;
        StringBuilder reportBuilder = new StringBuilder();
        reportBuilder.append(SUPPLY).append(SEPARATOR).append(supplySum).append(System.lineSeparator())
                .append(BUY).append(SEPARATOR).append(buySum).append(System.lineSeparator())
                .append(RESULT).append(SEPARATOR).append(result);

        return reportBuilder.toString();
    }

    private void writeToFile(String toFileName, String data) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(data);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file: " + toFileName, e);
        }
    }
}
