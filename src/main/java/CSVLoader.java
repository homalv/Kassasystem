package assortment;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class CSVLoader {

    /**
     * Load the CSV file from the given resource path into a list of lines.
     *
     * @param csvResourcePath Path to the CSV resource.
     * @return A list of lines from the CSV file.
     * @throws IOException If an error occurs while reading the file.
     */
    public List<String> load(Path csvResourcePath) throws IOException {
        if (csvResourcePath == null) {
            throw new IllegalArgumentException("Path is null");
        }

        if (isNotCSVPath(csvResourcePath)) {
            throw new IllegalArgumentException("Provided resource path is not a CSV file: " + csvResourcePath);
        }

        List<String> csvLines = new ArrayList<>();

        try (InputStream input = getClass().getClassLoader().getResourceAsStream(csvResourcePath.toString())) {
            if (input == null) {
                throw new IOException("Resource not found: " + csvResourcePath);
            }
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(input))) {
                String header = reader.readLine();

                if (header.isBlank()) {
                    throw new IOException("File is missing header");
                }

                String line;
                while ((line = reader.readLine()) != null) {
                    csvLines.add(line);
                }
            }
        }

        return csvLines;
    }

    private boolean isNotCSVPath(Path path) {
        return !path.toString().toLowerCase().endsWith(".csv");
    }
}
