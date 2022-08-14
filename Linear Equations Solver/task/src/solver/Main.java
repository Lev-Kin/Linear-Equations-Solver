package solver;

public class Main {
    public static void main(String[] args) {
        InputParameters input = new InputParameters(args);
        LinearEquationSolver solver = new LinearEquationSolver();
        Reader.getLinEq(input.getImportFile(), solver);
        solver.solve();
        Writer.AnswerToFile(input.getExportFile(), solver);
    }
}

