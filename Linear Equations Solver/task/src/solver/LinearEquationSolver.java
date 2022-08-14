package solver;

import java.util.LinkedList;
import java.util.List;

class LinearEquationSolver {
    private final List<ComplexNum[]> matrix = new LinkedList<>();
    private final StringBuilder answer = new StringBuilder();
    private int height;
    private int width;

    void fillRow(ComplexNum[] line) {
        matrix.add(line);
        height = matrix.size();
        width = line.length;
    }

    StringBuilder getAnswer() {
        return answer;
    }

    void solve() {
        solveDown();
        int solutions = numOfSolutions();

        if (solutions == -1) {
            answer.append("No solutions");
        } else if (solutions == 1) {
            answer.append("Infinitely many solutions");
        } else {
            solveUp();
            oneSolution();
        }
    }

    private void solveDown() {
        for (int i = 0; i < height - 1; i++) {
            ComplexNum[] topRow = matrix.get(i);

            if (zeroLine(topRow)) {
                matrix.remove(topRow);
                height = matrix.size();
            } else if (topRow[i].isZero()) {
                moveRow(i);
            }

            topRow = matrix.get(i);

            for (int j = i + 1; j < height; j++) {
                ComplexNum[] botRow = matrix.get(j);
                ComplexNum index = ComplexNum.divide(botRow[i], topRow[i]);

                for (int k = 0; k < width; k++) {
                    ComplexNum temp = ComplexNum.multiply(topRow[k], index);
                    botRow[k] = ComplexNum.subtract(botRow[k], temp);
                }
            }
        }
    }

    private void solveUp() {
        for (int i = height - 1; i > 0; i--) {
            ComplexNum[] botRow = matrix.get(i);

            for (int j = i - 1; j > -1; j--) {
                ComplexNum[] topRow = matrix.get(j);
                ComplexNum index = ComplexNum.divide(topRow[i], botRow[i]);

                for (int k = width - 1; k > i; k--) {
                    ComplexNum temp = ComplexNum.multiply(botRow[k], index);
                    topRow[k] = ComplexNum.subtract(topRow[k], temp);
                }
            }
        }
    }

    private boolean zeroLine(ComplexNum[] row) {
        for (ComplexNum num : row) {
            if (!num.isZero()) {
                return false;
            }
        }

        return true;
    }

    private void moveRow(int index) {
        while (matrix.get(index)[index].isZero()) {
            ComplexNum[] tempRow = matrix.get(index);
            matrix.remove(tempRow);
            matrix.add(tempRow);
        }
    }

    private int numOfSolutions() {
        matrix.removeIf(this::zeroLine);
        height = matrix.size();

        for (ComplexNum[] line : matrix) {
            if (noSolutions(line)) {
                return -1;
            }
        }

        if (height < width - 1) {
            return 1;
        }

        return 0;
    }

    private boolean noSolutions(ComplexNum[] line) {
        int numOfZeros = width - 1;

        for (int i = 0; i < width - 1; i++) {
            if (line[i].isZero()) {
                numOfZeros--;
            }
        }

        return numOfZeros == 0;
    }

    private void oneSolution() {

        for (int i = 0; i < height; i++) {
            ComplexNum a = matrix.get(i)[width - 1];
            ComplexNum b = matrix.get(i)[i];
            ComplexNum answ = ComplexNum.divide(a, b);
            answer.append(answ.toString()).append("\n");
        }
    }
}

