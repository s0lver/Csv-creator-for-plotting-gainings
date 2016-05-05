import java.io.*;
import java.text.ParseException;
import java.util.ArrayList;

public class YetAnotherFileProcessor {
    private File file;
    private boolean isFromLocalPhone;

    public YetAnotherFileProcessor(String filepath, boolean isFromLocalPhone) {
        this.file = new File(filepath);
        this.isFromLocalPhone = isFromLocalPhone;
    }

    public ArrayList<RowInFile> processFile() throws IOException, ParseException {
        ArrayList<RowInFile> fileDump = new ArrayList<RowInFile>();
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));

        String line;
        while ((line = bufferedReader.readLine()) != null) {
            YetAnotherParser lineParser = new YetAnotherParser(line, isFromLocalPhone);
            fileDump.add(lineParser.parseLine());
        }

        return fileDump;
    }
}
