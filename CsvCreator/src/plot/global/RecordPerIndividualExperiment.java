package plot.global;

import java.util.Comparator;

public class RecordPerIndividualExperiment{
    private int type;
    private long deltaTimestamp;
    private double batteryLevelLocalSmartphone;
    private double batteryLevelRemoteSmartphone;

    public RecordPerIndividualExperiment(int type, long deltaTimestamp, double batteryLevelLocalSmartphone, double batteryLevelRemoteSmartphone) {
        this.type = type;
        this.deltaTimestamp = deltaTimestamp;
        this.batteryLevelLocalSmartphone = batteryLevelLocalSmartphone;
        this.batteryLevelRemoteSmartphone = batteryLevelRemoteSmartphone;
    }

    public static Comparator<RecordPerIndividualExperiment> RowsComparator = new Comparator<RecordPerIndividualExperiment>() {
        @Override
        public int compare(RecordPerIndividualExperiment row1, RecordPerIndividualExperiment row2) {
            long comparison = row1.deltaTimestamp - row2.deltaTimestamp;

            if (comparison > 0) {
                return 1;
            } else if (comparison < 0) {
                return -1;
            } else {
                return 0;
            }
        }
    };
}
