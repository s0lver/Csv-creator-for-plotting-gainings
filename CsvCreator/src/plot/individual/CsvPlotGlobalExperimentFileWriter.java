package plot.individual;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class CsvPlotGlobalExperimentFileWriter {
    private File file;

    public CsvPlotGlobalExperimentFileWriter(String filePath, int amountExperiments) throws IOException {
        this.file = new File(filePath);
        writeHeader(amountExperiments);
    }

    private void writeHeader(int amountExperiments) throws IOException {
        PrintWriter printWriter = new PrintWriter(new FileWriter(file, false));
        StringBuilder sb = new StringBuilder();

        sb.append("deltaTime");
        for (int i = 0; i < amountExperiments; i++) {
            sb.append(",");
            sb.append("Period" + i + "-local");
            sb.append(",");
            sb.append("Period" + i + "-remote");
        }

        writeLine(sb.toString());
        printWriter.close();
    }

    public void writeLine(String line) throws IOException {
        PrintWriter printWriter = new PrintWriter(new FileWriter(file, true));
        printWriter.println(line);
        printWriter.close();
    }
}
