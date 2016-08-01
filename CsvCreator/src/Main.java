import java.io.IOException;
import java.text.ParseException;

public class Main {
    static String[] summarizedPaths = new String[] {
            "c:\\users\\rafael\\desktop\\summarized-30.csv",
            "c:\\users\\rafael\\desktop\\summarized-60.csv",
            "c:\\users\\rafael\\desktop\\summarized-90.csv",
            "c:\\users\\rafael\\desktop\\summarized-120.csv",
            "c:\\users\\rafael\\desktop\\summarized-150.csv",
    };

    public static void main(String[] args) throws IOException, ParseException {
        processIndividualExperiments();
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

//        String[] localFilePaths = new String[]{
//                "CsvCreator/input/r1-records-local-30.csv",
//                "CsvCreator/input/r1-records-local-60.csv",
//                "CsvCreator/input/r1-records-local-90.csv",
//                "CsvCreator/input/r1-records-local-120.csv",
//                "CsvCreator/input/r1-records-local-150.csv",
//        };
//
//        String[] remoteFilesPaths = new String[]{
//                "CsvCreator/input/r1-records-remote-30.csv",
//                "CsvCreator/input/r1-records-remote-60.csv",
//                "CsvCreator/input/r1-records-remote-90.csv",
//                "CsvCreator/input/r1-records-remote-120.csv",
//                "CsvCreator/input/r1-records-remote-150.csv",
//        };

        String[] localFilePaths = new String[]{
                "CsvCreator/input/r2-records-local-30.csv",
                "CsvCreator/input/r2-records-local-60.csv",
                "CsvCreator/input/r2-records-local-90.csv",
                "CsvCreator/input/r2-records-local-120.csv",
                "CsvCreator/input/r2-records-local-150.csv",
        };

        String[] remoteFilesPaths = new String[]{
                "CsvCreator/input/r2-records-remote-30.csv",
                "CsvCreator/input/r2-records-remote-60.csv",
                "CsvCreator/input/r2-records-remote-90.csv",
                "CsvCreator/input/r2-records-remote-120.csv",
                "CsvCreator/input/r2-records-remote-150.csv",
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
