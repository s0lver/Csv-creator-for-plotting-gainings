import experimentresults.ExperimentResultFileReader;
import experimentresults.ExperimentResultRecord;
import experimentresults.ExperimentResultRecordSorter;
import plot.individual.CsvPlotIndividualExperimentFileWriter;
import plot.individual.CsvPlotIndividualExperimentRecord;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

public class Main {
    public static void main(String[] args) throws IOException, ParseException {
        processIndividualExperiments();
        processExperimentsGlobally();
    }

    private static void processExperimentsGlobally() {
        // TODO follow the algorithm
    }

    private static void processIndividualExperiments() throws IOException, ParseException {
        String fileLocal = "CsvCreator/input/records-local.csv";
        String fileRemote = "CsvCreator/input/records-remote.csv";

        ExperimentResultFileReader processorLocal = new ExperimentResultFileReader(fileLocal, true);
        ExperimentResultFileReader processorRemote = new ExperimentResultFileReader(fileRemote, false);

        ArrayList<ExperimentResultRecord> localReadings = processorLocal.readFile();
        ArrayList<ExperimentResultRecord> remoteReadings = processorRemote.readFile();

        // TODO append the trailing 0 level record to the remote file here

        ArrayList<ExperimentResultRecord> mergedReadings = new ArrayList<>();
        mergedReadings.addAll(localReadings);
        mergedReadings.addAll(remoteReadings);

        ExperimentResultRecordSorter sorter = new ExperimentResultRecordSorter(mergedReadings);
        ArrayList<ExperimentResultRecord> sortedReadings = sorter.sortCollection();

        double currentBatteryLevelLocal = 1;
        double currentBatteryLevelRemote = 1;

        Date originTime = sortedReadings.get(0).getTimestamp();
        CsvPlotIndividualExperimentFileWriter writer = new CsvPlotIndividualExperimentFileWriter("c:\\users\\rafael\\desktop\\csvForPlotting.csv", originTime);

        for (int i = 0; i < sortedReadings.size(); i++) {
            ExperimentResultRecord currentReading = sortedReadings.get(i);
            if (currentReading.isFromLocalPhone())
                currentBatteryLevelLocal = currentReading.getBatteryLevel();
            else
                currentBatteryLevelRemote = currentReading.getBatteryLevel();

            CsvPlotIndividualExperimentRecord rowToWrite = new CsvPlotIndividualExperimentRecord(currentReading.getTimestamp(), currentBatteryLevelLocal, currentBatteryLevelRemote);

            writer.writeRecord(rowToWrite);
        }
    }
}
