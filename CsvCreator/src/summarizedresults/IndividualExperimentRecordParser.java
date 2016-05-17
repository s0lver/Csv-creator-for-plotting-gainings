package summarizedresults;

import java.text.ParseException;

public class IndividualExperimentRecordParser {
    private String lineToParse;
    private int type;

    private static final short COLUMN_DELTA = 1;
    private static final short COLUMN_LOCAL_SMARTPHONE_BATTERY_LEVEL = 2;
    private static final short COLUMN_REMOTE_SMARTPHONE_BATTERY_LEVEL = 3;

    public IndividualExperimentRecordParser(String lineToParse, int type) {
        this.lineToParse = lineToParse;
        this.type = type;
    }

    public RecordPerIndividualExperiment parseLine() throws ParseException {
        String[] lineDivided = lineToParse.split(",");
        long deltaTime = Long.valueOf(lineDivided[COLUMN_DELTA]);
        double localBatteryLevel = Double.valueOf(lineDivided[COLUMN_LOCAL_SMARTPHONE_BATTERY_LEVEL]);
        double remoteBatteryLevel = Double.valueOf(lineDivided[COLUMN_REMOTE_SMARTPHONE_BATTERY_LEVEL]);

        return new RecordPerIndividualExperiment(type, deltaTime, localBatteryLevel, remoteBatteryLevel);
    }
}
