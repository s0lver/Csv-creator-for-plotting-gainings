package experimentresults;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ExperimentResultRecordSorter {
    ArrayList<ExperimentResultRecord> records;

    public ExperimentResultRecordSorter(ArrayList<ExperimentResultRecord> records) {
        this.records = records;
    }

    public ArrayList<ExperimentResultRecord> sortCollection() {
        List<ExperimentResultRecord> list = records.stream().sorted(ExperimentResultRecord.RowsComparator).collect(Collectors.toList());
        ArrayList<ExperimentResultRecord> experimentResultRecords = new ArrayList<>();
        experimentResultRecords.addAll(list);
        return experimentResultRecords;
    }

}
