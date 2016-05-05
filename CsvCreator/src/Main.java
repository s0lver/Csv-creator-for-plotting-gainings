import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

public class Main {
    public static void main(String[] args) throws IOException, ParseException {
        String fileLocal = "CsvCreator/input/records-local.csv";
        String fileRemote = "CsvCreator/input/records-remote.csv";

        YetAnotherFileProcessor processorLocal = new YetAnotherFileProcessor(fileLocal, true);
        YetAnotherFileProcessor processorRemote = new YetAnotherFileProcessor(fileRemote, false);

        ArrayList<RowInFile> localReadings = processorLocal.processFile();
        ArrayList<RowInFile> remoteReadings = processorRemote.processFile();

        ArrayList<RowInFile> mergedReadings = new ArrayList<>();
        mergedReadings.addAll(localReadings);
        mergedReadings.addAll(remoteReadings);

        RecordsSorter sorter = new RecordsSorter(mergedReadings);
        ArrayList<RowInFile> sortedReadings = sorter.sortCollection();

        double currentBatteryLevelLocal = 1;
        double currentBatteryLevelRemote = 1;

        Date originTime = sortedReadings.get(0).getTimestamp();
        CsvForPlottingWriter writer = new CsvForPlottingWriter("c:\\users\\rafael\\desktop\\csvForPlotting.csv", originTime);

        for (int i = 0; i < sortedReadings.size(); i++) {
            RowInFile currentReading = sortedReadings.get(i);
            if (currentReading.isFromLocalPhone())
                currentBatteryLevelLocal = currentReading.getBatteryLevel();
            else
                currentBatteryLevelRemote = currentReading.getBatteryLevel();

            RowInCsvPlotFile rowToWrite = new RowInCsvPlotFile(currentReading.getTimestamp(), currentBatteryLevelLocal, currentBatteryLevelRemote);

            writer.writeRecord(rowToWrite);
        }
    }
}
