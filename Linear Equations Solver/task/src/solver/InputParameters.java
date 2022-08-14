package solver;

class InputParameters {
    private String importFile = "";
    private String exportFile = "";

    InputParameters(String[] args) {
        for (int i = 0; i < args.length; i += 2) {
            switch (args[i]) {
                case "-in" -> this.setImportFile(args[i + 1]);
                case "-out" -> this.setExportFile(args[i + 1]);
                default -> {
                }
            }
        }
    }

    public String getImportFile() {
        return importFile;
    }

    public void setImportFile(String importFile) {
        this.importFile = importFile;
    }

    public String getExportFile() {
        return exportFile;
    }

    public void setExportFile(String exportFile) {
        this.exportFile = exportFile;
    }

}
