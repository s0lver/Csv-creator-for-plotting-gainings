import plot.individual.CsvPlotGlobalExperimentFileWriter;
import summarizedresults.IndividualExperimentFileReader;
import summarizedresults.RecordPerIndividualExperiment;
import summarizedresults.RecordPerIndividualExperimentSorter;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

public class GlobalProcessor {
    private String[] individualExperimentsFilePaths;
    private String outputFilePath;
    private ArrayList<RecordPerIndividualExperiment>[] fileContents;
    ArrayList<RecordPerIndividualExperiment> allRecords;

    public GlobalProcessor(String[] individualExperimentsFilePaths, String outputFilePath) {
        this.individualExperimentsFilePaths = individualExperimentsFilePaths;
        this.outputFilePath = outputFilePath;
        this.allRecords = new ArrayList<>();
        this.fileContents = new ArrayList[individualExperimentsFilePaths.length];
    }

    public void readFiles() throws IOException, ParseException {
        IndividualExperimentFileReader[] readers = new IndividualExperimentFileReader[individualExperimentsFilePaths.length];
        for (int i = 0; i < individualExperimentsFilePaths.length; i++) {
            IndividualExperimentFileReader reader = new IndividualExperimentFileReader(individualExperimentsFilePaths[i], i);
            ArrayList<RecordPerIndividualExperiment> recordsPerExperiment = reader.readFile();
            fileContents[i] = recordsPerExperiment;
        }
    }

    public void mergeContents() {
        for (int i = 0; i < individualExperimentsFilePaths.length; i++) {
            allRecords.addAll(fileContents[i]);
        }
    }

    public void sortRecords() {
        RecordPerIndividualExperimentSorter sorter = new RecordPerIndividualExperimentSorter(allRecords);
        allRecords = sorter.sortCollection();
    }

    public void generateFile() throws IOException {
        double[] localBatteryLevels = new double[individualExperimentsFilePaths.length];
        double[] remoteBatteryLevels = new double[localBatteryLevels.length];

        for (int i = 0; i < localBatteryLevels.length; i++) {
            localBatteryLevels[i] = 1;
            remoteBatteryLevels[i] = 1;
        }

        CsvPlotGlobalExperimentFileWriter writer = new CsvPlotGlobalExperimentFileWriter(outputFilePath, localBatteryLevels.length);
        int size = allRecords.size();
        for (int i = 0; i < size; i++) {
            RecordPerIndividualExperiment record = allRecords.get(i);
            int type = record.getType();
            double localBatteryLevel = record.getBatteryLevelLocalSmartphone();
            double remoteBatteryLevel = record.getBatteryLevelRemoteSmartphone();

            localBatteryLevels[type] = localBatteryLevel;
            remoteBatteryLevels[type] = remoteBatteryLevel;

            long deltaTime = record.getDeltaTimestamp();
            String lineToWrite = buildStringToWrite(deltaTime, localBatteryLevels, remoteBatteryLevels);
            writer.writeLine(lineToWrite);
        }
    }

    private String buildStringToWrite(long deltaTime, double[] localBatteryLevels, double[] remoteBatteryLevels) {
        StringBuilder sb = new StringBuilder();
        sb.append(deltaTime);

        for (int i = 0; i < localBatteryLevels.length; i++) {
            sb.append(",");
            sb.append(localBatteryLevels[i]).append(",");
            sb.append(remoteBatteryLevels[i]);
        }

        return sb.toString();
    }
}
