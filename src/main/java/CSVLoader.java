import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class CSVLoader {

    private static final int MAX_LINES_IN_CSV = 100;
    private final Path csvResourcePath;

    public CSVLoader(Path csvResourcePath) {

        if (csvResourcePath == null) {
            throw new IllegalArgumentException("Path is null");
        }

        if (isNotCSVPath(csvResourcePath)) {
            throw new IllegalArgumentException("Provided resource path is not a CSV file: " + csvResourcePath);
        }

        this.csvResourcePath = csvResourcePath;
    }


    public List<String> loadLinesFromCsvPath() throws IOException {
        List<String> csvLines = new ArrayList<>();

        int lineCount = 0;

        try (InputStream input = getClass().getClassLoader().getResourceAsStream(csvResourcePath.toString())) {
            if (input == null) {
                throw new IOException("Resource not found: " + csvResourcePath);
            }

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(input))) {
                String header = reader.readLine();
                lineCount++;

                if (header.isBlank()) {
                    throw new IOException("File is missing header");
                }

                String line;
                while ((line = reader.readLine()) != null) {
                    if (lineCount > MAX_LINES_IN_CSV) {
                        throw new IOException("Exceeded maximum line count of " + MAX_LINES_IN_CSV);
                    }
                    csvLines.add(line);
                    lineCount++;
                }
            }
        }

        return csvLines;
    }


    private boolean isNotCSVPath(Path path) {
        return !path.toString().toLowerCase().endsWith(".csv");
    }
}
