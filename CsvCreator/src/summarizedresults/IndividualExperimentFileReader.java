package summarizedresults;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

public class IndividualExperimentFileReader {
    private File file;
    private int type;

    public IndividualExperimentFileReader(String filepath, int type) {
        this.file = new File(filepath);
        this.type = type;
    }

    public ArrayList<RecordPerIndividualExperiment> readFile() throws IOException, ParseException {
        ArrayList<RecordPerIndividualExperiment> fileDump = new ArrayList<>();
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));

        String line = bufferedReader.readLine(); // skipping header
        while ((line = bufferedReader.readLine()) != null) {
            IndividualExperimentRecordParser lineParser = new IndividualExperimentRecordParser(line, type);
            fileDump.add(lineParser.parseLine());
        }

        return fileDump;
    }
}
