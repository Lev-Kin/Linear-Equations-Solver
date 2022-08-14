package solver;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

class Writer {
    static void AnswerToFile(String fileName, LinearEquationSolver les) {
        File file = new File(fileName);

        try (FileWriter fileWriter = new FileWriter(file)) {
            fileWriter.append(les.getAnswer());
        } catch (IOException e) {
            String message = String.format("An exception occurs %s", e.getMessage());

            System.out.println(message);
        }
    }
}

