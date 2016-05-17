import java.io.IOException;
import java.text.ParseException;

public class Main {
    static String[] summarizedPaths = new String[] {
            "c:\\users\\rafael\\desktop\\summarized-30.csv",
            "c:\\users\\rafael\\desktop\\summarized-60.csv"
    };

    public static void main(String[] args) throws IOException, ParseException {
//        processIndividualExperiments();
        processExperimentsGlobally();
    }

    private static void processExperimentsGlobally() throws IOException, ParseException {
        String outputFilePath = "c:\\users\\rafael\\desktop\\global-file.csv";
        GlobalProcessor globalProcessor = new GlobalProcessor(summarizedPaths, outputFilePath);
        globalProcessor.readFiles();
        globalProcessor.mergeContents();
        globalProcessor.sortRecords();
        globalProcessor.generateFile();
    }

    private static void processIndividualExperiments() throws IOException, ParseException {

        String[] localFilePaths = new String[]{
                "CsvCreator/input/records-local-30.csv",
                "CsvCreator/input/records-local-60.csv"
        };

        String[] remoteFilesPaths = new String[]{
                "CsvCreator/input/records-remote-30.csv",
                "CsvCreator/input/records-remote-60.csv"
        };



        long init = System.currentTimeMillis();

        for (int i = 0; i < localFilePaths.length; i++) {
            IndividualExperimentProcessor individualExperimentProcessor =
                    new IndividualExperimentProcessor(localFilePaths[i], remoteFilesPaths[i], summarizedPaths[i]);

            individualExperimentProcessor.readFiles();
            individualExperimentProcessor.mergeContents();
            individualExperimentProcessor.sortReadings();
            individualExperimentProcessor.generateFile();

            System.out.println("Files " + localFilePaths[i] + ", " + remoteFilesPaths[i] + " processed");
        }

        long end = System.currentTimeMillis();

        System.out.println("elapsed = " + (end - init));
    }
}
