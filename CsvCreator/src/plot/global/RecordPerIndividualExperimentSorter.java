package plot.global;

import experimentresults.ExperimentResultRecord;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RecordPerIndividualExperimentSorter {
    ArrayList<RecordPerIndividualExperiment> records;

    public RecordPerIndividualExperimentSorter(ArrayList<RecordPerIndividualExperiment> records) {
        this.records = records;
    }

    public ArrayList<RecordPerIndividualExperiment> sortCollection() {
        List<RecordPerIndividualExperiment> list =
                records.stream().sorted(RecordPerIndividualExperiment.RowsComparator).collect(Collectors.toList());

        ArrayList<RecordPerIndividualExperiment> experimentResultRecords = new ArrayList<>();
        experimentResultRecords.addAll(list);
        return experimentResultRecords;
    }
}
