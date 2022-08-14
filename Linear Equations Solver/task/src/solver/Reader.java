package solver;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class Reader {
    static void getLinEq(String fileName, LinearEquationSolver les) {
        File file = new File(fileName);

        try (Scanner scanner = new Scanner(file)) {
            String ignoreThis = scanner.nextLine();

            while (scanner.hasNextLine()) {
                String[] temp = scanner.nextLine().split("\\s+");
                int length = temp.length;
                ComplexNum[] line = new ComplexNum[length];

                for (int i = 0; i < length; i++) {
                    line[i] = new ComplexNum(temp[i]);
                }
                les.fillRow(line);
            }
        } catch (FileNotFoundException e) {
            String message = "File not found.";
            System.out.println(message);
        }
    }
}

