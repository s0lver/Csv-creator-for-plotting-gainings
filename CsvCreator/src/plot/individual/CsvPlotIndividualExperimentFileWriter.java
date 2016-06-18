package plot.individual;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

public class CsvPlotIndividualExperimentFileWriter {
    private File file;
    private Date originTime;
    private PrintWriter printWriter;

    public CsvPlotIndividualExperimentFileWriter(String filepath, Date originTime) throws IOException {
        this.file = new File(filepath);
        this.originTime = originTime;
        writeHeader();
        printWriter = new PrintWriter(new FileWriter(file, true));
    }

    private void writeHeader() throws IOException {
        PrintWriter printWriter = new PrintWriter(new FileWriter(file, false));
        printWriter.println("timestamp,delta-timestamp,level-local,level-remote,battery-gaining");
        printWriter.close();
    }

    private void writeLine(String line) throws IOException {
//        PrintWriter printWriter = new PrintWriter(new FileWriter(file, true));
        printWriter.println(line);
//        printWriter.close();
    }

    public void writeRecord(CsvPlotIndividualExperimentRecord csvPlotIndividualExperimentRecord) throws IOException {
        String lineToWrite = csvPlotIndividualExperimentRecord.toCsvString(originTime);
        writeLine(lineToWrite);
    }
    public void closeWriter() {
        printWriter.close();
    }
}
