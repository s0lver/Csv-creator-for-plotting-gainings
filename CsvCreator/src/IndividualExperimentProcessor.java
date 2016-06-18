import experimentresults.ExperimentResultFileReader;
import experimentresults.ExperimentResultRecord;
import experimentresults.ExperimentResultRecordSorter;
import plot.individual.CsvPlotIndividualExperimentFileWriter;
import plot.individual.CsvPlotIndividualExperimentRecord;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

public class IndividualExperimentProcessor {
    private ExperimentResultFileReader readerLocal;
    private ExperimentResultFileReader readerRemote;
    private String outputFilePath;
    private ArrayList<ExperimentResultRecord> localReadings, remoteReadings;
    private ArrayList<ExperimentResultRecord> allReadings;

    public IndividualExperimentProcessor(String localFilePath, String remoteFilePath, String outputFilePath) throws IOException {
        this.readerLocal = new ExperimentResultFileReader(localFilePath, true);
        this.readerRemote = new ExperimentResultFileReader(remoteFilePath, false);

        this.outputFilePath = outputFilePath;
    }

    public void readFiles() throws IOException, ParseException {
        localReadings = readerLocal.readFile();
        remoteReadings = readerRemote.readFile();
        ExperimentResultRecord lastRecord = remoteReadings.get(remoteReadings.size() - 1);
        remoteReadings.add(
                new ExperimentResultRecord(false, lastRecord.getLatitude(), lastRecord.getLongitude(),
                        lastRecord.getAltitude(), lastRecord.getAccuracy(), lastRecord.getSpeed(),
                        lastRecord.getTimestamp(), 0, lastRecord.getBatteryVoltage(), lastRecord.getBatteryStatus(),
                        lastRecord.getBatteryTemperature(), lastRecord.getBatteryConnected()));
    }

    public void mergeContents() {
        allReadings = new ArrayList<>();
        allReadings.addAll(localReadings);
        allReadings.addAll(remoteReadings);
        localReadings = null;
        remoteReadings = null;
    }

    public void sortReadings() {
        ExperimentResultRecordSorter sorter = new ExperimentResultRecordSorter(allReadings);
        allReadings = sorter.sortCollection();
    }

    public void generateFile() throws IOException {
        double currentBatteryLevelLocal = 1;
        double currentBatteryLevelRemote = 1;

        Date originTime = allReadings.get(0).getTimestamp();
        CsvPlotIndividualExperimentFileWriter writer = new CsvPlotIndividualExperimentFileWriter(outputFilePath, originTime);
        int size = allReadings.size();
        ExperimentResultRecord currentReading = null;
        for (int i = 0; i < size; i++) {
            currentReading = allReadings.get(i);
            if (currentReading.isFromLocalPhone())
                currentBatteryLevelLocal = currentReading.getBatteryLevel();
            else
                currentBatteryLevelRemote = currentReading.getBatteryLevel();

            CsvPlotIndividualExperimentRecord rowToWrite =
                    new CsvPlotIndividualExperimentRecord(currentReading.getTimestamp(),
                            currentBatteryLevelLocal, currentBatteryLevelRemote);
            writer.writeRecord(rowToWrite);
        }

        CsvPlotIndividualExperimentRecord lastRow = new CsvPlotIndividualExperimentRecord(
                currentReading.getTimestamp(), 0, 0);
        writer.writeRecord(lastRow);

        writer.closeWriter();
    }
}
