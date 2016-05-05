import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

public class CsvForPlottingWriter {
    private File file;
    private Date originTime;

    public CsvForPlottingWriter(String filepath, Date originTime) throws IOException {
        this.file = new File(filepath);
        this.originTime = originTime;
        writeHeader();
    }

    private void writeHeader() throws IOException {
        PrintWriter printWriter = new PrintWriter(new FileWriter(file, false));
        writeLine("timestamp,delta-timestamp,level-local,level-remote,battery-gaining");
        printWriter.close();
    }

    private void writeLine(String line) throws IOException {
        PrintWriter printWriter = new PrintWriter(new FileWriter(file, true));
        printWriter.println(line);
        printWriter.close();
    }

    public void writeRecord(RowInCsvPlotFile rowInCsvPlotFile) throws IOException {
        String lineToWrite = rowInCsvPlotFile.toCsvString(originTime);
        writeLine(lineToWrite);
    }
}
