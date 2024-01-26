import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class GameLogicTest {
        public record ComparisonData(File inputFile, File outputFile) {}
        // Define the source for parameterized tests
        static Stream<ComparisonData> comparisonData() {
            File inputDirectory = new File("src/test/resources/inputs");
            File outputDirectory = new File("src/test/resources/outputs");

            if (!inputDirectory.exists() || !outputDirectory.exists()) {
                fail("Input or output directory not found");
            }

            File[] inputFiles = inputDirectory.listFiles();
            if (inputFiles == null) {
                fail("No input files found");
            }

            return Stream.of(inputFiles)
                    .filter(File::isFile)
                    .map(inputFile -> {
                        // Determine the corresponding output file
                        String outputFileName = inputFile.getName().replace("input", "output");
                        File outputFile = new File(outputDirectory, outputFileName);

                        if (outputFile.exists()) {
                            return new ComparisonData(inputFile, outputFile);
                        } else {
                            fail("Corresponding output file not found: " + outputFileName);
                            return null;
                        }
                    });
        }

        // Define a parameterized test that uses the source
        @ParameterizedTest
        @MethodSource("comparisonData")
        void testMove(ComparisonData comparisonData) {
            File inputFile = comparisonData.inputFile();
            File outputFile = comparisonData.outputFile();

            // Redirect System.out to capture output
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outputStream));

            // Create an instance of your game logic
            PlayableLogic gameLogic = new GameLogic();

            // Read input file and process it
            String inputContent = readFile(inputFile);
            List<Position> moves = parse(inputContent);

            // Process the moves using your game logic
            for (int i = 0; i < moves.size() - 1; i += 2) {
                Position from = moves.get(i);
                Position to = moves.get(i + 1);
                boolean result = gameLogic.move(from, to);
                assertTrue(result); // Ensure each move is successful
            }

            // Restore the original System.out
            System.setOut(System.out);

            // Retrieve the captured output
            String capturedOutput = outputStream.toString();

            // Read the expected output
            String expectedOutput = readFile(outputFile);
            capturedOutput = capturedOutput.replaceAll("\r","");

            // Assert if the captured output is as expected
            assertEquals(expectedOutput, capturedOutput);
        }

    private String readFile(File file) {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
            fail("Error reading file: " + file.getName());
        }
        return content.toString();
    }

    public static List<Position> parse(String movesString) {
        List<Position> positions = new ArrayList<>();
        Pattern pattern = Pattern.compile("\\((\\d+), (\\d+)\\)");
        Matcher matcher = pattern.matcher(movesString);

        while (matcher.find()) {
            int x = Integer.parseInt(matcher.group(1));
            int y = Integer.parseInt(matcher.group(2));
            positions.add(new Position(x, y));
        }

        return positions;
    }
}
