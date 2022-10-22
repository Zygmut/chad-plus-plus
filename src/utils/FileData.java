package utils;

public class FileData {

    private String fileName;
    private String fileExtension;
    private String filePath;
    private String outputPath;
    // Get path to current directory
    private String currentPath = System.getProperty("user.dir");

    public FileData(String fileName, String fileExtension,
            String filePath, String outputPath) {
        this.fileName = fileName;
        this.fileExtension = fileExtension;
        this.filePath = filePath;
        this.outputPath = outputPath;
    }

    public FileData() {
        this.fileName = "";
        this.fileExtension = "";
        this.filePath = currentPath + "/";
        this.outputPath = currentPath + "/output/";
    }

    /**
     * Este método se encarga de comprobar que el nombre del fichero cumple los
     * requisitos.
     * 
     * @return boolean - true si el fichero es valido, false si no lo es
     */
    public boolean checkFileData() {
        return !fileName.isEmpty() && !fileExtension.isEmpty()
                && ((!fileExtension.equals(".txt")
                        || !fileExtension.equals(".chpp")));
    }

    public void setMultipleFileData(String fileName, String fileExtension,
            String filePath, String outputPath) {
        this.fileName = fileName;
        this.fileExtension = fileExtension;
        this.filePath = filePath;
        this.outputPath = outputPath;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileExtension() {
        return fileExtension;
    }

    public void setFileExtension(String fileExtension) {
        this.fileExtension = fileExtension;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getOutputPath() {
        return outputPath;
    }

    public void setOutputPath(String outputPath) {
        this.outputPath = outputPath;
    }

}
