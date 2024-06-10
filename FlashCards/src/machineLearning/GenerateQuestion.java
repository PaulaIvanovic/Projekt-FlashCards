package machineLearning;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.File;
import java.util.stream.Collectors;

public class GenerateQuestion {
    public static void main(String[] args) {
        try {
            // Path to the Python executable
            String pythonExecutable = "python"; // Adjust this if needed

            // Path to the main Python script of question.ai
            String scriptPath = "C:/Users/paula/Downloads/Questgen.ai-master/Questgen.ai-master/Test.py"; // Adjust this path

            // Create a ProcessBuilder to run the Python script
            ProcessBuilder processBuilder = new ProcessBuilder(pythonExecutable, scriptPath);

            // Set the working directory to the location of the Python script
            File workingDirectory = new File("C:/Users/paula/Downloads/Questgen.ai-master/Questgen.ai-master");
            processBuilder.directory(workingDirectory); // Adjust this path

            // Start the process
            Process process = processBuilder.start();

            // Read the output of the Python script
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String output = reader.lines().collect(Collectors.joining("\n"));

            // Read the error stream (if any)
            BufferedReader errorReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            String errors = errorReader.lines().collect(Collectors.joining("\n"));

            // Wait for the process to complete
            int exitCode = process.waitFor();

            // Print output and errors
            System.out.println("Output from Python script:");
            System.out.println(output);
            if (!errors.isEmpty()) {
                System.err.println("Errors from Python script:");
                System.err.println(errors);
            }

            System.out.println("Exited with code: " + exitCode);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}