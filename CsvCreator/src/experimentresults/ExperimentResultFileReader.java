package experimentresults;

import java.io.*;
import java.text.ParseException;
import java.util.ArrayList;

public class ExperimentResultFileReader {
    private File file;
    private boolean isFromLocalPhone;

    public ExperimentResultFileReader(String filepath, boolean isFromLocalPhone) {
        this.file = new File(filepath);
        this.isFromLocalPhone = isFromLocalPhone;
    }

    public ArrayList<ExperimentResultRecord> readFile() throws IOException, ParseException {
        ArrayList<ExperimentResultRecord> fileDump = new ArrayList<ExperimentResultRecord>();
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));

        String line;
        while ((line = bufferedReader.readLine()) != null) {
            ExperimentResultRecordParser lineParser = new ExperimentResultRecordParser(line, isFromLocalPhone);
            fileDump.add(lineParser.parseLine());
        }

        return fileDump;
    }
}
