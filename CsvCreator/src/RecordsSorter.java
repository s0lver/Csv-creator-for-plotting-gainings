import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RecordsSorter {
    ArrayList<RowInFile> records;

    public RecordsSorter(ArrayList<RowInFile> records) {
        this.records = records;
    }

    public ArrayList<RowInFile> sortCollection() {
        List<RowInFile> list = records.stream().sorted(RowInFile.RowsComparator).collect(Collectors.toList());
        ArrayList<RowInFile> rowInFiles = new ArrayList<>();
        rowInFiles.addAll(list);
        return rowInFiles;
    }

}
