package utils;

public class FileData {

    private String filePath;
    private String fileName;
    private String fileExtension;
    private String outputPath;
    // Get path to current directory
    private String currentPath = System.getProperty("user.dir");

    public FileData(String fileName, String fileExtension, String outputPath) {
        this.filePath = currentPath + "\\" + fileName + "." + fileExtension;
        this.fileName = fileName;
        this.fileExtension = fileExtension;
        this.outputPath = outputPath;
    }

    public FileData(String filePath, String outputPath) {
        this.filePath = currentPath + "\\" + fileName + "." + fileExtension;
        String substring = filePath.substring(filePath.lastIndexOf("\\", filePath.length() - 1), filePath.length() - 1);
        this.fileName = substring.split("[.]")[0];
        this.fileExtension = substring.split("[.]")[1];
        this.outputPath = outputPath;
    }

    public FileData() {
        this.fileName = "";
        this.fileExtension = "chpp";
        this.outputPath = currentPath + "\\target\\";
    }

    /**
     * Este método se encarga de comprobar que el nombre del fichero cumple los
     * requisitos.
     *
     * @return boolean - true si el fichero es valido, false si no lo es
     */
    public boolean checkFileData() {
        return !fileName.isEmpty() && !fileExtension.isEmpty()
                && ((fileExtension.equals("txt")
                        || fileExtension.equals("chpp")));
    }

    @Deprecated
    public void setMultipleFileData(String fileName, String fileExtension,
            String outputPath) {
        this.fileName = fileName;
        this.fileExtension = fileExtension;
        this.filePath = ".\\" + fileName + "." + fileExtension;
        if (!((outputPath == null) || (outputPath == ""))) {
            this.outputPath = outputPath;
        }
    }

    public void setMultipleFileData(String filePath,
            String outputPath) {
        if (filePath.contains("/") || filePath.contains("\\")) {
            this.filePath = currentPath + filePath;
        } else {
            this.filePath = currentPath + "\\" + filePath;
        }
        String substring = filePath.substring(filePath.lastIndexOf("\\", filePath.length()) + 1, filePath.length());

        this.fileName = substring.split("[.]")[0];
        this.fileExtension = substring.split("[.]")[1];

        if (!((outputPath == null) || (outputPath == ""))) {
            this.outputPath = outputPath;
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final FileData other = (FileData) obj;
        if ((this.fileName == null) ? (other.fileName != null) : !this.fileName.equals(other.fileName)) {
            return false;
        }
        if ((this.fileExtension == null) ? (other.fileExtension != null)
                : !this.fileExtension.equals(other.fileExtension)) {
            return false;
        }
        if ((this.outputPath == null) ? (other.outputPath != null) : !this.outputPath.equals(other.outputPath)) {
            return false;
        }

        return true;
    }

    public String getFileName() {
        return fileName;
    }

    public String getFileExtension() {
        return fileExtension;
    }

    public String getOutputPath() {
        return outputPath;
    }

    public String getFilePath() {
        return this.filePath;
    }

    public String getCurrentPath() {
        return this.currentPath;
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();

        sb.append("=== File Data ===");
        sb.append("\n");
        sb.append("\tcurrentPath: ");
        sb.append(currentPath);
        sb.append("\n");
        sb.append("\tfilePath: ");
        sb.append(filePath);
        sb.append("\n");
        sb.append("\tfileName: ");
        sb.append(fileName);
        sb.append("\n");
        sb.append("\tfileExtension: ");
        sb.append(fileExtension);
        sb.append("\n");
        sb.append("\toutputPath: ");
        sb.append(outputPath);

        return sb.toString();
    }

}
